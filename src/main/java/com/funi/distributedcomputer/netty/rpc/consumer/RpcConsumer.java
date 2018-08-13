package com.funi.distributedcomputer.netty.rpc.consumer;

import com.funi.distributedcomputer.netty.rpc.api.IRpcCalc;
import com.funi.distributedcomputer.netty.rpc.api.IRpcHello;
import com.funi.distributedcomputer.netty.rpc.consumer.proxy.RpcProxy;

public class RpcConsumer {

    public static void main(String[] args) {
        hello();

//        math();
    }

    /**
     * 打招呼
     */
    public static void hello() {
        //本机一个人在玩
        //自娱自乐
        //肯定是用动态代理来实现的,传给它一个接口，返回一个实例，伪代理
        IRpcHello rpcHello = RpcProxy.create(IRpcHello.class);


        String r = rpcHello.hello("Tom老师");
        System.out.println(r);
    }

    /**
     * 算数
     */
    public static void math() {
        int a = 8, b = 2;
        IRpcCalc calc = RpcProxy.create(IRpcCalc.class);
        System.out.println(a + " + " + b + " = " + calc.add(a, b));
        System.out.println(a + " - " + b + " = " + calc.sub(a, b));
        System.out.println(a + " * " + b + " = " + calc.mult(a, b));
        System.out.println(a + " / " + b + " = " + calc.div(a, b));
    }
}
