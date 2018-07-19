package com.funi.spring.proxy.cglib;

public class SingleDog implements Person {
    @Override
    public void findObject() {
        System.out.println(this.getClass());
        System.out.println("我不想做单身狗");
        System.out.println("我要找对象");
    }
}
