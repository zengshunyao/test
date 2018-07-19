package com.funi.distributedcomputer.multicastdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class MulticastServer {
    public static void main(String[] args) {
        try {
            //D类地址用于多点广播（Multicast）。
//            D类IP地址第一个字节以“1110”开始，它是一个专门保留的地址。它并不指向特定的网络，目前这一类地址被用在多点广播（Multicasting）中。多点广播地址用来一次寻址一组计算机，它标识共享同一协议的一组计算机。
//            A类IP段　1.0.0.0 到126.255.255.255 (0段和127段不使用)
//            B类IP段　128.0.0.0 到191.255.255.255
//            C类IP段　192.0.0.0 到223.255.255.255
//            D类IP段 224.0.0.0 到 239.255.255.255。
//            D类的IP地址不标识网络，其地址覆盖范围为224.0.0.0~239.255.255.255。
            InetAddress group = InetAddress.getByName("224.5.6.7");
            MulticastSocket socket = new MulticastSocket();
            System.out.println("-------开始发组播------");
            for (int i = 0; i < 10; i++) {
                System.out.println("--第" + (i + 1) + "轮-----组播发送开始------");
                String data = "Hello mimi" + (i + 1);
                byte[] bytes = data.getBytes();
                socket.send(new DatagramPacket(bytes, bytes.length, group, 8888));
                System.out.println("--第" + (i + 1) + "轮-----组播发送结束------");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
