package cn.itsource.web.controller;

import cn.itsource.domain.*;
import cn.itsource.query.StockincomebillQuery;
import cn.itsource.service.*;
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
 * (Stockincomebill)表Controller
 *
 * @author 申林
 * @since 2020-05-09 14:21:59
 */
@Controller
@RequestMapping("Admin/Stockincomebill")
public class StockincomebillController {
    private IStockincomebillService stockincomebillService;
    private ISupplierService supplierService;
    private IEmployeeService employeeService;
    private IProductService productService;
    private IDepotService depotService;

    @Autowired
    public void setStockincomebillService(IStockincomebillService stockincomebillService) {
        this.stockincomebillService = stockincomebillService;
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
        return "WEB-INF/admin/stockincomebill/stockincomebill";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public Page<Stockincomebill> findAll(StockincomebillQuery stockincomebillQuery) {
        Page<Stockincomebill> pageUtil = stockincomebillService.findPageByQuery(stockincomebillQuery);
        return pageUtil;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<Object, Object> delete(long[] ids) {
        HashMap<Object, Object> map = null;
        if (ids.length > 0) {
            for (long id : ids) {
                stockincomebillService.delete(id);
            }
            map = new HashMap<>();
            map.put("success", true);
        }
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    public HashMap<Object, Object> save(Stockincomebill stockincomebill) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        stockincomebill.setInputUser(employee);
        stockincomebill.setInputtime(new Date());
        //定义两个变量用来统计总金额和总数量
        BigDecimal totalAmount = new BigDecimal(0);
        BigDecimal totalNum = new BigDecimal(0);
        for (Stockincomebillitem item : stockincomebill.getBillitems()) {
            //多方数据也必须能够找到一方
            item.setBill(stockincomebill);
            //计算小计金额 BigDecimal做加减乘除运算都必须调用方法来完成
            item.setAmount(item.getPrice().multiply(item.getNum()));
            //总金额和总数量 累加
            totalAmount = totalAmount.add(item.getAmount());
            totalNum = totalNum.add(item.getNum());
        }
        //循环完毕后设置采购单的总金额和总数量
        stockincomebill.setTotalamount(totalAmount);
        stockincomebill.setTotalnum(totalNum);
        stockincomebillService.save(stockincomebill);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ModelAttribute("update")/*所有方法执行前都要执行*/
    public Stockincomebill findOneBeforeUpdate(String action, Long id) {
        Stockincomebill stockincomebill = null;
        if ("update".equalsIgnoreCase(action)) {
            stockincomebill = stockincomebillService.findOne(id);
            stockincomebill.setSupplier(null);
            stockincomebill.setKeeper(null);
            stockincomebill.getBillitems().clear();
            stockincomebill.setDepot(null);
            //stockincomebill.setDepartment(null);/*department 脱离持久化状态*/
        }
        return stockincomebill;
    }

    /**
     * 修改前先查询一次，然后与传递的对比，合并新数据
     *
     * @param stockincomebill
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<Object, Object> update(@ModelAttribute("update") Stockincomebill stockincomebill) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        stockincomebill.setInputUser(employee);
        stockincomebill.setInputtime(new Date());
        //定义两个变量用来统计总金额和总数量
        BigDecimal totalAmount = new BigDecimal(0);
        BigDecimal totalNum = new BigDecimal(0);
        for (Stockincomebillitem item : stockincomebill.getBillitems()) {
            //多方数据也必须能够找到一方
            item.setBill(stockincomebill);
            //计算小计金额 BigDecimal做加减乘除运算都必须调用方法来完成
            item.setAmount(item.getPrice().multiply(item.getNum()));
            //总金额和总数量 累加
            totalAmount = totalAmount.add(item.getAmount());
            totalNum = totalNum.add(item.getNum());
        }
        //循环完毕后设置采购单的总金额和总数量
        stockincomebill.setTotalamount(totalAmount);
        stockincomebill.setTotalnum(totalNum);
        stockincomebillService.save(stockincomebill);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @ResponseBody
    @RequestMapping("/audit")
    public HashMap<Object, Object> audit(@ModelAttribute("update") Stockincomebill stockincomebill) {
        HashMap<Object, Object> map = new HashMap<>();
        try {
            //入库单不存在，就不允许审核
            if (stockincomebill == null) {
                map.put("faile", "审核失败：该入库单不存在！");
                return map;
            }
            //入库单只有待审状态才能被审核
            if (stockincomebill.getStatus() != 0) {
                map.put("faile", "审核失败：只有待审的入库单才能被审核！");
                return map;
            }
            if (stockincomebill.getBillitems().size() == 0) {
                map.put("faile", "无入库产品明细无需入库");
                return map;
            }

            //获取当前登录用户
            Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
            stockincomebill.setInputUser(employee);
            stockincomebill.setInputtime(new Date());

            //定义两个变量用来统计总金额和总数量
            BigDecimal totalAmount = new BigDecimal(0);
            BigDecimal totalNum = new BigDecimal(0);
            for (Stockincomebillitem item : stockincomebill.getBillitems()) {
                //多方数据也必须能够找到一方
                item.setBill(stockincomebill);
                //计算小计金额 BigDecimal做加减乘除运算都必须调用方法来完成
                item.setAmount(item.getPrice().multiply(item.getNum()));
                //总金额和总数量 累加
                totalAmount = totalAmount.add(item.getAmount());
                totalNum = totalNum.add(item.getNum());
            }
            //循环完毕后设置采购单的总金额和总数量
            stockincomebill.setTotalamount(totalAmount);
            stockincomebill.setTotalnum(totalNum);

            //调用审核业务
            stockincomebillService.audit(stockincomebill);

            //修改入库的状态为已审核，并且填入审核人和审核时间
            stockincomebill.setStatus(1);
            stockincomebill.setAuditor(employee);
            stockincomebill.setAuditortime(new Date());

            stockincomebillService.update(stockincomebill);
            map.put("success", "审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("faile", "审核失败：" + e.getMessage());
        }
        return map;
    }


    @RequestMapping("/supplier/findAll")
    @ResponseBody
    public List<Supplier> findAll() {
        return supplierService.findAll();
    }

    @RequestMapping("/keeper/findAll")
    @ResponseBody
    public List<Employee> findEmployeeByDepartmenttName(String deptName) {
        return employeeService.findEmployeeByDepartmenttName(deptName);
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