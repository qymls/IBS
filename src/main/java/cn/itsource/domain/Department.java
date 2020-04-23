package cn.itsource.domain;

import javax.persistence.Entity;


@Entity
public class Department extends BaseDomain {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
