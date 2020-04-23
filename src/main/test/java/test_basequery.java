import cn.itsource.domain.Employee;
import cn.itsource.query.EmployeeQuery;
import cn.itsource.repository.IEmployeeRepository;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dataSource.xml")
public class test_basequery {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void test() throws Exception {

        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setUsername("%role%");
       /* employeeQuery.setAge("25");*/
        employeeQuery.setOrderFiled("id");
        Sort sort = null;
        if (StringUtils.isNoneBlank(employeeQuery.getOrderFiled())) {
            Sort.Direction direction = Sort.Direction.ASC;
            if ("desc".equalsIgnoreCase(employeeQuery.getOrderType())) {
                direction =  Sort.Direction.DESC;
            }
            Sort.Order order = new Sort.Order(direction, employeeQuery.getOrderFiled());
            sort = new Sort(order);/*可变参数*/
        }

        Specification<Employee> specification = Specifications.<Employee>and()
                .like("username", employeeQuery.getUsername())
               /* .ge("age", employeeQuery.getAge())*/
                .build();

        PageRequest pageRequest = new PageRequest(employeeQuery.getCurrentPage(), employeeQuery.getPageSize(), sort);
        Page<Employee> employeePage = employeeRepository.findAll(specification, pageRequest);
        System.out.println(employeePage.getContent());

    }

    @Test
    public void test02() throws Exception {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setUsername("%role%");
       /* employeeQuery.setAge("25");*/
        Page<Employee> employeePage = employeeRepository.findAll(employeeQuery.getSpecification(), employeeQuery.getPageable());
        System.out.println(employeePage.getContent());

    }

    /**
     * 封装了分页
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setUsername("%role%");
      /*  employeeQuery.setAge("25");*/
        Page<Employee> employeePage = employeeRepository.findAll(employeeQuery.getSpecification(), employeeQuery.getPageable());
        System.out.println(employeePage.getContent());

    }
}
