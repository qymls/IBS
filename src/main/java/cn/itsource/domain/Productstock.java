package cn.itsource.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

/**
 * (库存)实体类
 *
 * @author 申林
 * @since 2020-05-09 14:21:58
 */
@Entity
public class Productstock extends BaseDomain {
    // 产品id 入库时间 入库数量 价格 小计
    // 100 03 100 10 1000
    // 100 05 200 30 6000
    // 加权平均法=总金额/总数量
    // 100 05 300 (1000+6000)/300 7000
    private BigDecimal num;// 当前仓库的产品的数量
    private BigDecimal amount;// 当前仓库的产品的小计
    private BigDecimal price;// 当前仓库的产品的价格
    private Date incomedate;// 入库时间
    private Boolean warning = true;// 库存过多，过少自动发出库存预警
    private BigDecimal topnum = new BigDecimal(100);// 最大库存量
    private BigDecimal bottomnum = new BigDecimal(50);// 最小库存量
    // 同时唯一(同一个产品，同一个仓库 -> 才进行累加)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;// 多对一,非空
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "depot_id")
    private Depot depot;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getIncomedate() {
        return incomedate;
    }

    public void setIncomedate(Date incomedate) {
        this.incomedate = incomedate;
    }

    public Boolean getWarning() {
        return warning;
    }

    public void setWarning(Boolean warning) {
        this.warning = warning;
    }

    public BigDecimal getTopnum() {
        return topnum;
    }

    public void setTopnum(BigDecimal topnum) {
        this.topnum = topnum;
    }

    public BigDecimal getBottomnum() {
        return bottomnum;
    }

    public void setBottomnum(BigDecimal bottomnum) {
        this.bottomnum = bottomnum;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }
}