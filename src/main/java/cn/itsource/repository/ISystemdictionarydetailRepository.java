package cn.itsource.repository;

import cn.itsource.domain.Systemdictionarydetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * (Systemdictionarydetail)表数据库访问层
 *
 * @author 申林
 * @since 2020-04-30 11:50:12
 */
public interface ISystemdictionarydetailRepository extends IBaseRepository<Systemdictionarydetail, Long> {
    @Query("select sdd from Systemdictionarydetail sdd where sdd.types.sn=?1")
    List<Systemdictionarydetail> findBySn(String sn);
}