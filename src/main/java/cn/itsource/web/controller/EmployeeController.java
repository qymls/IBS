package cn.itsource.web.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.itsource.domain.Department;
import cn.itsource.domain.Employee;
import cn.itsource.domain.Role;
import cn.itsource.query.EmployeeQuery;
import cn.itsource.service.IDepartmentService;
import cn.itsource.service.IEmployeeService;
import cn.itsource.service.IRoleService;
import cn.itsource.service.picture.IPictureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("Admin/Employee")
public class EmployeeController {
    private IEmployeeService employeeService;
    private IDepartmentService departmentService;
    private IPictureService pictureService;
    private IRoleService roleService;

    @Autowired
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setPictureService(IPictureService pictureService) {
        this.pictureService = pictureService;
    }

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/employee/employee";
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

    @RequestMapping("/role/findAll")
    @ResponseBody
    public List<Role> rolefindAll() {
        return roleService.findAll();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids, HttpServletRequest req) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                Employee employee = employeeService.findOne(id);/*先删除图片*/
                employeeService.delete(id);
                pictureService.deleteImage(employee.getHeadImage(), req);/*删除对应的图片*/
            }
            map = new HashMap<>();
            map.put("success", true);
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
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Employee findOneBeforeUpdate(String action, Long id) {
        Employee employee = null;
        if ("update".equalsIgnoreCase(action)) {
            employee = employeeService.findOne(id);
            employee.setDepartment(null);/*department 脱离持久化状态*/
            employee.getRoleList().clear();/*脱离*/
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
        return map;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(String headImage, MultipartFile multipartFile, HttpServletRequest req) {
        return pictureService.saveImage(multipartFile, headImage, req);
    }

    @RequestMapping("/deleteImg")
    @ResponseBody
    public Boolean deleteImg(String path, HttpServletRequest req) {
        return pictureService.deleteImage(path, req);
    }

    /**
     * 导出数据
     * ModelMap的键值会储存在当前请求作用域中
     * 会返回一个字符串，继续处理
     *
     * @return
     */
    @RequestMapping("/exportEmployeeData")
    public String exportEmployeeData(EmployeeQuery query, ModelMap map, HttpServletRequest req) {
        List<Employee> list = employeeService.findByQuery(query);
        //搞定路径问题
        String realPath = req.getServletContext().getRealPath("/");
        list.forEach(e -> e.setHeadImage(realPath + e.getHeadImage()));
        ExportParams params = new ExportParams("员工数据", "员工列表", ExcelType.XSSF);
        //params.setFreezeCol(5); //设置冻结列数
        map.put(NormalExcelConstants.DATA_LIST, list); // 数据集合
        map.put(NormalExcelConstants.CLASS, Employee.class);//导出实体
        map.put(NormalExcelConstants.PARAMS, params);//参数
        map.put(NormalExcelConstants.FILE_NAME, "员工信息");//文件名称
        return NormalExcelConstants.EASYPOI_EXCEL_VIEW;

    }
}