import cn.itsource.domain.Employee;
import cn.itsource.query.EmployeeQuery;
import cn.itsource.repository.IEmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dataSource.xml")
public class test_05 {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void findPageByQuery() throws Exception {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        /*employeeQuery.setUsername("%role%");*/
       /* employeeQuery.setAge("25");*/
        Page<Employee> page = employeeRepository.findPageByQuery(employeeQuery);
        System.out.println(page);
    }

    @Test
    public void findByQuery() throws Exception {
        EmployeeQuery employeeQuery = new EmployeeQuery();
        employeeQuery.setUsername("%role%");
     /*   employeeQuery.setAge("25");*/
        List<Employee> page = employeeRepository.findByQuery(employeeQuery);
        System.out.println(page);
    }

    @Test
    public void findByJpql() throws Exception {
        String sql = "select e from Employee e where username = ? and password = ?";
        List<Employee> employees = employeeRepository.findByJpql(sql,"admin","123456");
        System.out.println(employees);
    }



}
