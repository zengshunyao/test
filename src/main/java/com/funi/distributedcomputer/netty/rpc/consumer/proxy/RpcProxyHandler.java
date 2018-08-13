package com.funi.distributedcomputer.netty.rpc.consumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RpcProxyHandler extends ChannelInboundHandlerAdapter {

    //保存结果
    private Object result;

    public Object getResult() {
        return this.result;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("当前线程----RpcProxyHandler.channelRead------" + Thread.currentThread().getName() + "------");
        System.out.println(System.currentTimeMillis() + "--------------channelRead------------------" + msg);
        this.result = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
