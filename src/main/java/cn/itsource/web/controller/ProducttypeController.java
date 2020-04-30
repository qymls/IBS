package cn.itsource.web.controller;

import cn.itsource.domain.Producttype;
import cn.itsource.query.ProducttypeQuery;
import cn.itsource.service.IProducttypeService;
import cn.itsource.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * (Producttype)表Controller
 *
 * @author 申林
 * @since 2020-04-30 11:50:13
 */
@Controller
@RequestMapping("Admin/Producttype")
public class ProducttypeController {
    private IProducttypeService producttypeService;

    @Autowired
    public void setProducttypeService(IProducttypeService producttypeService) {
        this.producttypeService = producttypeService;
    }

    /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/producttype/producttype";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public PageUtil<Producttype> findAllHasChild(ProducttypeQuery producttypeQuery) {
        PageUtil<Producttype> pageUtil = producttypeService.findAllHasChild(producttypeQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                producttypeService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Producttype producttype) {
        producttypeService.save(producttype);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Producttype findOneBeforeUpdate(String action, Long id) {
        Producttype producttype = null;
        if ("update".equalsIgnoreCase(action)) {
            producttype = producttypeService.findOne(id);
            //producttype.setDepartment(null);/*department 脱离持久化状态*/
        }
        return producttype;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param producttype
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Producttype producttype) {
        producttypeService.update(producttype);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }
}