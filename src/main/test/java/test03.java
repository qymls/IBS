import cn.itsource.domain.Employee;
import cn.itsource.repository.IEmployeeRepository;
import com.github.wenhao.jpa.Specifications;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:dataSource.xml")
public class test03 {
    /**
     * specification 工具类
     *
     * @throws Exception
     */
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void test() throws Exception {
        Specification<Employee> specification = Specifications.<Employee>and()
                .like("username", "%role%")
                .ge("age", 20)
                .build();
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC,"id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC,"age");
        Sort sort = new Sort(order1,order2);/*可变参数*/
        PageRequest pageRequest = new PageRequest(0,10,sort);
        Page<Employee> employeePage = employeeRepository.findAll(specification,pageRequest);
        System.out.println(employeePage.getContent());

    }
}
