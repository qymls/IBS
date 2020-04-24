package cn.itsource.web.controller;

import cn.itsource.domain.Resource;
import cn.itsource.query.ResourceQuery;
import cn.itsource.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
/**
 * (Resource)表Controller
 *
 * @author 申林
 * @since 2020-04-24 18:41:41
 */
@Controller
@RequestMapping("Admin/Resource")
public class ResourceController {
    private IResourceService resourceService;

    @Autowired
    public void setResourceService(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Resource> findAll(ResourceQuery resourceQuery) {
        Page<Resource> pageUtil = resourceService.findPageByQuery(resourceQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                resourceService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
            map.put("msg", "操作做成功");
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Resource resource) {
        resourceService.save(resource);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "操作做成功");
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Resource findOneBeforeUpdate(String action, Long id) {
        Resource resource = null;
        if ("update".equalsIgnoreCase(action)) {
            resource = resourceService.findOne(id);
            //resource.setDepartment(null);/*department 脱离持久化状态*/
        }
        return resource;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param resource
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Resource resource) {
        resourceService.update(resource);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "操作做成功");
        return map;
    }
}