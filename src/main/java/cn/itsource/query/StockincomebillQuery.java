package cn.itsource.query;

import cn.itsource.domain.Stockincomebill;
import cn.itsource.domain.Stockincomebill;
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
import java.util.Date;

/**
 * (Stockincomebill)Query实体类
 *
 * @author 申林
 * @since 2020-05-09 14:21:59
 */
public class StockincomebillQuery extends BaseQuery<Stockincomebill> {
    private Long supplierId;
    private String time;
    private Long keeperId;
    private Long status;
    private Long depotId;

    @Override
    public Specification getSpecification() {
        PredicateBuilder<Stockincomebill> and = Specifications.and();
        and.eq(supplierId != null && supplierId > 0, "supplier.id", supplierId);
        and.eq(keeperId != null && keeperId > 0, "keeper.id", keeperId);
        and.eq(depotId != null && depotId >= 0, "depot.id", depotId);
        and.eq(status != null && status >= 0, "status", status);
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
                and.ge("vdate", start_data);
                and.lt("vdate", end_data);
            }
        }
        Specification<Stockincomebill> specification = and.build();
        return specification;
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

    public Long getKeeperId() {
        return keeperId;
    }

    public void setKeeperId(Long keeperId) {
        this.keeperId = keeperId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }
}