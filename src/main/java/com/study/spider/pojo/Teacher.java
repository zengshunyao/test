package com.study.spider.pojo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class Teacher {

    public final static transient Map<Integer, Field> ORDER_MAP = new HashMap<Integer, Field>();

    static {
        Field[] fields = Teacher.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ORDER_MAP.put(i, field);
        }
    }

    private String url;
    private String name;
    private String level;
    private String workCompany;
    private String region;
    private String field;
    private transient String note;

    public Teacher() {
    }

    public Teacher(String url, String name, String level, String workCompany, String region, String field) {
        this.url = url;
        this.name = name;
        this.level = level;
        this.workCompany = workCompany;
        this.region = region;
        this.field = field;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getWorkCompany() {
        return workCompany;
    }

    public void setWorkCompany(String workCompany) {
        this.workCompany = workCompany;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", workCompany='" + workCompany + '\'' +
                ", region='" + region + '\'' +
                ", field='" + field + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
