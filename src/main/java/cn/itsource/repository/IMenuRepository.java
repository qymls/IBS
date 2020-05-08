package cn.itsource.repository;

import cn.itsource.domain.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMenuRepository extends IBaseRepository<Menu, Long> {

    @Query("select m from Menu m where m.name = ?1")
    List<Menu> findByName(String name);

    @Query(nativeQuery = true, value = "select * from menu where firstmenuid = 0")
    List<Menu> getStairMenu();

    @Query("select distinct m from Employee e  join e.roleList r  join r.permissionList p  join p.menu m where e.id=?1 order by m.id")
    List<Menu> findMenuByEmployeeId(Long id);

    @Query("select distinct m from Role r join r.permissionList p join p.menu m where r.id=?1")
    List<Menu> findAllRolePermissionMenuByRoleId(Long id);
}
