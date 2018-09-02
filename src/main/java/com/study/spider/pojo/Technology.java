package com.study.spider.pojo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class Technology {
    public final static transient Map<Integer, Field> ORDER_MAP = new HashMap<Integer, Field>();

    static {
        Field[] fields = Technology.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ORDER_MAP.put(i, field);
        }
    }

    private String url;
    //技术供给
    private String technologyName;
    //产品领域
    private String field;
    //技术类别
    private String technologyCategory;
    //适用区域
    private String region;

    private transient String note;

    public Technology() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTechnologyCategory() {
        return technologyCategory;
    }

    public void setTechnologyCategory(String technologyCategory) {
        this.technologyCategory = technologyCategory;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Technology{" +
                "url='" + url + '\'' +
                ", technologyName='" + technologyName + '\'' +
                ", field='" + field + '\'' +
                ", technologyCategory='" + technologyCategory + '\'' +
                ", region='" + region + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
