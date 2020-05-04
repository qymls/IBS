package cn.itsource.web.controller;

import cn.itsource.domain.Employee;
import cn.itsource.domain.Product;
import cn.itsource.domain.Producttype;
import cn.itsource.domain.Systemdictionarydetail;
import cn.itsource.query.ProductQuery;
import cn.itsource.service.IProductService;
import cn.itsource.service.IProducttypeService;
import cn.itsource.service.ISystemdictionarydetailService;
import cn.itsource.service.picture.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * (Product)表Controller
 *
 * @author 申林
 * @since 2020-04-30 11:50:14
 */
@Controller
@RequestMapping("Admin/Product")
public class ProductController {
    private IProductService productService;
    private IProducttypeService producttypeService;
    private ISystemdictionarydetailService systemdictionarydetailService;
    private IPictureService pictureService;

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProducttypeService(IProducttypeService producttypeService) {
        this.producttypeService = producttypeService;
    }

    @Autowired
    public void setSystemdictionarydetailService(ISystemdictionarydetailService systemdictionarydetailService) {
        this.systemdictionarydetailService = systemdictionarydetailService;
    }

    @Autowired
    public void setPictureService(IPictureService pictureService) {
        this.pictureService = pictureService;
    }

    /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/product/product";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Product> findAll(ProductQuery productQuery) {
        Page<Product> pageUtil = productService.findPageByQuery(productQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids, HttpServletRequest req) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                Product product = productService.findOne(id);/*先删除图片*/
                productService.delete(id);
                pictureService.deleteImage(product.getPic(), req);/*删除对应的图片*/
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Product product) {
        productService.save(product);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Product findOneBeforeUpdate(String action, Long id) {
        Product product = null;
        if ("update".equalsIgnoreCase(action)) {
            product = productService.findOne(id);
            product.setBrand(null);
            product.setUnit(null);
            product.setProducttype(null);
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
        return map;
    }

    @RequestMapping("/productTypes/findAll")
    @ResponseBody
    public List<Producttype> findAllProductType() {
        List<Producttype> producttypeList = producttypeService.findAll();
        return producttypeList;
    }

    @RequestMapping("/productTypes/findAllParentByID")
    @ResponseBody
    public List<Long> findAllParentByID(Long id) {
        return producttypeService.findAllParentByID(id);
    }

    @ResponseBody
    @RequestMapping("/Systemdictionarydetail/findBySn")
    public List<Systemdictionarydetail> findBySn(String sn) {
        List<Systemdictionarydetail> list = systemdictionarydetailService.findBySn(sn);
        return list;
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(String pic, MultipartFile multipartFile, HttpServletRequest req) {
        return pictureService.saveImage(multipartFile, pic, req);
    }

    @RequestMapping("/deleteImg")
    @ResponseBody
    public Boolean deleteImg(String path, HttpServletRequest req) {
        return pictureService.deleteImage(path, req);
    }

}