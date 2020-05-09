package cn.itsource.web.controller;

import cn.itsource.domain.Depot;
import cn.itsource.domain.Product;
import cn.itsource.domain.Productstock;
import cn.itsource.query.ProductstockQuery;
import cn.itsource.service.IDepotService;
import cn.itsource.service.IProductService;
import cn.itsource.service.IProductstockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * (Productstock)表Controller
 *
 * @author 申林
 * @since 2020-05-09 14:21:58
 */
@Controller
@RequestMapping("Admin/Productstock")
public class ProductstockController {
    private IProductstockService productstockService;
    private IProductService productService;
    private IDepotService depotService;

    @Autowired
    public void setProductstockService(IProductstockService productstockService) {
        this.productstockService = productstockService;
    }

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

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
        return "WEB-INF/admin/productstock/productstock";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Productstock> findAll(ProductstockQuery productstockQuery) {
        Page<Productstock> pageUtil = productstockService.findPageByQuery(productstockQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                productstockService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Productstock productstock) {
        productstock.setIncomedate(new Date());
        productstock.setAmount(productstock.getNum().multiply(productstock.getPrice()));
        productstockService.save(productstock);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Productstock findOneBeforeUpdate(String action, Long id) {
        Productstock productstock = null;
        if ("update".equalsIgnoreCase(action)) {
            productstock = productstockService.findOne(id);
            //productstock.setDepartment(null);/*department 脱离持久化状态*/
            productstock.setDepot(null);
            productstock.setProduct(null);
        }
        return productstock;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param productstock
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Productstock productstock) {
        productstock.setIncomedate(new Date());
        productstock.setAmount(productstock.getNum().multiply(productstock.getPrice()));
        productstockService.update(productstock);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/depot/findAll")
    @ResponseBody
    public List<Depot> findAllDepot() {
        return depotService.findAll();
    }

    /**
     * 查询所有的产品
     *
     * @return
     */
    @RequestMapping("/product/findAll")
    @ResponseBody
    public List<Product> findAllProduct() {
        return productService.findAll();
    }

}