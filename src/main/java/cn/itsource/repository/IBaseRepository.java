package cn.itsource.repository;

import cn.itsource.query.BaseQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * 定义泛型
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean/*不创建对象*/
public interface IBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    //根据Query拿到分页对象(分页),支持高级查询，排序
    Page findPageByQuery(BaseQuery<T> baseQuery);

    //根据Query拿到对应的所有数据(不分页),支持高级查询，排序
    List<T> findByQuery(BaseQuery<T> baseQuery);

    //根据jpql与对应的参数拿到数据
    List findByJpql(String jpql, Object... values);

    //原生sql查询
    List findByNaciveSql(String sql, Class tClass, Object... values);

    //执行原生sql
    void createNativeQuery(String sql, Object... values);

}
