package com.funi.spring.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MeiPo implements InvocationHandler {

    private Person target;

    public Object getInstance(Person target) {
        this.target=target;
        Class clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我要炸天");
        System.out.println("--------炸天开始-----------------");
        Object result=method.invoke(target,args);
        System.out.println("--------炸天结束-----------------");
        return result;
    }
}
