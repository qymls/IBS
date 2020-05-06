package cn.itsource.domain;
import javax.persistence.*;
/**
 * (Supplier)实体类
 *
 * @author 申林
 * @since 2020-05-06 10:28:42
 */
@Entity
public class Supplier extends BaseDomain{
    
    private String name;

        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}