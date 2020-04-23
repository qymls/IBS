package cn.itsource.service.impl;

import cn.itsource.query.BaseQuery;
import cn.itsource.repository.IBaseRepository;
import cn.itsource.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional(readOnly = true)
public class BaseServiceImpl<T, ID extends Serializable> implements IBaseService<T, ID> {
    @Autowired
    private IBaseRepository<T, ID> baseRepository;

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public T findOne(ID id) {
        return baseRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(ID id) {
        baseRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(T t) {
        baseRepository.save(t);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(T t) {
        baseRepository.save(t);
    }


    @Override
    public Page findPageByQuery(BaseQuery baseQuery) {
        return baseRepository.findPageByQuery(baseQuery);
    }

    @Override
    public List<T> findByQuery(BaseQuery baseQuery) {
        return baseRepository.findByQuery(baseQuery);
    }

    @Override
    public List findByJpql(String jpql, Object... values) {
        return baseRepository.findByJpql(jpql,values);
    }
}
