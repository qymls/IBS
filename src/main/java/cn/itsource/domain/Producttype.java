package cn.itsource.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (Producttype)实体类
 *
 * @author 申林
 * @since 2020-04-30 18:35:08
 */
@Entity
public class Producttype extends BaseDomain {

    private String name;

    private String descs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JSONField(serialize = false)
    private Producttype parent;

    @Transient
    private List<Producttype> children = new ArrayList<>();

    private Long firstid;

    public Producttype getParent() {
        return parent;
    }

    public void setParent(Producttype parent) {
        this.parent = parent;
    }

    public List<Producttype> getChildren() {
        return children;
    }

    public void setChildren(List<Producttype> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }


    public Long getFirstid() {
        return firstid;
    }

    public void setFirstid(Long firstid) {
        this.firstid = firstid;
    }

}