package cn.itsource.easypoi;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)/*使用在字段上*/
@Retention(RetentionPolicy.RUNTIME)/*生命周期*/
@Constraint(validatedBy = DepartmentNameNotBlankValidator.class)
public @interface DepartmentNameNotNull {
    String message() default "员工所属部门不能为空！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
