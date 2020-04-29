package cn.itsource.service.impl;

import cn.itsource.domain.Employee;
import cn.itsource.domain.Menu;
import cn.itsource.query.MenuQuery;
import cn.itsource.repository.IMenuRepository;
import cn.itsource.service.IMenuService;
import cn.itsource.util.PageUtil;
import org.apache.shiro.SecurityUtils;
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
            if (menu.getParent() != null) {/*排除一级菜单在进来对比的情况*/
                if (id.equals(menu.getParent().getId())) {/*可能出现空指的放在后面*/
                    menu.setUrl("Admin/jframeUrl?url=" + menu.getUrl());/*特殊处理下url，用于放在web-info下时请求*/
                    menu.setChildren(getMenuTree(menuList, menu.getId()));
                    treeList.add(menu);
                }
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
            if (menu.getParent() != null) {
                if (id.equals(menu.getParent().getId())) {/*可能出现空指的放在后面*/
                    menu.setChildren(getMenuTreeMenu(menuList, menu.getId()));
                    treeList.add(menu);
                }
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
    public List<String> findAllParent(Menu menu) {
        List<String> menuListParent = new ArrayList<>();
        Menu updateMenu = menuRepository.findOne(menu.getId());/*修改的菜单*/
        getPartnt(updateMenu, menuListParent);
        return menuListParent;
    }


    /**
     * 递归查找所有的父菜单,装到list中
     *
     * @param list
     */
    public void getPartnt(Menu updateMenu, List<String> list) {
        Menu parent = updateMenu.getParent();
        if (parent != null) {
            list.add(parent.getName());
            getPartnt(parent, list);
        }

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
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();/*获取当前用户*/
        menu.setLabel(menu.getName());/*这里的name和lable必须一致*/
        menu.setCreateTime(new Timestamp(System.currentTimeMillis()));
        menu.setOperator(employee.getUsername());/*创建菜单操作人*/
        menuRepository.save(menu);
        return menu.getId();
    }

    @Override
    public Menu findByName(String name) {
        List<Menu> menuList = menuRepository.findByName(name);
        if (menuList.size() > 0) {
            return menuList.get(0);
        }
        return null;
    }

    @Override
    public List<Menu> findMenuByEmployeeId(Long id) {
        List<Menu> firstMenuList = new ArrayList<>();/*用于装一级菜单*/
        List<Menu> menuByEmployeeIdList = menuRepository.findMenuByEmployeeId(id);/*查询用户有权限的菜单*/
        for (Menu menuByEmployeeId : menuByEmployeeIdList) {/*数据不能重复，需要去重复，sql或写逻辑代码，这里用了sql*/
            getPermissionnMenuDg(menuByEmployeeId, firstMenuList);
        }
        return firstMenuList;
    }


    public void getPermissionnMenuDg(Menu menuByEmployeeId, List<Menu> firstMenuList) {
        menuByEmployeeId.setUrl("Admin/jframeUrl?url=" + menuByEmployeeId.getUrl());
        Menu parent = menuByEmployeeId.getParent();
        if (parent != null) {
            parent.setUrl("Admin/jframeUrl?url=" + parent.getUrl());
            parent.getChildren().add(menuByEmployeeId);
            getPermissionnMenuDg(parent, firstMenuList);/*递归了*/
        } else {
            if (!firstMenuList.contains(menuByEmployeeId)) {/*这里解决两个子菜单拥有用一个父菜单的情况*/
                firstMenuList.add(menuByEmployeeId);/*一直递归到没有父菜单为止*/
            }
        }
    }

    /**
     * 查询所有最后一级菜单
     *
     * @return
     */
    @Override
    public List<Menu> findMenuItem() {
        List<Menu> menuItemList = new ArrayList<>();
        List<Menu> allMenu = findAll();/*得到所有菜单的数据已经是有children的数据了*/
        getLastMenuItem(allMenu, menuItemList);
        return menuItemList;
    }

    public void getLastMenuItem(List<Menu> menuList, List<Menu> menuItemList) {
        for (Menu menu : menuList) {
            if (menu.getChildren().size() > 0) {
                getLastMenuItem(menu.getChildren(), menuItemList);
            } else {
                menuItemList.add(menu);
            }

        }
    }

}
