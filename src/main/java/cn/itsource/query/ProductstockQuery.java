package cn.itsource.query;

import java.math.BigDecimal;
import java.util.Date;

import cn.itsource.domain.Productstock;
import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * (Productstock)Query实体类
 *
 * @author 申林
 * @since 2020-05-09 14:21:58
 */
public class ProductstockQuery extends BaseQuery<Productstock> {
    private Long depotId;

    @Override
    public Specification getSpecification() {
        Specification<Productstock> specification = Specifications.<Productstock>and()
                .eq(depotId != null && depotId >= 0, "depot.id", depotId)
                .build();
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

    public Long getDepotId() {
        return depotId;
    }

    public void setDepotId(Long depotId) {
        this.depotId = depotId;
    }
}