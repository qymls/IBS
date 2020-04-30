package cn.itsource.web.controller;

import cn.itsource.domain.Department;
import cn.itsource.query.DepartmentQuery;
import cn.itsource.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * (Department)表Controller
 *
 * @author 申林
 * @since 2020-04-28 12:38:19
 */
@Controller
@RequestMapping("Admin/Department")
public class DepartmentController {
    private IDepartmentService departmentService;

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/department/department";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Department> findAll(DepartmentQuery departmentQuery) {
        Page<Department> pageUtil = departmentService.findPageByQuery(departmentQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                departmentService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Department department) {
        departmentService.save(department);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Department findOneBeforeUpdate(String action, Long id) {
        Department department = null;
        if ("update".equalsIgnoreCase(action)) {
            department = departmentService.findOne(id);
            //department.setDepartment(null);/*department 脱离持久化状态*/
        }
        return department;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param department
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Department department) {
        departmentService.update(department);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}