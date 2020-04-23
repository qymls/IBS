package cn.itsource.domain;

import javax.persistence.*;
import java.sql.Timestamp;
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
    @Column(name = "parent_id")
    private Long parent;
    private String description;
    @Transient/*临时字段，不持久化*/
    private List<Menu> children;

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


    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
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
                ", parent=" + parent +
                ", description='" + description + '\'' +
                ", children=" + children +
                '}';
    }
}
