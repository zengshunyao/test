package com.funi.distributedcomputer.socket;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class SocketClient {
    public static void main(String[] args) {

        for (int j = 0; j < 10; j++) {
            final int th = j;
            new Thread(() -> {
                try {
                    for (int i = 0; i < 10; i++) {
                        Socket socket = new Socket("localhost", 9999);
                        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                        printWriter.println("第" + (th + 1) + "线程发送" + (i + 1) + "次数据发送");
                        printWriter.flush();

                        String line = null;
                        StringBuilder builder = new StringBuilder();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        if ((line = bufferedReader.readLine()) != null) {
                            builder.append(line);
                        }
                        printWriter.close();
                        bufferedReader.close();
                        System.out.println("接收到数据：" + builder.toString());
                        System.out.println("------------------------------------------------------------");
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("第" + (th + 1) + "线程========完了=========");
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }).start();
        }

    }
}
