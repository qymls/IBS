package cn.itsource.service.impl;

import cn.itsource.domain.Employee;
import cn.itsource.repository.IEmployeeRepository;
import cn.itsource.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long> implements IEmployeeService {

    private IEmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

}
