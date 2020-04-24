package cn.itsource.domain;
import javax.persistence.*;
/**
 * (Resource)实体类
 *
 * @author 申林
 * @since 2020-04-24 18:41:39
 */
@Entity
public class Resource extends BaseDomain{
    
    private String name;
    
    private String url;
    
    private String descs;

        
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

}