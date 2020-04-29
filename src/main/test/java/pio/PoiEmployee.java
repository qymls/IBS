package pio;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class PoiEmployee {
    @Excel(name = "用户名")
    private String username;
    @Excel(name = "邮件",width = 20)
    private String email;
    @Excel(name = "年纪")
    private Integer age;
    @Excel(name = "部门名称")
    private String department;
    @Excel(name = "性别",replace = {"男_true","女_false"})
    private Boolean sex;

    public PoiEmployee() {
    }

    public PoiEmployee(String username, String email, Integer age, String department, Boolean sex) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.department = department;
        this.sex = sex;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "PoiEmployee{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
