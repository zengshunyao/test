package com.funi.spring.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MeiPo implements MethodInterceptor {

    private Person target;

    public Object getInstance(Person target) {
        this.target = target;
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("我要炸天");
        System.out.println("--------炸天开始-----------------");
        //执行目标对象的方法
        Object returnValue = method.invoke(target, objects);
        System.out.println("--------炸天结束-----------------");
        return returnValue;
    }
}
