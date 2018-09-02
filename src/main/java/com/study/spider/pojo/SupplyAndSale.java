package com.study.spider.pojo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SupplyAndSale {
    public final static transient Map<Integer, Field> ORDER_MAP = new HashMap<Integer, Field>();

    static {
        Field[] fields = SupplyAndSale.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ORDER_MAP.put(i, field);
        }
    }

    private String url;
    //企业名称
    private String company;
    //经营范围
    private String scope;
    //经营方式
    private String operationMode;
    //产业领域
    private String field;

    private transient String note;

    public SupplyAndSale() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(String operationMode) {
        this.operationMode = operationMode;
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
        return "SupplyAndSale{" +
                "url='" + url + '\'' +
                ", company='" + company + '\'' +
                ", scope='" + scope + '\'' +
                ", operationMode='" + operationMode + '\'' +
                ", field='" + field + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
