package com.funi.distributedcomputer.clone;

import java.io.Serializable;

public class Teacher extends SuperObject<Teacher> implements Serializable, Cloneable {

    private static final long serialVersionUID = -7154088902359541395L;
    private int id;
    private String name;
    private int age;

    public Teacher() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
