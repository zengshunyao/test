package com.funi.distributedcomputer.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9999);

            while (true) {
                System.out.println("等待接收中：------------");
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String line = null;
                        StringBuilder builder = new StringBuilder();

                        if ((line = bufferedReader.readLine()) != null) {
                            builder.append(line);
                        }
                        System.out.println("接收到数据：" + builder.toString());
                        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                        printWriter.println("收到 信息:" + builder.toString());
                        printWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
