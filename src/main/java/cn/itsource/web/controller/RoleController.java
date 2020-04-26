package cn.itsource.web.controller;

import cn.itsource.domain.Permission;
import cn.itsource.domain.Role;
import cn.itsource.query.RoleQuery;
import cn.itsource.service.IPermissionService;
import cn.itsource.service.IRoleService;
import com.hazelcast.security.permission.ListPermission;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * (Role)表Controller
 *
 * @author 申林
 * @since 2020-04-26 14:28:46
 */
@Controller
@RequestMapping("Admin/Role")
public class RoleController {
    private IRoleService roleService;
    private IPermissionService permissionService;

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Role> findAll(RoleQuery roleQuery) {
        Page<Role> pageUtil = roleService.findPageByQuery(roleQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                roleService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
            map.put("msg", "操作做成功");
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Role role) {
        roleService.save(role);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "操作做成功");
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Role findOneBeforeUpdate(String action, Long id) {
        Role role = null;
        if ("update".equalsIgnoreCase(action)) {
            role = roleService.findOne(id);
            role.getPermissionList().clear();/*解决nton*/
        }
        return role;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param role
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Role role) {
        roleService.update(role);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "操作做成功");
        return map;
    }

    @RequestMapping("/permission/findAll")
    @ResponseBody
    public List<Permission> findAllPermission() {
        return permissionService.findAll();
    }
}