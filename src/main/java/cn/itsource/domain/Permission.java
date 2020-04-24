package cn.itsource.domain;
import javax.persistence.*;
/**
 * (Permission)实体类
 *
 * @author 申林
 * @since 2020-04-24 18:41:36
 */
@Entity
public class Permission extends BaseDomain{
    
    private String name;
    
    private String url;
    
    private String descs;
    
    private String sn;
    @Column(name = "menu_id")
    private Long menuId;

        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
        
    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }
        
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
        
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}