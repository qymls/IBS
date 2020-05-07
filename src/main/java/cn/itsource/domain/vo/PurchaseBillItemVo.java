package cn.itsource.domain.vo;

import cn.itsource.domain.Purchasebillitem;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseBillItemVo {
    private Long id; //编号
    private String supplierName; //供应商名称
    private String buyerName; //采购员名称
    private String productName; //产品名称
    private String productType; //产品分类
    private Date vdate; //交易时间
    private BigDecimal num; //采购数量
    private BigDecimal price; //价格
    private BigDecimal amount; //小计 = 价格*数量
    private Integer status;//状态
    private String groupField = ""; //分组字段

    public PurchaseBillItemVo() {
    }

    public PurchaseBillItemVo(Purchasebillitem item) {
        this.id = item.getId();
        this.supplierName = item.getBill().getSupplier().getName();
        this.buyerName = item.getBill().getBuyer().getUsername();
        this.productName = item.getProduct().getName();
        this.productType = item.getProduct().getProducttype().getName();
        this.vdate = item.getBill().getVdate();
        this.num = item.getNum();
        this.price = item.getPrice();
        this.amount = item.getAmount();
        this.status = item.getBill().getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }
}
