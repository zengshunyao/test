package com.funi.spring.proxy.dynamic;

public class TestMain {

    public static void main(String[] args) {

        Person person = new SingleDog();
//        person.findObject();

        Person person1= (Person) new MeiPo().getInstance(person);
        person1.findObject();
    }

}
