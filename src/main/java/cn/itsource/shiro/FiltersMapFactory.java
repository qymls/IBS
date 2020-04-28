package cn.itsource.shiro;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义权限控制的实例工厂
 */
public class FiltersMapFactory {
    public Map<String, Filter> create() {
        Map<String, Filter> map = new HashMap<>();
        map.put("customPerms", new CustomPermissionsAuthorizationFilter());
        return map;
    }
}
