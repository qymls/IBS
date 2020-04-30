package cn.itsource.web.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.itsource.domain.Department;
import cn.itsource.domain.Employee;
import cn.itsource.query.EmployeeQuery;
import cn.itsource.service.IDepartmentService;
import cn.itsource.service.IEmployeeService;
import cn.itsource.shiro.MD5Utils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/Admin/Import")
public class ImportController {
    private IDepartmentService departmentService;
    private IEmployeeService employeeService;
    private IExcelVerifyHandler usernameUniqueVerifyHandler;/*自定义的验证器对象*/
    private Workbook workbook;

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setUsernameUniqueVerifyHandler(IExcelVerifyHandler usernameUniqueVerifyHandler) {
        this.usernameUniqueVerifyHandler = usernameUniqueVerifyHandler;
    }

    /**
     * 菜单跳转
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "WEB-INF/admin/employee/import";
    }

    @RequestMapping("/importEmployeeData")
    @ResponseBody
    public HashMap<String, List<Employee>> importData(MultipartFile multipartFile, HttpServletResponse response) throws Exception {
        HashMap<String, List<Employee>> map = new HashMap<>();
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        params.setNeedVerfiy(true);/*开启验证*/
        params.setVerifyHandler(usernameUniqueVerifyHandler);/*自定义验证器*/
        ExcelImportResult<Employee> result = ExcelImportUtil.importExcelMore(multipartFile.getInputStream(), Employee.class, params);
        List<Employee> list = result.getList();/*成功的数据*/
        List<Employee> failList = result.getFailList();/*失败的数据*/
        //System.out.println(failList.size());
        for (Employee employee : list) {
            employee.setPassword(MD5Utils.getMD5Password(employee.getUsername() + "123")); //默认密码用户名
            if (employee.getDepartment() != null) {
                Department department = departmentService.findByName(employee.getDepartment().getName());
                employee.setDepartment(department);
            }
            employeeService.save(employee);
        }
        map.put("successList", list);
        map.put("failList", failList);
        if (result.isVerfiyFail()) {
            //把这个文件导出
            workbook = result.getFailWorkbook();/*给成员变量，给下面用*/
        }
        return map;

    }

    /**
     * 下载错误的文档
     *
     * @param response
     * @throws IOException
     */

    @RequestMapping("/downLoadFaileList")
    @ResponseBody
    public void test(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //mime类型
        response.setHeader("Content-disposition", "attachment;filename=error.xlsx");
        response.setHeader("Pragma", "No-cache");//设置不要缓存
        OutputStream ouputStream = response.getOutputStream();
        workbook.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

}
