package cn.itsource.domain;
import javax.persistence.*;
/**
 * (Role)实体类
 *
 * @author 申林
 * @since 2020-04-23 21:39:52
 */
@Entity
public class Role extends BaseDomain{
    
    private String name;
    
    private String sn;

        
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

}