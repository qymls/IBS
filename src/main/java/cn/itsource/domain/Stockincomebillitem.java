package cn.itsource.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 * (入库单明细)实体类
 *
 * @author 申林
 * @since 2020-05-09 14:22:00
 */
@Entity
public class Stockincomebillitem extends BaseDomain {
    private BigDecimal price;
    private BigDecimal num;
    private BigDecimal amount;
    private String descs;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;// 多对一,非空
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bill_id")
    @JSONField(serialize = false) //生成json的时候忽略这个属性
    private Stockincomebill bill;// 组合关系,非空

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Stockincomebill getBill() {
        return bill;
    }

    public void setBill(Stockincomebill bill) {
        this.bill = bill;
    }
}