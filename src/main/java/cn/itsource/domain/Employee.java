package cn.itsource.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.google.inject.internal.cglib.core.$ClassNameReader;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ExcelTarget("emp")
public class Employee extends BaseDomain {
    @Excel(name = "员工姓名")
    private String username;
    @Excel(name = "员工密码", width = 50)
    private String password;
    @Excel(name = "员工邮箱", width = 50)
    private String email;
    @Excel(name = "员工头像", type = 2, width = 10,height = 20)
    private String headImage;
    @Excel(name = "年龄")
    private Long age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @ExcelEntity
    private Department department;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList = new ArrayList<>();

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }


    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", headImage='" + headImage + '\'' +
                ", age=" + age +
                '}';
    }
}
