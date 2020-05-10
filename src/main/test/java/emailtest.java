import cn.itsource.domain.Productstock;
import cn.itsource.repository.IProductstockRepository;
import cn.itsource.util.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class emailtest {
    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private IProductstockRepository productstockRepository;
    @Test
    public void test() throws Exception {
        List<Productstock> list = productstockRepository.findAll();
        for (Productstock productstock : list) {
            if (productstock.getWarning()) {/*开启警告查询*/
                System.out.println(productstock.getProduct().getName());
            }
        }
    }
}
