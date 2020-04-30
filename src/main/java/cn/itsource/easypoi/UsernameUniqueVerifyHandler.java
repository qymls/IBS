package cn.itsource.easypoi;

import cn.afterturn.easypoi.excel.entity.result.ExcelVerifyHandlerResult;
import cn.afterturn.easypoi.handler.inter.IExcelVerifyHandler;
import cn.itsource.domain.Employee;
import cn.itsource.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * easy poi 自定义验证
 */
@Component
public class UsernameUniqueVerifyHandler implements IExcelVerifyHandler<Employee> {

    private IEmployeeService employeeService;

    @Autowired
    public void setEmployeeService(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public ExcelVerifyHandlerResult verifyHandler(Employee employee) {
        ExcelVerifyHandlerResult result = new ExcelVerifyHandlerResult();
        Employee employeeFromDB = employeeService.findByUsername(employee.getUsername());
        if (employeeFromDB != null) {
            result.setSuccess(false);/*认证失败*/
            result.setMsg("用户名" + employee.getUsername() + "被占用");
        } else {
            result.setSuccess(true);
        }

        return result;
    }
}
