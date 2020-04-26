package cn.itsource.service;

import cn.itsource.domain.Employee;

public interface IEmployeeService extends IBaseService<Employee, Long> {
    Employee findByUsername(String username);
}
