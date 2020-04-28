package cn.itsource.service;

import cn.itsource.domain.Menu;
import cn.itsource.query.MenuQuery;
import cn.itsource.util.PageUtil;

import java.util.List;

public interface IMenuService extends IBaseService<Menu, Long> {

    PageUtil<Menu> findPageByQueryUseUtil(MenuQuery menuQuery);

    List<String> findAllParent(Menu menu);

    Long saveReturnParam(Menu menu);

    Menu findByName(String name);

    List<Menu> findMenuByEmployeeId(Long id);

    List<Menu> findMenuItem();
}
