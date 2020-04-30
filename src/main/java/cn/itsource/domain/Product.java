package cn.itsource.domain;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 * (Product)实体类
 *
 * @author 申林
 * @since 2020-04-30 11:50:14
 */
@Entity
public class Product extends BaseDomain {

    private String name;

    private String color;

    private String pic;

    private String smallpic;

    private BigDecimal costprice;

    private BigDecimal saleprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    private Producttype producttype;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Systemdictionarydetail unit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Systemdictionarydetail brand;

    public Producttype getProducttype() {
        return producttype;
    }

    public void setProducttype(Producttype producttype) {
        this.producttype = producttype;
    }

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

    public Systemdictionarydetail getUnit() {
        return unit;
    }

    public void setUnit(Systemdictionarydetail unit) {
        this.unit = unit;
    }

    public Systemdictionarydetail getBrand() {
        return brand;
    }

    public void setBrand(Systemdictionarydetail brand) {
        this.brand = brand;
    }
}