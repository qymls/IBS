package cn.itsource.domain;

import javax.persistence.*;

/**
 * (Systemdictionarydetail)实体类
 *
 * @author 申林
 * @since 2020-04-30 11:50:12
 */
@Entity
public class Systemdictionarydetail extends BaseDomain {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "types_id")
    private Systemdictionarytype types;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Systemdictionarytype getTypes() {
        return types;
    }

    public void setTypes(Systemdictionarytype types) {
        this.types = types;
    }
}