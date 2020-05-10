package cn.itsource.domain.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeriesLine {
    private String name;
    private String type = "line";
    private String stack = "总量";
    private List<Map<String, BigDecimal>> tempdata = new ArrayList<>();
    private List<BigDecimal> data = new ArrayList<>();
    private List<String> lineMonth = new ArrayList<>();


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

    public void setData(List<BigDecimal> data) {
        this.data = data;
    }

    public List<String> getLineMonth() {
        return lineMonth;
    }

    public void setLineMonth(List<String> lineMonth) {
        this.lineMonth = lineMonth;
    }

    public List<Map<String, BigDecimal>> getTempdata() {
        return tempdata;
    }

    public void setTempdata(List<Map<String, BigDecimal>> tempdata) {
        this.tempdata = tempdata;
    }


    @Override
    public String toString() {
        return "SeriesLine{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", stack='" + stack + '\'' +
                ", tempdata=" + tempdata +
                ", data=" + data +
                ", lineMonth=" + lineMonth +
                '}';
    }
}
