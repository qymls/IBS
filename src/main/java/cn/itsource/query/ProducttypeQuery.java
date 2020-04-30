package cn.itsource.query;

import cn.itsource.domain.Employee;
import cn.itsource.domain.Producttype;
import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * (Producttype)Query实体类
 *
 * @author 申林
 * @since 2020-04-30 11:50:13
 */
public class ProducttypeQuery extends BaseQuery<Producttype> {
    private String name;

    @Override
    public Specification getSpecification() {
        PredicateBuilder<Producttype> and = Specifications.and();
        and.eq("firstid", 0);
        and.like(StringUtils.isNoneBlank(name), "name", "%" + name + "%");
        Specification<Producttype> specification = and.build();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}