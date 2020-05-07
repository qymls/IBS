package cn.itsource.query;

import cn.itsource.domain.Purchasebillitem;
import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Purchasebillitem)Query实体类
 *
 * @author 申林
 * @since 2020-05-06 10:28:42
 */
public class PurchasebillitemQuery extends BaseQuery<Purchasebillitem> {
    private Long supplierId;
    private String time;
    private Long buyerId;
    private Integer status;
    private Long productypeId;
    private String groupField;
    private List params = new ArrayList();

    @Override
    public Specification getSpecification() {
        PredicateBuilder<Purchasebillitem> and = Specifications.and();
        and.eq(supplierId != null && supplierId > 0, "bill.supplier.id", supplierId);
        and.eq(buyerId != null && buyerId > 0, "bill.buyer.id", buyerId);
        and.eq(status != null && status >= 0, "bill.status", status);
        and.eq(productypeId != null && productypeId >= 0, "product.producttype.id", productypeId);
        if (StringUtils.isNoneBlank(time)) {/*时间处理特殊*/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!",".equals(time)) {/*处理区间段是空，也会有个，号的*/
                String start_time = time.split(",")[0];
                String end_time = time.split(",")[1];
                Date start_data = null;/*需要时间格式*/
                Date end_data = null;
                try {
                    start_data = sdf.parse(start_time);
                    end_data = DateUtils.addDays(sdf.parse(end_time), 1);/*处理同一天的bug*/
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                and.ge("bill.vdate", start_data);
                and.lt("bill.vdate", end_data);
            }
        }
        Specification<Purchasebillitem> specification = and.build();
        return specification;
    }

    public String getJpql() {/*这里使用自定义的jpql来高级查询*/
        StringBuilder jpql = new StringBuilder(" from Purchasebillitem o");
        if (StringUtils.isNoneBlank(time)) {/*时间处理特殊*/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (!",".equals(time)) {/*处理区间段是空，也会有个，号的*/
                String start_time = time.split(",")[0];
                String end_time = time.split(",")[1];
                Date start_data = null;/*需要时间格式*/
                Date end_data = null;
                try {
                    start_data = sdf.parse(start_time);
                    end_data = DateUtils.addDays(sdf.parse(end_time), 1);/*处理同一天的bug*/
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                jpql.append(" and  o.bill.vdate >= ?");
                jpql.append(" and  o.bill.vdate <= ?");
                params.add(start_data);
                params.add(end_data);
            }
        }
        if (supplierId != null && supplierId > 0) {
            jpql.append(" and o.bill.supplier.id = ?");
            params.add(supplierId);
        }
        if (buyerId != null && buyerId > 0) {
            jpql.append(" and o.bill.buyer.id = ?");
            params.add(buyerId);
        }
        if (status != null && status >= 0) {
            jpql.append(" and o.bill.status = ?");
            params.add(status);
        }
        if (productypeId != null && productypeId > 0) {
            jpql.append(" and o.product.producttype.id = ?");
            params.add(productypeId);
        }
        return jpql.toString().replaceFirst("and", "where");
    }


    @Override
    public Sort getSort() {
        Sort sort = null;
        if (StringUtils.isNoneBlank(this.getOrderFiled())) {
            Sort.Direction direction = Sort.Direction.ASC;
            if ("desc".equalsIgnoreCase(this.getOrderType())) {
                direction = Sort.Direction.DESC;
            }
            Sort.Order order = new Sort.Order(direction, this.getOrderFiled());
            sort = new Sort(order);/*可变参数*/
        }
        return sort;
    }

    @Override
    public Pageable getPageable() {
        Pageable pageable = new PageRequest(this.getCurrentPage(), this.getPageSize(), getSort());
        return pageable;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getProductypeId() {
        return productypeId;
    }

    public void setProductypeId(Long productypeId) {
        this.productypeId = productypeId;
    }

    public List getParams() {
        return params;
    }

    public void setParams(List params) {
        this.params = params;
    }

    public String getGroupField() {
        return groupField;
    }

    public void setGroupField(String groupField) {
        this.groupField = groupField;
    }
}