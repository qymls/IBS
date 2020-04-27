package cn.itsource.service;

import cn.itsource.query.BaseQuery;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T, ID extends Serializable> {
    List<T> findAll();

    T findOne(ID id);

    void delete(ID id);

    void save(T t);

    void update(T t);


    Page findPageByQuery(BaseQuery baseQuery);

    //根据Query拿到对应的所有数据(不分页)
    List<T> findByQuery(BaseQuery baseQuery);

    //根据jpql与对应的参数拿到数据
    List findByJpql(String jpql, Object... values);

    //原生sql查询
    List findByNaciveSql(String sql, Class tClass, Object... values);

    //执行原生sql
    void createNativeQuery(String sql, Object... values);

}
