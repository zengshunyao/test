package category.io.bio.basic;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

public class BIOClient {
    public static void main(String[] args) {

        try {
            //建立通信对象
            Socket client = new Socket("localhost", 8080);
            //活得输出流
            OutputStream sender = client.getOutputStream();
            //生成发送内容
            String context = UUID.randomUUID().toString();

            //发送内容
            sender.write(context.getBytes());
            //刷新
            sender.flush();
            //关闭
            sender.close();

            //关闭
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
