package com.study.spider.pojo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class Industrial {
    public final static transient Map<Integer, Field> ORDER_MAP = new HashMap<Integer, Field>();

    static {
        Field[] fields = Industrial.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ORDER_MAP.put(i, field);
        }
    }

    private String url;
    //产业领域
    private String field;
    //产业类型
    private String technologyCategory;
    //产业适用地区或支撑机构
    private String region;
    //产业龙头企业
    private String topCompany;

    private transient String note;

    public Industrial() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopCompany() {
        return topCompany;
    }

    public void setTopCompany(String topCompany) {
        this.topCompany = topCompany;
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
                ", field='" + field + '\'' +
                ", technologyCategory='" + technologyCategory + '\'' +
                ", region='" + region + '\'' +
                ", topCompany='" + topCompany + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
