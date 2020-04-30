package cn.itsource.query;

import cn.itsource.domain.Systemdictionarytype;
import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * (Systemdictionarytype)Query实体类
 *
 * @author 申林
 * @since 2020-04-30 11:50:11
 */
public class SystemdictionarytypeQuery extends BaseQuery<Systemdictionarytype>{
    private String name;
    @Override
    public Specification getSpecification() {
        Specification<Systemdictionarytype> specification = Specifications.<Systemdictionarytype>and()
        .like(StringUtils.isNoneBlank(name), "name", "%" + name + "%")
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}