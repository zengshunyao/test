package com.funi.distributedcomputer.serialize;

import java.io.*;

public class SerializDemo {
    public static void main(String[] args) {

        try {
            //序列化
            SerializePerson();

            System.out.println("--------------华丽的分割线----------------------");
            //反序列化
            DeserializePerson();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void DeserializePerson() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("person"));
        Person personBak = (Person) inputStream.readObject();
        System.out.println(personBak);
        System.out.println("hashcode：" + personBak.hashCode());
        System.out.println("读取完毕。。。。。。");

        Person personBak2 = (Person) inputStream.readObject();
        System.out.println(personBak2);
        System.out.println("hashcode：" + personBak2.hashCode());
        System.out.println("读取完毕。。。。。。");
        inputStream.close();

//        ObjectInputStream inputStream2 = new ObjectInputStream(new FileInputStream("person"));
//        Person personBak2 = (Person) inputStream2.readObject();
//        System.out.println(personBak2);
//        System.out.println("读取完毕。。。。。。");
//        inputStream.close();


        System.out.println("内存地址：" + (personBak2 == personBak));
    }

    /**
     * 序列化
     *
     * @throws IOException
     */
    private static void SerializePerson() throws IOException {
        Person person = new Person();
        person.setAge(18);
        person.setName("菜鸟a");
        person.setId(1);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("person"));

        outputStream.writeObject(person);
        outputStream.flush();
        System.out.println(person);
        System.out.println("hashcode：" + person.hashCode());
        System.out.println("序列化完成。。。。。。");

        person.setAge(81);
        person.setName("菜鸟b");
        person.setId(2);

        outputStream.writeObject(person);
        outputStream.flush();
        System.out.println(person);
        System.out.println("hashcode：" + person.hashCode());
        System.out.println("序列化完成。。。。。。");


        outputStream.close();

        System.out.println("内存地址：" + (person == person));
//        ObjectOutputStream outputStream2 = new ObjectOutputStream(new FileOutputStream("person1"));
//        outputStream2.writeObject(person);
//        outputStream2.close();
//        System.out.println(person);
//        System.out.println("序列化完成。。。。。。");
    }

}
