package cn.itsource.repository;

import cn.itsource.domain.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEmployeeRepository extends IBaseRepository<Employee,Long>{

}
