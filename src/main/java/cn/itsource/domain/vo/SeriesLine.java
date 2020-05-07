package cn.itsource.domain.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SeriesLine {
    private String name;
    private String type = "line";
    private String stack = "总量";
    private List<BigDecimal> data = new ArrayList<>();
    private List<String> date = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public List<BigDecimal> getData() {
        return data;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public void setData(List<BigDecimal> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SeriesLine{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", stack='" + stack + '\'' +
                ", data=" + data +
                ", date=" + date +
                '}';
    }
}
