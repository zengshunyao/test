package com.funi.distributedcomputer.netty.rpc.provider;

import com.funi.distributedcomputer.netty.rpc.api.IRpcCalc;

public class RpcCalc implements IRpcCalc {

    @Override
    public int add(int a, int b) {
        print();
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        print();
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        print();
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        print();
        return a / b;
    }

    /**
     * 打印
     */
    public void print() {
        System.out.println("服务端---当前线程" + Thread.currentThread().getName());
    }
}
