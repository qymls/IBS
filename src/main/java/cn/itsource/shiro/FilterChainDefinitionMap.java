package cn.itsource.shiro;

import cn.itsource.domain.Permission;
import cn.itsource.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FilterChainDefinitionMap {
    @Autowired
    private IPermissionService permissionService;

    public Map<String, String> create() {
        HashMap<String, String> map = new LinkedHashMap<>();
        map.put("/Admin/gitHubLogin", "anon");
        map.put("/Admin/qqLogin", "anon");
        map.put("/Admin/login", "anon");
        map.put("/Admin/logout", "anon");
        map.put("/Admin/register", "anon");
        map.put("/iview/**", "anon");
       /* map.put("/Admin/Employee/save", "perms[employee:save]");
        map.put("/Admin/Employee/delete", " perms[employee:delete]");
        map.put("/Admin/Employee/findAll", "perms[employee:findALL]");*/
        //拿到所有权限
        List<Permission> permissions = permissionService.findAll();
        //2.遍历权限，拿到权限与资源
        for (Permission permission : permissions) {
            String url = permission.getUrl();//资源
            String sn = permission.getSn();//权限
            //把路径与资源放到拦截中去
            map.put(url, "perms[" + sn + "]");
        }
        map.put("/**", "authc");
        return map;
    }
}
