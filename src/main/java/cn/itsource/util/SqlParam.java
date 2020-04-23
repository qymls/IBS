package cn.itsource.util;

import org.springframework.util.StringUtils;

public class SqlParam {
    private String cloum;
    private String value;


    public String getSql() {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.hasLength(cloum) && StringUtils.hasLength(value)) {
            sb.append(" where " + cloum + " = '" + value + "'");
        }
        return sb.toString();
    }

    public String getCloum() {
        return cloum;
    }

    public void setCloum(String cloum) {
        this.cloum = cloum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
