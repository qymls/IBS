package cn.itsource.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * (入库单)实体类
 *
 * @author 申林
 * @since 2020-05-09 14:21:59
 */
@Entity
public class Stockincomebill extends BaseDomain {

    private Date vdate;// 交易时间
    private BigDecimal totalamount;
    private BigDecimal totalnum;
    private Date inputtime = new Date();
    private Date auditortime;
    /**
     * 0待审,1已审，2作废
     */
    private Integer status = 0;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;// 多对一，非空
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_id")
    private Employee auditor;// 多对一，可以为空
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inputUser_id")
    private Employee inputUser;// 多对一，非空
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "keeper_id")
    private Employee keeper;// 多对一，非空
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "depot_id")
    private Depot depot;// 多对一，非空
    // 一般组合关系使用List
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Stockincomebillitem> billitems = new ArrayList<>();

    public Date getVdate() {
        return vdate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")/*必须填写*/
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }

    public BigDecimal getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(BigDecimal totalnum) {
        this.totalnum = totalnum;
    }

    public Date getInputtime() {
        return inputtime;
    }

    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    public Date getAuditortime() {
        return auditortime;
    }

    public void setAuditortime(Date auditortime) {
        this.auditortime = auditortime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
    }

    public Employee getInputUser() {
        return inputUser;
    }

    public void setInputUser(Employee inputUser) {
        this.inputUser = inputUser;
    }

    public Employee getKeeper() {
        return keeper;
    }

    public void setKeeper(Employee keeper) {
        this.keeper = keeper;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public List<Stockincomebillitem> getBillitems() {
        return billitems;
    }

    public void setBillitems(List<Stockincomebillitem> billitems) {
        this.billitems = billitems;
    }
}