package pio;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

@ExcelTarget("dept")
public class PoiDepartment {
    @Excel(name = "部门编号_dept")
    private Long id;
    @Excel(name = "所属部门_emp,部门名称_dept")
    private String name;

    public PoiDepartment() {
    }

    public PoiDepartment(Long id ,String name) {
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
