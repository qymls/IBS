package cn.itsource.repository;

import cn.itsource.domain.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEmployeeRepository extends IBaseRepository<Employee, Long> {
    @Query("select e from Employee e where e.department.name = ?1")
    List<Employee> findEmployeeByDepartmenttName(String deptName);

}
