package com.funi.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class MinaClinet {

//    public static void main(String[] args) {
//        // 第一步，建立一个connecter
//        NioSocketConnector connecter = new NioSocketConnector();
//
//        // 第二步，设置消息处理的Handler
//        connecter.setHandler(new MinaClinetHandler());
//
//        // 第三步骤，设置过滤器
//        connecter.getFilterChain().addLast("minaClinet", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"))));
//
//        // 第四步骤，连接服务器
//        ConnectFuture future = connecter.connect(new InetSocketAddress("127.0.0.1",9999));
//        // 阻塞等待连接创建
//        future.awaitUninterruptibly();
//
//        BufferedReader inputReader = null ;
//        try {
//            inputReader = new BufferedReader(new InputStreamReader(System.in, "utf-8")); // 从控制台读取的输入内容
//            String s ;
//            while (!(s = inputReader.readLine()).equals("exit")) {
//                sendMsg(future.getSession(), "消息测试--"+s);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void sendMsg(IoSession session , String msg){
//        if(session != null){
//            session.write(msg);
//        }
//    }
}
