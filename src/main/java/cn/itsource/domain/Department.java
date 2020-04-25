package cn.itsource.domain;
import javax.persistence.*;
/**
 * (Department)实体类
 *
 * @author 申林
 * @since 2020-04-25 10:14:22
 */
@Entity
public class Department extends BaseDomain{
    
    private String name;

        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}