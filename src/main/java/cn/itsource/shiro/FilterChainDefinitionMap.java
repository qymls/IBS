package cn.itsource.shiro;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FilterChainDefinitionMap {
    public Map<String, String> create() {
        HashMap<String, String> map = new LinkedHashMap<>();
        map.put("/Admin/gitHubLogin", "anon");
        map.put("/Admin/qqLogin", "anon");
        map.put("/Admin/login", "anon");
        map.put("/Admin/logout", "anon");
        map.put("/Admin/register", "anon");
        map.put("/iview/**", "anon");
        /*map.put("/Admin/Employee/save", "perms[employee:save]");
        map.put("/Admin/Employee/delete", " perms[employee:delete]");
        map.put("/Admin/Employee/update", "perms[employee:update]");*/
        map.put("/**", "authc");
        return map;
    }
}
