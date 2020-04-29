package cn.itsource.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import javax.persistence.*;

/**
 * (Department)实体类
 *
 * @author 申林
 * @since 2020-04-28 12:38:15
 */
@Entity
@ExcelTarget("dept")
public class Department extends BaseDomain {
    @Excel(name = "部门名称_dept,所属部门_emp")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}