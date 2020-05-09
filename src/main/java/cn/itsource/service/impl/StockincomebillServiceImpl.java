package cn.itsource.service.impl;

import cn.itsource.domain.Productstock;
import cn.itsource.domain.Stockincomebill;
import cn.itsource.domain.Stockincomebillitem;
import cn.itsource.repository.IProductstockRepository;
import cn.itsource.repository.IStockincomebillRepository;
import cn.itsource.service.IStockincomebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * (Stockincomebill)表Service层接口
 *
 * @author 申林
 * @since 2020-05-09 14:21:59
 */
@Service
public class StockincomebillServiceImpl extends BaseServiceImpl<Stockincomebill, Long> implements IStockincomebillService {

    private IStockincomebillRepository stockincomebillRepository;


    private IProductstockRepository productstockRepository;

    @Autowired
    public void setStockincomebillRepository(IStockincomebillRepository stockincomebillRepository) {
        this.stockincomebillRepository = stockincomebillRepository;
    }

    @Autowired
    public void setProductstockRepository(IProductstockRepository productstockRepository) {
        this.productstockRepository = productstockRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public void audit(Stockincomebill stockincomebill) {
        //这里要注意，如果商品是有保质期字段的话，还需要加上保质期作为查询条件
        String jpql = "select ps from Productstock ps where ps.product.id = ?1 and ps.depot.id = ?2";

        Productstock productstock;
        //循环遍历入库单中的所有明细数据
        for (Stockincomebillitem item : stockincomebill.getBillitems()) {
            //先通过仓库id和商品id去查询库存表数据
            List<Productstock> list = productstockRepository.findByJpql(jpql, item.getProduct().getId(),
                    item.getBill().getDepot().getId());
            //判断
            if (list != null && list.size() > 0) {
                System.out.println("当前要入库的商品在库存表中已存在");
                //查询到了数据，表示当前要入库的商品在库存表中已存在：直接修改数量（增加）
                productstock = list.get(0);
                //修改库存数量：原有数量加上本次入库的数量
                productstock.setNum(productstock.getNum().add(item.getNum()));
                //修改库存小计金额：原有小计金额加上本次入库的小计金额
                productstock.setAmount(productstock.getAmount().add(item.getAmount()));
                //重新计算一下单价：BigDecimal的除法运算除不尽的时候会抛异常  RoundingMode.HALF_UP表示四舍五入
                productstock.setPrice(productstock.getAmount().divide(productstock.getNum(), 2, RoundingMode.HALF_UP));
                //然后入库时间以当前系统时间为准
                productstock.setIncomedate(new Date());
                productstockRepository.save(productstock);
            } else {
                System.out.println("当前要入库的商品在库存表中不存在");
                //没查询到数据，表示当前要入库的商品在库存表中不存在：直接新增一行库存表数据
                productstock = new Productstock();
                productstock.setProduct(item.getProduct());
                productstock.setDepot(item.getBill().getDepot());
                productstock.setPrice(item.getPrice());
                productstock.setNum(item.getNum());
                productstock.setAmount(item.getAmount());
                productstock.setIncomedate(new Date());
                productstockRepository.save(productstock);
            }
        }
    }
}