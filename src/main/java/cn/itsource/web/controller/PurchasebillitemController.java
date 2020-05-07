package cn.itsource.web.controller;

import cn.itsource.domain.Employee;
import cn.itsource.domain.Producttype;
import cn.itsource.domain.Purchasebillitem;
import cn.itsource.domain.Supplier;
import cn.itsource.domain.vo.PurchaseBillItemVo;
import cn.itsource.query.PurchasebillitemQuery;
import cn.itsource.service.IEmployeeService;
import cn.itsource.service.IProducttypeService;
import cn.itsource.service.IPurchasebillitemService;
import cn.itsource.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * (Purchasebillitem)表Controller
 *
 * @author 申林
 * @since 2020-05-06 10:28:42
 */
@Controller
@RequestMapping("Admin/Purchasebillitem")
public class PurchasebillitemController {
    private IPurchasebillitemService purchasebillitemService;
    private ISupplierService supplierService;
    private IEmployeeService employeeService;
    private IProducttypeService producttypeService;

    @Autowired
    public void setPurchasebillitemService(IPurchasebillitemService purchasebillitemService) {
        this.purchasebillitemService = purchasebillitemService;
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
        return "WEB-INF/admin/purchasebillitem/purchasebillitem";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<PurchaseBillItemVo> findAll(PurchasebillitemQuery purchasebillitemQuery) {
        return purchasebillitemService.findAllVo(purchasebillitemQuery);
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

    @RequestMapping("/producttype/findAllLastProducttype")
    @ResponseBody
    public List<Producttype> findAllLastProducttype() {
        return producttypeService.findAllLastProducttype();
    }

}