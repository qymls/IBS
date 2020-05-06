package cn.itsource.service.impl;

import cn.itsource.domain.Employee;
import cn.itsource.repository.IEmployeeRepository;
import cn.itsource.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long> implements IEmployeeService {

    private IEmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findByUsername(String username) {
        String jpql = "select e from Employee e where e.username=?1";
        List<Employee> employeeList = employeeRepository.findByJpql(jpql, username);
        if (employeeList.size() > 0) {
            return employeeList.get(0);
        }
        return null;
    }

    @Override
    public List<Employee> findEmployeeByDepartmenttName(String deptName) {
        return employeeRepository.findEmployeeByDepartmenttName(deptName);
    }
}
