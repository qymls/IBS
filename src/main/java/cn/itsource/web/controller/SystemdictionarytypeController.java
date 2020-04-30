package cn.itsource.web.controller;

import cn.itsource.domain.Systemdictionarytype;
import cn.itsource.query.SystemdictionarytypeQuery;
import cn.itsource.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
/**
 * (Systemdictionarytype)表Controller
 *
 * @author 申林
 * @since 2020-04-30 11:50:11
 */
@Controller
@RequestMapping("Admin/Systemdictionarytype")
public class SystemdictionarytypeController {
    private ISystemdictionarytypeService systemdictionarytypeService;

    @Autowired
    public void setSystemdictionarytypeService(ISystemdictionarytypeService systemdictionarytypeService) {
        this.systemdictionarytypeService = systemdictionarytypeService;
    }
    
     /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/systemdictionarytype/systemdictionarytype";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Systemdictionarytype> findAll(SystemdictionarytypeQuery systemdictionarytypeQuery) {
        Page<Systemdictionarytype> pageUtil = systemdictionarytypeService.findPageByQuery(systemdictionarytypeQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                systemdictionarytypeService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Systemdictionarytype systemdictionarytype) {
        systemdictionarytypeService.save(systemdictionarytype);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Systemdictionarytype findOneBeforeUpdate(String action, Long id) {
        Systemdictionarytype systemdictionarytype = null;
        if ("update".equalsIgnoreCase(action)) {
            systemdictionarytype = systemdictionarytypeService.findOne(id);
            //systemdictionarytype.setDepartment(null);/*department 脱离持久化状态*/
        }
        return systemdictionarytype;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param systemdictionarytype
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Systemdictionarytype systemdictionarytype) {
        systemdictionarytypeService.update(systemdictionarytype);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}