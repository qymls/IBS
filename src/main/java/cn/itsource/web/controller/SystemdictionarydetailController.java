package cn.itsource.web.controller;

import cn.itsource.domain.Systemdictionarydetail;
import cn.itsource.domain.Systemdictionarytype;
import cn.itsource.query.SystemdictionarydetailQuery;
import cn.itsource.service.ISystemdictionarydetailService;
import cn.itsource.service.ISystemdictionarytypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * (Systemdictionarydetail)表Controller
 *
 * @author 申林
 * @since 2020-04-30 11:50:12
 */
@Controller
@RequestMapping("Admin/Systemdictionarydetail")
public class SystemdictionarydetailController {
    private ISystemdictionarydetailService systemdictionarydetailService;
    private ISystemdictionarytypeService systemdictionarytypeService;

    @Autowired
    public void setSystemdictionarydetailService(ISystemdictionarydetailService systemdictionarydetailService) {
        this.systemdictionarydetailService = systemdictionarydetailService;
    }


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
        return "WEB-INF/admin/systemdictionarydetail/systemdictionarydetail";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Systemdictionarydetail> findAll(SystemdictionarydetailQuery systemdictionarydetailQuery) {
        Page<Systemdictionarydetail> pageUtil = systemdictionarydetailService.findPageByQuery(systemdictionarydetailQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                systemdictionarydetailService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Systemdictionarydetail systemdictionarydetail) {
        systemdictionarydetailService.save(systemdictionarydetail);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Systemdictionarydetail findOneBeforeUpdate(String action, Long id) {
        Systemdictionarydetail systemdictionarydetail = null;
        if ("update".equalsIgnoreCase(action)) {
            systemdictionarydetail = systemdictionarydetailService.findOne(id);
            systemdictionarydetail.setTypes(null);/*department 脱离持久化状态*/
        }
        return systemdictionarydetail;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param systemdictionarydetail
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Systemdictionarydetail systemdictionarydetail) {
        systemdictionarydetailService.update(systemdictionarydetail);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    /**
     * 所有字典的类别
     *
     * @return
     */
    @RequestMapping("/systemdictionarytype/findAll")
    @ResponseBody
    public List<Systemdictionarytype> systemdictionarytypeFindAll() {
        List<Systemdictionarytype> systemdictionarytypeList = systemdictionarytypeService.findAll();
        return systemdictionarytypeList;
    }


}