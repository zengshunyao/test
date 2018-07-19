package com.funi.distributedcomputer.clone;

import java.io.Serializable;

public class Student extends SuperObject<Student> implements Serializable, Cloneable {
    private static final long serialVersionUID = 4720668746970954464L;
    private int id;
    private String name;
    private int age;

    private Teacher teacher;

    public Student() {
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

}
