package cn.itsource.web.controller;

import cn.itsource.com.baidu.translate.demo.TransApi;
import cn.itsource.domain.Employee;
import cn.itsource.domain.Menu;
import cn.itsource.query.MenuQuery;
import cn.itsource.service.IMenuService;
import cn.itsource.util.ConstantApi;
import cn.itsource.util.PageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/Admin/Menu")
public class MenuController {
    private IMenuService menuService;

    @Autowired
    public void setMenuService(IMenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/menulist/menu_data";
    }

    /**
     * 初始化的菜单
     *
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Menu> findAll() {
        return menuService.findAll();
    }

    /**
     * 菜单管理查询的菜单
     *
     * @param menuQuery
     * @return
     */
    @RequestMapping("/findAllMenu")
    @ResponseBody
    public PageUtil<Menu> findAllMenu(MenuQuery menuQuery) {
        return menuService.findPageByQueryUseUtil(menuQuery);
    }

    /**
     * 获取有权限的菜单
     *
     * @return
     */
    @RequestMapping("/findOne")
    @ResponseBody
    public List<Menu> findOne() {
        List<Menu> menusList = new LinkedList();
        Long[] ids = {-1l, 6l, 7l, 15l, 17l, 24l, 26l, 29l, 27l, 32l, 41l, 42l};
        for (Long id : ids) {
            Menu menu = menuService.findOne(id);
            menusList.add(menu);
        }
        return menusList;
    }

    /**
     * 查询菜单名称是否重复
     *
     * @param name
     * @return
     */
    @RequestMapping("/findByName")
    @ResponseBody
    public Menu findOne(String name) {
        return menuService.findByName(name);
    }

    /**
     * 删除菜单
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<Object, Object> delete(Long[] ids) {
        HashMap<Object, Object> map = new HashMap<>();
        for (long id : ids) {
            menuService.delete(id);
        }
        map.put("success", true);
        return map;
    }

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Menu save(Menu menu) {
        Menu newMenu = menuService.findOne(menuService.saveReturnParam(menu));
        return newMenu;/*返回添加的对象*/
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Menu findOneBeforeUpdate(String action, Long id) {
        Menu menu = null;
        if ("update".equalsIgnoreCase(action)) {
            menu = menuService.findOne(id);
            //permission.setDepartment(null);/*department 脱离持久化状态*/
        }
        return menu;
    }


    /**
     * 修改方法，并且返回当前修改菜单的所有父菜单
     *
     * @param menu
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public List<String> update(@ModelAttribute("update") Menu menu) {
        menu.setLabel(menu.getName());/*修改name要和lable一起修改了*/
        menuService.update(menu);/*修改后返回其父菜单，用于打开父菜单*/
        /*如果直接返回null的话，ajax的success方法不会执行*/
        List<String> allParentName = menuService.findAllParent(menu);/*list中只添加了名字，可以自行添加整个菜单*/
        return allParentName;
    }

    /**
     * 到菜单新加和删除界面
     *
     * @return
     */
    @RequestMapping("/forwardMenuEdit")
    public String forwardMenuEdit() {/*返回到添加页面*/
        return "WEB-INF/admin/menulist/edit";
    }

    @RequestMapping("/forwardMenuList")
    public String forwardMenuList() {/*返回到菜单List界面*/
        return "WEB-INF/admin/menulist/menu_data";
    }

    /**
     * 百度翻译Api
     *
     * @param name
     * @return
     */
    @RequestMapping("/getEnglishNameByBaiduApi")
    @ResponseBody
    public String getEnglishNameByBaiduApi(String name) {
        TransApi api = new TransApi(ConstantApi.BaiduFanyi_APP_ID, ConstantApi.BaiduFanyi_SECURITY_KEY);
        String transResult = api.getTransResult(name, "auto", "en");
        return transResult;
    }

    /**
     * 根据用户权限查询菜单
     *
     * @return
     */
    @RequestMapping("/findMenuByEmployeeId")
    @ResponseBody
    public List<Menu> findMenuByEmployeeId() {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();/*获取登录用户*/
        return menuService.findMenuByEmployeeId(employee.getId());
    }

    /**
     * 查询所有最后一级菜单
     *
     * @return
     */
    @RequestMapping("/findMenuItem")
    @ResponseBody
    public List<Menu> findMenuItem() {
        return menuService.findMenuItem();
    }

}
