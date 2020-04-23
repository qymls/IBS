package cn.itsource.service.impl;

import cn.itsource.domain.Menu;
import cn.itsource.query.MenuQuery;
import cn.itsource.repository.IMenuRepository;
import cn.itsource.service.IMenuService;
import cn.itsource.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, Long> implements IMenuService {
    private IMenuRepository menuRepository;

    @Autowired
    public void setMenuRepository(IMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


    /**
     * 初始化菜单管理
     *
     * @return
     */
    @Override
    public List<Menu> findAll() {/*重写FindAll方法，应为需要特殊处理下才能返回的数据*/
        List<Menu> menuList = menuRepository.findAll();
        List<Menu> stairMenuList = menuRepository.getStairMenu();
        for (Menu stairMenu : stairMenuList) {
            stairMenu.setUrl("Admin/jframeUrl?url=" + stairMenu.getUrl());/*特殊处理下url，用于放在web-info下时请求不需要的可以换个地方*/
            stairMenu.setChildren(getMenuTree(menuList, stairMenu.getId()));/*一级菜单*/
        }
        return stairMenuList;
    }

    /**
     * 给一级菜单添加子菜单，递归，文件都放在web-info下的，所以需要加上请求，用于菜单初始化
     *
     * @param menuList
     * @param id
     * @return
     */
    private List<Menu> getMenuTree(List<Menu> menuList, Long id) {
        List<Menu> treeList = new ArrayList<>();
/*        menuList.forEach(menu -> {
            if (Objects.equals(id, menu.getParent())) {
                menu.setChildren(getMenuTree(menuList, menu.getId()));
                treeList.add(menu);
            }
        });*/
        for (Menu menu : menuList) {/*将所有菜单的parentid和传递的菜单id对比，相等就递归调用，并且加到treeList中，用于setChildren*/
            if (id.equals(menu.getParent())) {/*可能出现空指的放在后面*/
                menu.setUrl("Admin/jframeUrl?url=" + menu.getUrl());/*特殊处理下url，用于放在web-info下时请求*/
                menu.setChildren(getMenuTree(menuList, menu.getId()));
                treeList.add(menu);
            }

        }
        return treeList;
    }

    /**
     * 菜单管理界面
     *
     * @param menuQuery
     * @return
     */

    @Override
    public PageUtil<Menu> findPageByQueryUseUtil(MenuQuery menuQuery) {
        Page pageByQuery = menuRepository.findPageByQuery(menuQuery);
        List<Menu> menuList = menuRepository.findAll();
        PageUtil<Menu> menuPageUtil = new PageUtil<>();
        List<Menu> stairMenuList = pageByQuery.getContent();/*分页对的一级菜单数据需要处理一下*/
        for (Menu stairMenu : stairMenuList) {
            stairMenu.setChildren(getMenuTreeMenu(menuList, stairMenu.getId()));/*一级菜单*/
        }
        menuPageUtil.setList(stairMenuList);
        menuPageUtil.setCurrentPage(pageByQuery.getNumber() + 1);/*当前页数+1，是从0开始的*/
        menuPageUtil.setTotalRows((int) pageByQuery.getTotalElements());
        return menuPageUtil;
    }


    /**
     * 菜单管理，不用初始化菜单，不需要特殊处理url
     *
     * @param menuList
     * @param id
     * @return
     */
    private List<Menu> getMenuTreeMenu(List<Menu> menuList, Long id) {
        List<Menu> treeList = new ArrayList<>();
        for (Menu menu : menuList) {/*将所有菜单的parentid和传递的菜单id对比，相等就递归调用，并且加到treeList中，用于setChildren*/
            if (id.equals(menu.getParent())) {/*可能出现空指的放在后面*/
                menu.setChildren(getMenuTreeMenu(menuList, menu.getId()));
                treeList.add(menu);
            }
        }
        return treeList;
    }


    /**
     * 获取一个子菜单所有的父菜单
     *
     * @return
     */
    @Override
    public List<String> findAllParent(Long parent) {
        List<String> menuListParent = new LinkedList<>();
        Menu menu = menuRepository.findOne(parent);
        if (menu != null) {
            menuListParent.add(menu.getName());
            getPartnt(menu.getParent(), menuListParent);
        }
        return menuListParent;
    }


    /**
     * 递归查找所有的父菜单,装到list中
     *
     * @param parent
     * @param list
     */
    public void getPartnt(Long parent, List<String> list) {
        Menu menu = menuRepository.findOne(parent);
        if (menu != null) {
            list.add(menu.getName());
            getPartnt(menu.getParent(), list);
        }

    }


    /**
     * 重写父类的sqve方法，不能满足需求
     *
     * @param menu
     */
    @Transactional(readOnly = false)
    @Override
    public void update(Menu menu) {
        String sql = "update menu set name =? , label = ? , icon = ?,english_name = ? , url= ?,description = ? where id = ?";
        menuRepository.createNativeQuery(sql, menu.getName(), menu.getName(), menu.getIcon(), menu.getEnglishName(), menu.getUrl(), menu.getDescription(), menu.getId());
    }

    /**
     * 添加菜单返回主键
     *
     * @param menu
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public Long saveReturnParam(Menu menu) {
        menu.setLabel(menu.getName());/*这里的name和lable必须一致*/
        menu.setCreateTime(new Timestamp(System.currentTimeMillis()));
        menuRepository.save(menu);
        return menu.getId();
    }

    @Override
    public Menu findByName(String name) {
        return menuRepository.findByName(name);
    }


}
