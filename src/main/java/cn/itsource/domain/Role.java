package cn.itsource.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (Role)实体类
 *
 * @author 申林
 * @since 2020-04-26 14:28:46
 */
@Entity
public class Role extends BaseDomain {

    private String name;

    private String sn;

    @ManyToMany(fetch = FetchType.LAZY)/*多对多配置*/
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissionList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}