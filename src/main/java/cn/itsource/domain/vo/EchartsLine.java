package cn.itsource.domain.vo;

import java.math.BigDecimal;

public class EchartsLine {
    private String vateformat;
    private BigDecimal totalprice;
    private BigDecimal totalnum;
    private String productname;

    public String getVateformat() {
        return vateformat;
    }

    public void setVateformat(String vateformat) {
        this.vateformat = vateformat;
    }

    public BigDecimal getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public BigDecimal getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(BigDecimal totalnum) {
        this.totalnum = totalnum;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
