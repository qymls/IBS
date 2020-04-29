package cn.itsource.repository;

import cn.itsource.domain.Department;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (Department)表数据库访问层
 *
 * @author 申林
 * @since 2020-04-28 12:38:19
 */
public interface IDepartmentRepository extends IBaseRepository<Department, Long> {
    @Query("select d from Department d where d.name=?1")
    List<Department> findByName(String name);

}