package cn.itsource.web.controller;

import cn.itsource.domain.Depot;
import cn.itsource.query.DepotQuery;
import cn.itsource.service.IDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
/**
 * (Depot)表Controller
 *
 * @author 申林
 * @since 2020-05-09 14:21:55
 */
@Controller
@RequestMapping("Admin/Depot")
public class DepotController {
    private IDepotService depotService;

    @Autowired
    public void setDepotService(IDepotService depotService) {
        this.depotService = depotService;
    }
    
     /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/depot/depot";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Depot> findAll(DepotQuery depotQuery) {
        Page<Depot> pageUtil = depotService.findPageByQuery(depotQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                depotService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Depot depot) {
        depotService.save(depot);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Depot findOneBeforeUpdate(String action, Long id) {
        Depot depot = null;
        if ("update".equalsIgnoreCase(action)) {
            depot = depotService.findOne(id);
            //depot.setDepartment(null);/*department 脱离持久化状态*/
        }
        return depot;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param depot
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Depot depot) {
        depotService.update(depot);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}