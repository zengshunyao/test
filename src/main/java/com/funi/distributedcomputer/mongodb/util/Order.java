package com.funi.distributedcomputer.mongodb.util;

public class Order {
    private String propertyName;
    private boolean ascending;

    public Order(String propertyName, boolean ascending) {
        this.propertyName = propertyName;
        this.ascending = ascending;
    }

    public static Order asc(String propertyName) {
        return new Order(propertyName, true);
    }

    public static Order desc(String propertyName) {
        return new Order(propertyName, false);
    }


    public String getSqlPart() {
        return this.propertyName + ' ' + (ascending ? "asc" : "desc");
    }

}
