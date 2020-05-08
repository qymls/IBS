package cn.itsource.web.controller;

import cn.itsource.domain.Menu;
import cn.itsource.domain.Permission;
import cn.itsource.domain.Role;
import cn.itsource.query.PermissionQuery;
import cn.itsource.query.RoleQuery;
import cn.itsource.service.IMenuService;
import cn.itsource.service.IPermissionService;
import cn.itsource.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
    private IMenuService menuService;

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setMenuService(IMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/role/role";
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
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Role role) {
        roleService.save(role);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
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
        return map;
    }

    @RequestMapping("permission/findPageByQuery")
    @ResponseBody
    public Page<Permission> findAll(PermissionQuery permissionQuery) {
        Page<Permission> pageUtil = permissionService.findPageByQuery(permissionQuery);
        return pageUtil;
    }

    @RequestMapping("/Menu/findAll")
    @ResponseBody
    public List<Menu> findAllMenu() {
        return menuService.findAll();
    }

    @RequestMapping("/Menu/newTreeDate")
    @ResponseBody
    public List<Menu> findAllMenunewTreeDate(Long[] ids) {
        return menuService.findAllMenunewTreeDate(ids);
    }

    @RequestMapping("/Menu/findAllRolePermissionMenuByRoleId")
    @ResponseBody
    public List<Menu> findAllRolePermissionMenuByRoleId(Long id) {
        return menuService.findAllRolePermissionMenuByRoleId(id);
    }

    /**
     * 角色直接配置菜单，保存时的方法
     *
     * @param ids
     * @return
     */
    @RequestMapping("/Menu/getLastMenuByRoleSave")
    @ResponseBody
    public HashMap<Object, Object> getLastMenuByRoleSave(Long[] ids, Long roleid) {
        List<Menu> byRoleSave = menuService.getLastMenuByRoleSave(ids);
        Role role = roleService.findOne(roleid);
        role.getPermissionList().clear();/*清空，配置的菜单都只有idnex权限*/
        for (Menu menu : byRoleSave) {
            String[] strings = menu.getUrl().split("/");/*根据菜单的url判断名称拼接*/
            String permisionUrldefault = strings[strings.length - 2];
            String permisionSndefault = String.valueOf(permisionUrldefault.charAt(0)).toLowerCase() + permisionUrldefault.substring(1);
            Set<Permission> permissionsByMenu = permissionService.findPermissionsByMenu(menu.getId());
            if (permissionsByMenu.size() > 0) {/*判断该菜单是否需要权限，如果没有就去添加一下权限*/
                boolean hasIndexPermiss = false;
                Permission newpermission = null;
                for (Permission byMenu : permissionsByMenu) {
                    if (byMenu.getUrl().equals("/Admin/" + permisionUrldefault + "/index")) {/*如果存在index权限那么直接设置即可*/
                        newpermission = byMenu;
                        hasIndexPermiss = true;
                    }
                }
                if (hasIndexPermiss && newpermission != null) {/*permissiom中有这个菜单的index权限*/
                    if (!role.getPermissionList().contains(newpermission)) {/*不重复添加*/
                        role.getPermissionList().add(newpermission);
                        roleService.update(role);
                    }
                } else {/*没有indexx权限的，添加一个*/
                    Permission permissionTemp = new Permission();
                    permissionTemp.setName(menu.getName() + "页面权限");
                    permissionTemp.setUrl("/Admin/" + permisionUrldefault + "/index");
                    permissionTemp.setSn(permisionSndefault + ":" + "index");
                    permissionTemp.setMenu(menu);
                    permissionService.save(permissionTemp);
                    role.getPermissionList().add(permissionTemp);/*给role添加这一个权限*/
                    roleService.update(role);/*保存*/
                }
            } else {/*为空就添加一个页面权限即可*/
                Permission permissionTemp = new Permission();
                permissionTemp.setName(menu.getName() + "页面权限");
                permissionTemp.setUrl("/Admin/" + permisionUrldefault + "/index");
                permissionTemp.setSn(permisionSndefault + ":" + "index");
                permissionTemp.setMenu(menu);
                permissionService.save(permissionTemp);
                role.getPermissionList().add(permissionTemp);/*给role添加这一个权限*/
                roleService.update(role);/*保存*/

            }
        }
        roleService.update(role);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

}