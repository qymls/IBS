package pio;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.google.inject.internal.cglib.core.$ClassNameReader;

import java.util.Date;
@ExcelTarget("emp")
public class PoiEmployee {
    @Excel(name = "用户名")
    private String username;
    @Excel(name = "邮件", width = 20)
    private String email;
    @Excel(name = "年纪")
    private Integer age;
   /* @Excel(name = "部门名称")
    private String department;*/
    @Excel(name = "性别", replace = {"男_true", "女_false"})
    private Boolean sex;
    @Excel(name = "生日", format = "yyyy-MM-dd", width = 50)
    private Date birthday = new Date();
    @Excel(name = "头像", type = 2, width = 10, height = 20)
    private String headImg = "img/login.jpg";

    @ExcelEntity
    private PoiDepartment pioDepartment;

    public PoiEmployee() {
    }

    public PoiEmployee(String username, String email, Integer age, String department, Boolean sex, PoiDepartment pioDepartment) {
        this.username = username;
        this.email = email;
        this.age = age;
        this.sex = sex;
        this.pioDepartment = pioDepartment;
    }



    public PoiDepartment getPioDepartment() {
        return pioDepartment;
    }

    public void setPioDepartment(PoiDepartment pioDepartment) {
        this.pioDepartment = pioDepartment;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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


    @Override
    public String toString() {
        return "PoiEmployee{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
