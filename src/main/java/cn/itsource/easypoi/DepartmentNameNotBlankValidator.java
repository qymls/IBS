package cn.itsource.easypoi;

import cn.itsource.domain.Department;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 注解验证规则
 */
public class DepartmentNameNotBlankValidator implements ConstraintValidator<DepartmentNameNotNull, Department> {
    private DepartmentNameNotNull departmentNameNotNull;/*注解类型的成员变量*/

    /**
     * 初始化
     *
     * @param departmentNameNotNull;
     */
    @Override
    public void initialize(DepartmentNameNotNull departmentNameNotNull) {
        this.departmentNameNotNull = departmentNameNotNull;

    }

    @Override
    public boolean isValid(Department department, ConstraintValidatorContext constraintValidatorContext) {
        //departmentNameNotNull.message();
        if (department == null) {
            return true;
        }
        if (department.getId() != null && department.getId() > 0) {/*导入的无id*/
            return true;
        }
        return StringUtils.isNoneBlank(department.getName());
    }
}
