package cn.itsource.web.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.itsource.domain.Department;
import cn.itsource.domain.Employee;
import cn.itsource.service.IDepartmentService;
import cn.itsource.service.IEmployeeService;
import cn.itsource.shiro.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/Admin/Import")
public class ImportController {
    private IDepartmentService departmentService;
    private IEmployeeService employeeService;

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/index")
    public String index() {
        return "";
    }

    @RequestMapping("/importEmployeeData")
    @ResponseBody
    public List<Employee> importData(MultipartFile multipartFile) throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<Employee> list = ExcelImportUtil.importExcel(
                multipartFile.getInputStream(),
                Employee.class, params);
        for (Employee employee : list) {
            employee.setPassword(MD5Utils.getMD5Password(employee.getUsername() + "123")); //默认密码用户名
            if (employee.getDepartment() != null) {
                Department department = departmentService.findByName(employee.getDepartment().getName());
                employee.setDepartment(department);
            }
           /* employeeService.save(employee);*/
            employeeList.add(employee);
        }
        return employeeList;
    }

}
