package cn.itsource.web.controller;

import cn.itsource.domain.Department;
import cn.itsource.domain.Employee;
import cn.itsource.query.EmployeeQuery;
import cn.itsource.service.IDepartmentService;
import cn.itsource.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("Admin/Employee")
public class EmployeeController {
    private IEmployeeService employeeService;
    private IDepartmentService departmentService;

    @Autowired
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Employee> findAll(EmployeeQuery employeeQuery) {
        Page<Employee> pageUtil = employeeService.findPageByQuery(employeeQuery);
        return pageUtil;
    }

    @RequestMapping("/department/findAll")
    @ResponseBody
    public List<Department> departmentfindAll() {
        return departmentService.findAll();
    }


    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                employeeService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
            map.put("msg", "操作做成功");
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Employee employee) {
        System.out.println(employee);
        employeeService.save(employee);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "操作做成功");
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Employee findOneBeforeUpdate(String action, Long id) {
        Employee employee = null;
        if ("update".equalsIgnoreCase(action)) {
            employee = employeeService.findOne(id);
            employee.setDepartment(null);/*department 脱离持久化状态*/
        }
        return employee;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param employee
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Employee employee) {
        employeeService.update(employee);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "操作做成功");
        return map;
    }
}
