package cn.itsource.service.impl;

import cn.itsource.domain.Purchasebillitem;
import cn.itsource.domain.vo.EchartsLine;
import cn.itsource.domain.vo.PurchaseBillItemVo;
import cn.itsource.domain.vo.SeriesLine;
import cn.itsource.query.PurchasebillitemQuery;
import cn.itsource.repository.IPurchasebillitemRepository;
import cn.itsource.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * (Purchasebillitem)表Service层接口
 *
 * @author 申林
 * @since 2020-05-06 10:28:42
 */
@Service
public class PurchasebillitemServiceImpl extends BaseServiceImpl<Purchasebillitem, Long> implements IPurchasebillitemService {

    private IPurchasebillitemRepository purchasebillitemRepository;

    @Autowired
    public void setPurchasebillitemRepository(IPurchasebillitemRepository purchasebillitemRepository) {
        this.purchasebillitemRepository = purchasebillitemRepository;
    }

    /**
     * 不分页的高级查询jpql
     *
     * @param purchasebillitemQuery
     * @return
     */
    @Override
    public List<PurchaseBillItemVo> findAllVo(PurchasebillitemQuery purchasebillitemQuery) {
        List<Purchasebillitem> items = purchasebillitemRepository.findByJpql("select o" + purchasebillitemQuery.getJpql(), purchasebillitemQuery.getParams().toArray());
        List<PurchaseBillItemVo> vos = new ArrayList<>();
        for (Purchasebillitem item : items) {
            PurchaseBillItemVo itemVo = new PurchaseBillItemVo(item);
            vos.add(itemVo);
        }
        return vos;

    }

    /**
     * 饼状图数据
     *
     * @param purchasebillitemQuery
     * @return
     */
    @Override
    public List<Map<String, Object>> findChartsPie(PurchasebillitemQuery purchasebillitemQuery) {
        List<Map<String, Object>> pieData = new ArrayList<>();

        String jpql = "select " + purchasebillitemQuery.getGroupField() + ",sum(o.amount),sum(o.num)" + purchasebillitemQuery.getJpql()
                + " group by " + purchasebillitemQuery.getGroupField();
        List<Object[]> list = purchasebillitemRepository.findByJpql(jpql, purchasebillitemQuery.getParams().toArray());
        Map<String, Object> map;
        for (Object[] objects : list) {
            map = new HashMap<>();
            map.put("name", objects[0]);
            map.put("value", objects[1]);
            map.put("num", objects[2]);
            pieData.add(map);
        }
        return pieData;
    }

    @Override
    public List<SeriesLine> findChartsLine(PurchasebillitemQuery purchasebillitemQuery) {
        List<SeriesLine> lineData = new ArrayList<>();
        String jpql = "select " + "date_format(o.bill.vdate,'%Y年%m月')" + ",sum(o.amount),sum(o.num) as totalnum,o.product.name" + purchasebillitemQuery.getJpql()
                + " group by date_format(o.bill.vdate,'%Y年%m月'),o.product.name ORDER BY date_format(o.bill.vdate,'%Y年%m月')";
        List<Object[]> listobj = purchasebillitemRepository.findByJpql(jpql, purchasebillitemQuery.getParams().toArray());
        List<EchartsLine> list = new ArrayList<>();/*处理一下obj类型好处理*/
        for (Object[] objects : listobj) {
            EchartsLine echartsLine = new EchartsLine();
            echartsLine.setVateformat(String.valueOf(objects[0]));
            echartsLine.setTotalprice(new BigDecimal(String.valueOf(objects[1])));
            echartsLine.setTotalnum(new BigDecimal(String.valueOf(objects[2])));
            echartsLine.setProductname(String.valueOf(objects[3]));
            list.add(echartsLine);
        }
        Set<String> productName = new HashSet<>();
        for (EchartsLine echartsLine : list) {
            productName.add(echartsLine.getProductname());
        }
        for (String s : productName) {
            SeriesLine seriesLine = new SeriesLine();
            seriesLine.setName(s);
            for (EchartsLine line : list) {
                if (line.getProductname().equals(s)) {
                    seriesLine.getData().add(line.getTotalprice());
                    seriesLine.getDate().add(line.getVateformat());
                }
            }
            lineData.add(seriesLine);
        }
        return lineData;
    }
}