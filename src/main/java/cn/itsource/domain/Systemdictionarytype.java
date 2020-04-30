package cn.itsource.domain;
import javax.persistence.*;
/**
 * (Systemdictionarytype)实体类
 *
 * @author 申林
 * @since 2020-04-30 11:50:11
 */
@Entity
public class Systemdictionarytype extends BaseDomain{
    
    private String sn;
    
    private String name;

        
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}