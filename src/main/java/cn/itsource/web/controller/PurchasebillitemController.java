package cn.itsource.web.controller;

import cn.itsource.domain.Purchasebillitem;
import cn.itsource.query.PurchasebillitemQuery;
import cn.itsource.service.IPurchasebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
/**
 * (Purchasebillitem)表Controller
 *
 * @author 申林
 * @since 2020-05-06 10:28:42
 */
@Controller
@RequestMapping("Admin/Purchasebillitem")
public class PurchasebillitemController {
    private IPurchasebillitemService purchasebillitemService;

    @Autowired
    public void setPurchasebillitemService(IPurchasebillitemService purchasebillitemService) {
        this.purchasebillitemService = purchasebillitemService;
    }
    
     /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/purchasebillitem/purchasebillitem";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Purchasebillitem> findAll(PurchasebillitemQuery purchasebillitemQuery) {
        Page<Purchasebillitem> pageUtil = purchasebillitemService.findPageByQuery(purchasebillitemQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                purchasebillitemService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Purchasebillitem purchasebillitem) {
        purchasebillitemService.save(purchasebillitem);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Purchasebillitem findOneBeforeUpdate(String action, Long id) {
        Purchasebillitem purchasebillitem = null;
        if ("update".equalsIgnoreCase(action)) {
            purchasebillitem = purchasebillitemService.findOne(id);
            //purchasebillitem.setDepartment(null);/*department 脱离持久化状态*/
        }
        return purchasebillitem;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param purchasebillitem
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Purchasebillitem purchasebillitem) {
        purchasebillitemService.update(purchasebillitem);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}