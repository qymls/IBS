package cn.itsource.query;

import cn.itsource.domain.Department;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class DepartmentQuery extends BaseQuery<Department> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Specification getSpecification() {
        return null;
    }

    @Override
    public Pageable getPageable() {
        return null;
    }

    @Override
    public Sort getSort() {
        return null;
    }
}
