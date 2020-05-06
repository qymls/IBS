package cn.itsource.web.controller;

import cn.itsource.domain.Supplier;
import cn.itsource.query.SupplierQuery;
import cn.itsource.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
/**
 * (Supplier)表Controller
 *
 * @author 申林
 * @since 2020-05-06 10:28:42
 */
@Controller
@RequestMapping("Admin/Supplier")
public class SupplierController {
    private ISupplierService supplierService;

    @Autowired
    public void setSupplierService(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }
    
     /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/supplier/supplier";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Supplier> findAll(SupplierQuery supplierQuery) {
        Page<Supplier> pageUtil = supplierService.findPageByQuery(supplierQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                supplierService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Supplier supplier) {
        supplierService.save(supplier);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Supplier findOneBeforeUpdate(String action, Long id) {
        Supplier supplier = null;
        if ("update".equalsIgnoreCase(action)) {
            supplier = supplierService.findOne(id);
            //supplier.setDepartment(null);/*department 脱离持久化状态*/
        }
        return supplier;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param supplier
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Supplier supplier) {
        supplierService.update(supplier);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}