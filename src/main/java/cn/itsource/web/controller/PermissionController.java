package cn.itsource.web.controller;

import cn.itsource.domain.Permission;
import cn.itsource.query.PermissionQuery;
import cn.itsource.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
/**
 * (Permission)表Controller
 *
 * @author 申林
 * @since 2020-04-28 13:06:01
 */
@Controller
@RequestMapping("Admin/Permission")
public class PermissionController {
    private IPermissionService permissionService;

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Permission> findAll(PermissionQuery permissionQuery) {
        Page<Permission> pageUtil = permissionService.findPageByQuery(permissionQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                permissionService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Permission permission) {
        permissionService.save(permission);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Permission findOneBeforeUpdate(String action, Long id) {
        Permission permission = null;
        if ("update".equalsIgnoreCase(action)) {
            permission = permissionService.findOne(id);
            //permission.setDepartment(null);/*department 脱离持久化状态*/
        }
        return permission;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param permission
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Permission permission) {
        permissionService.update(permission);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}