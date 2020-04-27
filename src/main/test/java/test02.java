import cn.itsource.domain.Employee;
import cn.itsource.repository.IEmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

/**
 * 高级查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dataSource.xml")
public class test02 {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Test
    public void test() throws Exception {
        Specification<Employee> specification = new Specification<Employee>() {
            /**
             * @param root 获取要作为高级查询的字段名称
             * @param criteriaQuery 多个条件使用and或or
             * @param criteriaBuilder 运算符
             * @return
             */
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path username = root.get("username");
                Predicate predicate = criteriaBuilder.like(username, "%role%");
                return predicate;
            }
        };
        List<Employee> list = employeeRepository.findAll(specification);
        System.out.println(list);

    }

    @Test
    public void testMoreWhere() throws Exception {
        Specification<Employee> specification = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path username = root.get("username");
                Predicate predicate1 = criteriaBuilder.like(username, "%role%");
                Path age = root.get("age");
                Predicate predicate2 = criteriaBuilder.ge(age, 25);
                CriteriaQuery where = criteriaQuery.where(predicate1, predicate2);
                return where.getRestriction();
            }
        };
        List<Employee> list = employeeRepository.findAll(specification);
        Employee one = employeeRepository.findOne(specification);
        System.out.println(one);

    }

    @Test
    public void testMoreWherePage() throws Exception {
        Specification<Employee> specification = new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path username = root.get("username");
                Predicate predicate1 = criteriaBuilder.like(username, "%role%");
                Path age = root.get("age");
                Predicate predicate2 = criteriaBuilder.ge(age, 25);
                CriteriaQuery where = criteriaQuery.where(predicate1, predicate2);
                return where.getRestriction();
            }
        };
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC,"id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC,"age");
        Sort sort = new Sort(order1,order2);/*可变参数*/
        PageRequest pageRequest = new PageRequest(0,10,sort);
        Page<Employee> employeePage = employeeRepository.findAll(specification,pageRequest);
        System.out.println(employeePage.getContent());

    }

    @Test
    public void testss() throws Exception {
    String str = "c";
        String[] split = str.split(";");
        System.out.println(Arrays.toString(split));
    }

}
