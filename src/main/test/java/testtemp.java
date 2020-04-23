import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class testtemp {
  /*  @Autowired
    private IDepotService depotService;

    @Test
    public void test() throws Exception {
        List<Depot> depotList = depotService.findAll();
        System.out.println(depotList.size());
    }*/
}
