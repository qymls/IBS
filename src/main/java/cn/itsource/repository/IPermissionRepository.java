package cn.itsource.repository;

import cn.itsource.domain.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * (Permission)表数据库访问层
 *
 * @author 申林
 * @since 2020-04-26 13:55:47
 */
public interface IPermissionRepository extends IBaseRepository<Permission, Long> {
    @Query("select distinct p.sn from Employee e  join e.roleList r join r.permissionList p where e.id=?1")
    Set<String> findPermissionsByID(Long id);

    @Query("select p from Permission p where p.menu.id =?1")
    Set<Permission> findPermissionsByMenu(Long id);
}