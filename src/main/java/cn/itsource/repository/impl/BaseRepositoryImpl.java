package cn.itsource.repository.impl;

import cn.itsource.query.BaseQuery;
import cn.itsource.repository.IBaseRepository;
import cn.itsource.util.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements IBaseRepository<T, ID> {

    private EntityManager entityManager;

    /*父类中没有无参构造，所以必须创建一个*/
    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    /**
     * 高级分页查询
     *
     * @param baseQuery
     * @return
     */
    @Override
    public Page findPageByQuery(BaseQuery<T> baseQuery) {/*先查询一次，然后纠错*/
        Page page = super.findAll(baseQuery.getSpecification(), baseQuery.getPageable());
        if (page.getTotalElements() != 0) {
            PageUtil<T> pageUtil = new PageUtil<T>(page.getNumber() + 1, page.getSize(), (int) page.getTotalElements());
            baseQuery.setCurrentPage(pageUtil.getCurrentPage());/*纠正page和pagesize的可能的错误*/
            baseQuery.setPageSize(pageUtil.getPageSize());
            page = super.findAll(baseQuery.getSpecification(), baseQuery.getPageable());
        }
        return page;
    }

    /**
     * @param baseQuery
     * @return
     */
    @Override
    public List<T> findByQuery(BaseQuery<T> baseQuery) {
        return super.findAll(baseQuery.getSpecification(), baseQuery.getSort());
    }

    @Override
    public List findByJpql(String jpql, Object... values) {
        Query query = entityManager.createQuery(jpql);
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }

        return query.getResultList();
    }

    @Override
    public List findByNaciveSql(String sql, Class tClass, Object... values) {
        Query query = entityManager.createNativeQuery(sql, tClass.getClass());
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }

        return query.getResultList();
    }

    @Override
    public void createNativeQuery(String sql, Object... values) {
        Query query = entityManager.createNativeQuery(sql);
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i + 1, values[i]);
            }
        }
        query.executeUpdate();
    }

}
