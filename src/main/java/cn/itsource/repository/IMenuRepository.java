package cn.itsource.repository;

import cn.itsource.domain.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMenuRepository extends IBaseRepository<Menu, Long> {
    Menu findByName(String name);

    @Query(nativeQuery = true, value = "select * from menu where parent_id = 0")
    List<Menu> getStairMenu();

}
