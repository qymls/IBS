package cn.itsource.web.controller;

import cn.itsource.domain.Product;
import cn.itsource.query.ProductQuery;
import cn.itsource.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
/**
 * (Product)表Controller
 *
 * @author 申林
 * @since 2020-04-23 21:39:49
 */
@Controller
@RequestMapping("Admin/Product")
public class ProductController {
    private IProductService productService;

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Product> findAll(ProductQuery productQuery) {
        Page<Product> pageUtil = productService.findPageByQuery(productQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                productService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
            map.put("msg", "操作做成功");
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Product product) {
        productService.save(product);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "操作做成功");
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Product findOneBeforeUpdate(String action, Long id) {
        Product product = null;
        if ("update".equalsIgnoreCase(action)) {
            product = productService.findOne(id);
            //product.setDepartment(null);/*department 脱离持久化状态*/
        }
        return product;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param product
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Product product) {
        productService.update(product);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "操作做成功");
        return map;
    }
}