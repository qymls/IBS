package cn.itsource.repository;

import cn.itsource.domain.Producttype;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (Producttype)表数据库访问层
 *
 * @author 申林
 * @since 2020-04-30 11:50:13
 */
public interface IProducttypeRepository extends IBaseRepository<Producttype, Long> {
    @Query(nativeQuery = true, value = "select * from producttype where firstid = 0")
    List<Producttype> getStairProducttype();

}