package cn.itsource.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @MappedSuperclass 这个注解是JPA提供的，专门用于抽取domain实体类的父类
 * 作用是让子类可以继承父类的私有成员变量
 */
@MappedSuperclass
public class BaseDomain {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
