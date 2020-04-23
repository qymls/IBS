package cn.itsource.domain;
import java.math.BigDecimal;
import javax.persistence.*;
/**
 * (Product)实体类
 *
 * @author 申林
 * @since 2020-04-23 20:36:44
 */
@Entity
public class Product extends BaseDomain{
    
    private String name;
    
    private String color;
    
    private String pic;
    
    private String smallpic;
    
    private BigDecimal costprice;
    
    private BigDecimal saleprice;
    
    private Long typesId;
    
    private Long unitId;
    
    private Long brandId;

        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
        
    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
        
    public String getSmallpic() {
        return smallpic;
    }

    public void setSmallpic(String smallpic) {
        this.smallpic = smallpic;
    }
        
    public BigDecimal getCostprice() {
        return costprice;
    }

    public void setCostprice(BigDecimal costprice) {
        this.costprice = costprice;
    }
        
    public BigDecimal getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }
        
    public Long getTypesId() {
        return typesId;
    }

    public void setTypesId(Long typesId) {
        this.typesId = typesId;
    }
        
    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }
        
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

}