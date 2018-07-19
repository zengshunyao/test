package com.funi.distributedcomputer.multicastdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastClient {
    public static void main(String[] args) {
        InetAddress group = null;
        try {
            group = InetAddress.getByName("224.5.6.7");

            MulticastSocket socket = new MulticastSocket(8888);
            //加到指定的组里面
            socket.joinGroup(group);
            byte[] buf = new byte[256];
            int count = 0;
            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                socket.receive(datagramPacket);
                count++;
                System.out.println("接收到第" + (count) + "轮的组播：" + new String(datagramPacket.getData()));
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
