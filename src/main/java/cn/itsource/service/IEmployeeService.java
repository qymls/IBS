package cn.itsource.service;

import cn.itsource.domain.Employee;

import java.util.List;

public interface IEmployeeService extends IBaseService<Employee, Long> {
    Employee findByUsername(String username);

    List<Employee> findEmployeeByDepartmenttName(String deptName);
}
