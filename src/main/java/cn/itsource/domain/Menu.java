package cn.itsource.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String label;
    private String icon;
    private String url;
    @Column(name = "english_name")
    private String englishName;
    private String operator;
    @Column(name = "create_time")
    private Timestamp createTime;

    private Long firstmenuid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")/*将其看成是子菜单，如果父菜单是null，则是一级菜单*/
    @JSONField(serialize = false)/*json转换时忽略*/
    private Menu parent;

    private String description;
    @Transient/*临时字段，不持久化*//*配置关系的话会直接查出子菜单*/
    private List<Menu> children = new ArrayList<>();/*减当前菜单当成父菜单，会拥有一子菜单*/

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Menu() {
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFirstmenuid() {
        return firstmenuid;
    }

    public void setFirstmenuid(Long firstmenuid) {
        this.firstmenuid = firstmenuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }


    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", englishName='" + englishName + '\'' +
                ", operator='" + operator + '\'' +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", children=" + children +
                '}';
    }
}
