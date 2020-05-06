package cn.itsource.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * (采购单)实体类
 *
 * @author 申林
 * @since 2020-05-06 10:28:40
 */
@Entity
public class Purchasebill extends BaseDomain {

    private Date vdate;// 采购时间 -> 需要录入(时间set的时候加上@DateTimeFormat(pattern = "yyyy-MM-dd"))
    private BigDecimal totalamount; //总金额 -> 明细计算
    private BigDecimal totalnum; //总数量 -> 明细计算
    private Date inputtime = new Date(); //录入时间 ->当前系统时间
    private Date auditortime; //审核时间 -> 可以为空,审核时自己生成
    /**
     * 0待审,1已审，2作废
     */
    private Integer status = 0; //单据状态 -> 默认待审

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;// 多对一，非空 供应商(需要选择)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditor_id")
    private Employee auditor;// 多对一，审核员可以为空

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inputUser_id")
    private Employee inputUser;// 多对一，非空 录入人 -> 登录用户就是录入人

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private Employee buyer;// 多对一，非空 采购员 -> 需要

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Purchasebillitem> billitems = new ArrayList<Purchasebillitem>();

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

    public Employee getBuyer() {
        return buyer;
    }

    public void setBuyer(Employee buyer) {
        this.buyer = buyer;
    }

    public List<Purchasebillitem> getBillitems() {
        return billitems;
    }

    public void setBillitems(List<Purchasebillitem> billitems) {
        this.billitems = billitems;
    }
}