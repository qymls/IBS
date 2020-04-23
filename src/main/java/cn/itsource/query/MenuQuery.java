package cn.itsource.query;

import cn.itsource.domain.Employee;
import cn.itsource.domain.Menu;
import com.github.wenhao.jpa.PredicateBuilder;
import com.github.wenhao.jpa.Specifications;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuQuery extends BaseQuery<Menu> {
    private String name;
    private String time;/*查询的字段*/

    @Override
    public Specification getSpecification() {
        PredicateBuilder<Employee> and = Specifications.and();
        and.eq("parent", 0);
        and.like(StringUtils.isNoneBlank(name), "name", "%" + name + "%");
        if (StringUtils.isNoneBlank(time)) {/*时间处理特殊*/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            if (!",".equals(time)) {/*处理区间段是空，也会有个，号的*/
                String start_time = time.split(",")[0];
                String end_time = time.split(",")[1];
                Date start_data = null;/*需要时间格式*/
                Date end_data = null;
                try {
                    start_data = sdf.parse(start_time);
                    end_data = sdf.parse(end_time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                and.ge("createTime", start_data);
                and.le("createTime", end_data);
            }
        }
        Specification<Employee> specification = and.build();
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
