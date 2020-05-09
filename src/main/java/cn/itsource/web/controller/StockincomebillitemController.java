package cn.itsource.web.controller;

import cn.itsource.domain.Stockincomebillitem;
import cn.itsource.query.StockincomebillitemQuery;
import cn.itsource.service.IStockincomebillitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
/**
 * (Stockincomebillitem)表Controller
 *
 * @author 申林
 * @since 2020-05-09 14:22:00
 */
@Controller
@RequestMapping("Admin/Stockincomebillitem")
public class StockincomebillitemController {
    private IStockincomebillitemService stockincomebillitemService;

    @Autowired
    public void setStockincomebillitemService(IStockincomebillitemService stockincomebillitemService) {
        this.stockincomebillitemService = stockincomebillitemService;
    }
    
     /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/stockincomebillitem/stockincomebillitem";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Stockincomebillitem> findAll(StockincomebillitemQuery stockincomebillitemQuery) {
        Page<Stockincomebillitem> pageUtil = stockincomebillitemService.findPageByQuery(stockincomebillitemQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                stockincomebillitemService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Stockincomebillitem stockincomebillitem) {
        stockincomebillitemService.save(stockincomebillitem);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Stockincomebillitem findOneBeforeUpdate(String action, Long id) {
        Stockincomebillitem stockincomebillitem = null;
        if ("update".equalsIgnoreCase(action)) {
            stockincomebillitem = stockincomebillitemService.findOne(id);
            //stockincomebillitem.setDepartment(null);/*department 脱离持久化状态*/
        }
        return stockincomebillitem;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param stockincomebillitem
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Stockincomebillitem stockincomebillitem) {
        stockincomebillitemService.update(stockincomebillitem);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}