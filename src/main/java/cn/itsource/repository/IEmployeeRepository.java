package cn.itsource.repository;

import cn.itsource.domain.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEmployeeRepository extends IBaseRepository<Employee,Long>{
    List<Employee> findAllByUsernameLike(String username);

    Employee findByUsernameAndPassword(String username, String password);

    @Query("select e from Employee e where e.username = ?1 and e.password = ?2")
    Employee findByNameAndPwd(String username, String password);

    @Query(nativeQuery = true, value = "select * from employee  where username = ?1 and password = ?2")
    Employee findByNameAndPwdNative(String username, String password);

}
