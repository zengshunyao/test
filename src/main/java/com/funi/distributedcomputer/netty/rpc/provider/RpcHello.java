package com.funi.distributedcomputer.netty.rpc.provider;

import com.funi.distributedcomputer.netty.rpc.api.IRpcHello;

public class RpcHello implements IRpcHello {

    @Override
    public String hello(String name) {
        try {
            System.out.println("服务端-------------------开始---------------");
            System.out.println("正在干活" + this.getClass().getName());
            System.out.println(Thread.currentThread().getName());
            System.out.println(RpcHello.class.getMethod("hello", new Class[]{String.class}));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } finally {
            System.out.println("服务端-------------------结束---------------");
        }

        return "Hello , " + name + "!";
    }

}
