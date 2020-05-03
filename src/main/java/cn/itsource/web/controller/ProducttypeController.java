package cn.itsource.web.controller;

import cn.itsource.domain.Producttype;
import cn.itsource.query.ProducttypeQuery;
import cn.itsource.service.IProducttypeService;
import cn.itsource.util.PageUtil;
import com.hazelcast.internal.metrics.LongGauge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.PartialResultException;
import java.util.HashMap;
import java.util.List;

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

    @RequestMapping("/findAllByPage")
    @ResponseBody
    public PageUtil<Producttype> findAllHasChild(ProducttypeQuery producttypeQuery) {
        PageUtil<Producttype> pageUtil = producttypeService.findAllHasChild(producttypeQuery);
        return pageUtil;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Producttype> findAllProductType() {
        List<Producttype> producttypeList = producttypeService.findAll();
        return producttypeList;
    }

    /**
     * 删除后返回删除的id的父类
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public List<Long> delete(long[] ids) {
        List<Long> allParentByID = producttypeService.findAllParentByID(ids[ids.length - 1]);
        if (ids.length > 0) {
            for (long id : ids) {
                producttypeService.delete(id);
            }
        }
        return allParentByID;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Long save(Producttype producttype) {
        return producttypeService.saveReturnParam(producttype);/*返回主键id*/
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Producttype findOneBeforeUpdate(String action, Long id) {
        Producttype producttype = null;
        if ("update".equalsIgnoreCase(action)) {
            producttype = producttypeService.findOne(id);
            producttype.setParent(null);
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
    public Long update(@ModelAttribute("update") Producttype producttype) {
        producttypeService.update(producttype);
        return producttype.getId();/*由于设置了parent为null，所以需要传回id，再次请求，获得所有父类*/
    }

    /**
     * 通过id查询所有的父类，用于级联选择器回显和修改后展开菜单
     *
     * @param
     * @return
     */
    @RequestMapping("/findAllParentByID")
    @ResponseBody
    public List<Long> findAllParentByID(Long id) {
        return producttypeService.findAllParentByID(id);
    }

}