package cn.itsource.web.controller;

import cn.itsource.domain.*;
import cn.itsource.query.PurchasebillQuery;
import cn.itsource.query.SupplierQuery;
import cn.itsource.service.IEmployeeService;
import cn.itsource.service.IProductService;
import cn.itsource.service.IPurchasebillService;
import cn.itsource.service.ISupplierService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * (Purchasebill)表Controller
 *
 * @author 申林
 * @since 2020-05-06 10:28:40
 */
@Controller
@RequestMapping("Admin/Purchasebill")
public class PurchasebillController {
    private IPurchasebillService purchasebillService;
    private ISupplierService supplierService;
    private IEmployeeService employeeService;
    private IProductService productService;

    @Autowired
    public void setPurchasebillService(IPurchasebillService purchasebillService) {
        this.purchasebillService = purchasebillService;
    }

    @Autowired
    public void setSupplierService(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Autowired
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/purchasebill/purchasebill";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Purchasebill> findAll(PurchasebillQuery purchasebillQuery) {
        Page<Purchasebill> pageUtil = purchasebillService.findPageByQuery(purchasebillQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                purchasebillService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Purchasebill purchasebill) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        purchasebill.setInputUser(employee);
        purchasebill.setInputtime(new Date());
        //定义两个变量用来统计总金额和总数量
        BigDecimal totalAmount = new BigDecimal(0);
        BigDecimal totalNum = new BigDecimal(0);
        for (Purchasebillitem item : purchasebill.getBillitems()) {
            //多方数据也必须能够找到一方
            item.setBill(purchasebill);
            //计算小计金额 BigDecimal做加减乘除运算都必须调用方法来完成
            item.setAmount(item.getPrice().multiply(item.getNum()));
            //总金额和总数量 累加
            totalAmount = totalAmount.add(item.getAmount());
            totalNum = totalNum.add(item.getNum());
        }
        //循环完毕后设置采购单的总金额和总数量
        purchasebill.setTotalamount(totalAmount);
        purchasebill.setTotalnum(totalNum);
        purchasebillService.save(purchasebill);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Purchasebill findOneBeforeUpdate(String action, Long id) {
        Purchasebill purchasebill = null;
        if ("update".equalsIgnoreCase(action)) {
            purchasebill = purchasebillService.findOne(id);
            purchasebill.setSupplier(null);
            purchasebill.setBuyer(null);
            purchasebill.getBillitems().clear();
        }
        return purchasebill;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param purchasebill
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Purchasebill purchasebill) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        purchasebill.setInputUser(employee);
        purchasebill.setInputtime(new Date());
        //定义两个变量用来统计总金额和总数量
        BigDecimal totalAmount = new BigDecimal(0);
        BigDecimal totalNum = new BigDecimal(0);
        for (Purchasebillitem item : purchasebill.getBillitems()) {
            //多方数据也必须能够找到一方
            item.setBill(purchasebill);
            //计算小计金额 BigDecimal做加减乘除运算都必须调用方法来完成
            item.setAmount(item.getPrice().multiply(item.getNum()));
            //总金额和总数量 累加
            totalAmount = totalAmount.add(item.getAmount());
            totalNum = totalNum.add(item.getNum());
        }
        //循环完毕后设置采购单的总金额和总数量
        purchasebill.setTotalamount(totalAmount);
        purchasebill.setTotalnum(totalNum);

        purchasebillService.update(purchasebill);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping("/supplier/findAll")
    @ResponseBody
    public List<Supplier> findAll() {
        return supplierService.findAll();
    }

    @RequestMapping("/buyer/findAll")
    @ResponseBody
    public List<Employee> findEmployeeByDepartmenttName(String deptName) {
        return employeeService.findEmployeeByDepartmenttName(deptName);
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

    /**
     * 查询单个商品
     *
     * @param id
     * @return
     */
    @RequestMapping("/product/findOne")
    @ResponseBody
    public Product findOneProduct(Long id) {
        return productService.findOne(id);
    }
}