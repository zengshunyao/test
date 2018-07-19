package com.funi.distributedcomputer.serialize;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 4882123360038064180L;
    private int id;
    private String name;
    private int age;

    public Person() {
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
