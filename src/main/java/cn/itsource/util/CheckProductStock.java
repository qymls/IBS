package cn.itsource.util;

import cn.itsource.domain.Productstock;
import cn.itsource.repository.IProductstockRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public class CheckProductStock {
    @Autowired
    private IProductstockRepository productstockRepository;

    @Autowired
    private EmailUtils emailUtils;

    public void checkProductStock() {
        List<Productstock> list = productstockRepository.findAll();
        for (Productstock productstock : list) {
            if (productstock.getWarning()) {/*开启警告查询*/
                if (productstock.getNum().doubleValue() >= productstock.getTopnum().doubleValue()) {
                    System.out.println("触发超高预警"+productstock.getProduct().getName());
                    //emailUtils.send("123@qq.com", "库存超高预警", productstock.getProduct().getName() + "库存超高，请及时处理");
                }
                if (productstock.getNum().doubleValue() <= productstock.getBottomnum().doubleValue()) {
                    System.out.println("触发超低预警"+productstock.getProduct().getName());
                    //emailUtils.send("123@qq.com", "库存不足预警", productstock.getProduct().getName() + "库存不足，请及时处理");
                }
            }
        }
    }
}
