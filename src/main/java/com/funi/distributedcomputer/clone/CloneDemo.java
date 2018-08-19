package com.funi.distributedcomputer.clone;

import java.io.IOException;

public class CloneDemo {

    /**
     *
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student student = new Student();
        student.setAge(18);
        student.setName("bug");

        Teacher teacher = new Teacher();
        teacher.setAge(81);
        teacher.setName("oldbug");

        student.setTeacher(teacher);

        System.out.println(student);
        System.out.println("hashcode：" + student.hashCode());

        Student studentBak = student.deepClone();
        System.out.println(studentBak);
        System.out.println("hashcode：" + studentBak.hashCode());
        System.out.println("内存地址：" + (studentBak == student));

        System.out.println("----------------分割线-----------------");

        student.getTeacher().setName("newbug");
        System.out.println(student);
        System.out.println("hashcode：" + student.hashCode());

        studentBak = student.deepClone();
        System.out.println(studentBak);
        System.out.println("hashcode：" + studentBak.hashCode());
        System.out.println("内存地址：" + (studentBak == student));
    }
}
