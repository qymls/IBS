import cn.itsource.domain.Employee;
import cn.itsource.domain.Menu;
import cn.itsource.query.MenuQuery;
import cn.itsource.repository.IEmployeeRepository;
import cn.itsource.repository.IMenuRepository;
import cn.itsource.service.IMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test01 {
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Autowired
    private IMenuRepository menuRepository;

    @Autowired
    private IMenuService menuService;

    @Test
    public void test() throws Exception {
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println(employeeList.size());
    }

    @Test
    public void save() throws Exception {
        Employee employee = new Employee();
        employee.setAge(20l);
        employee.setUsername("dada");
        employee.setPassword("123123");
        employee.setHeadImage("123");
        employee.setEmail("131313");
        employeeRepository.save(employee);
    }

    @Test
    public void update() throws Exception {
        Employee employee = employeeRepository.findOne(274l);
        employee.setEmail("000");
        employee.setAge(99l);
        employeeRepository.save(employee);
    }

    @Test
    public void delete() throws Exception {
        employeeRepository.delete(273L);
    }

    @Test
    public void findOne() throws Exception {
        Employee employee = employeeRepository.findOne(270l);
        System.out.println(employee);
    }

    /**
     * Spring data的jap页码是重0开始的
     * @throws Exception
     */
    @Test
    public void page() throws Exception {
        PageRequest pageRequest = new PageRequest(0,10);
        Page<Employee> employeePage = employeeRepository.findAll(pageRequest);
        System.out.println(employeePage.getTotalElements());/*总记录数*/
        System.out.println(employeePage.getTotalPages());/*总页数*/
        System.out.println(employeePage.getNumber());/*当前页数*/
        System.out.println(employeePage.getNumberOfElements());/*当前页有多少行*/
        System.out.println(employeePage.getSize());/*当前页显示的行数*/
        List<Employee> employeeList = employeePage.getContent();/*获取的集合*/
    }

    @Test
    public void testFindBySort() throws Exception {
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC,"id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC,"age");
        Sort sort = new Sort(order1,order2);/*可变参数*/
        List<Employee> employeeList = employeeRepository.findAll(sort);
        System.out.println(employeeList);

    }


    /*高级查询*/
    @Test
    public void findAllByUsernameLike() throws Exception {
        List<Employee> employeeList = employeeRepository.findAllByUsernameLike("%admin%");
        System.out.println(employeeList.size());
    }

    @Test
    public void findByUsernameAndPassword(){
        Employee admin = employeeRepository.findByUsernameAndPassword("admin", "123456");
        System.out.println(admin);
    }


    @Test
    public void findByNameAndPwd(){
        Employee admin = employeeRepository.findByNameAndPwd("admin", "123456");
        System.out.println(admin);
    }

    @Test
    public void findByNameAndPwdNative(){
        Employee admin = employeeRepository.findByNameAndPwdNative("admin", "123456");
        System.out.println(admin);
    }


    @Test
    public void findByName(){
       /* Menu menu = menuRepository.findByName("系统管理");*//*找不到就是空*//*
        System.out.println(menu);*/
        List<Menu> all = menuRepository.findAll();
        System.out.println(all);
    }

    @Test
    public void pageOrder() throws Exception {
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC,"id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC,"age");
        Sort sort = new Sort(order1,order2);/*可变参数*/
        PageRequest pageRequest = new PageRequest(0,10,null);
        MenuQuery menuQuery = new MenuQuery();
        menuQuery.setName("测试");
        menuQuery.setCurrentPage(3);
        menuQuery.setPageSize(5);
        Page<Menu> employeePage = menuRepository.findPageByQuery(menuQuery);
        System.out.println(employeePage.getTotalElements()+"总记录数");/*总记录数*/
        System.out.println(employeePage.getTotalPages()+"总页数");/*总页数*/
        System.out.println(employeePage.getNumber()+1+"当前页数");/*当前页数*/
        System.out.println(employeePage.getNumberOfElements()+"当前页有多少行");/*当前页有多少行*/
        System.out.println(employeePage.getSize()+"当前页显示的行数");/*当前页显示的行数*/
        List<Menu> employeeList = employeePage.getContent();/*获取的集合*/
        System.out.println(employeeList.size()+"集合");

    }

    @Test
    public void test121() throws Exception {
        System.out.println(4/5);
    }

}
