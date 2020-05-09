package cn.itsource.domain;
import java.math.BigDecimal;
import javax.persistence.*;
/**
 * (仓库)实体类
 *
 * @author 申林
 * @since 2020-05-09 14:21:55
 */
@Entity
public class Depot extends BaseDomain{
    
    private String name;
    
    private BigDecimal maxcapacity;
    
    private BigDecimal currentcapacity;
    
    private BigDecimal totalamount;

        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    public BigDecimal getMaxcapacity() {
        return maxcapacity;
    }

    public void setMaxcapacity(BigDecimal maxcapacity) {
        this.maxcapacity = maxcapacity;
    }
        
    public BigDecimal getCurrentcapacity() {
        return currentcapacity;
    }

    public void setCurrentcapacity(BigDecimal currentcapacity) {
        this.currentcapacity = currentcapacity;
    }
        
    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

}