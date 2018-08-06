package category.io.nio.application;

/**
 * Created by Lenovo on 2015/6/14.
 */

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.net.InetSocketAddress;
import java.io.IOException;

public class BaiduReader {
    private Charset charset = Charset.forName("UTF-8");// 创建UTF-8字符集
    private SocketChannel channel;

    public void readHTMLContent() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress(
                    "www.baidu.com", 443);
            //step1:打开连接
            channel = SocketChannel.open(socketAddress);
            //step2:发送请求，使用UTF-8编码
            StringBuilder head = new StringBuilder(64);
            head.append("GET" + " HTTP/1.1").append("\n");
            head.append("Host:" + "www.baidu.com\n");
            head.append("Cache-Control:" + "no-cache");
            channel.write(charset.encode(head.toString()));
            //step3:读取数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);// 创建1024字节的缓冲
            while (channel.read(buffer) != -1) {
                buffer.flip();// flip方法在读缓冲区字节操作之前调用。
                System.out.println(charset.decode(buffer));
                // 使用Charset.decode方法将字节转换为字符串
                buffer.clear();// 清空缓冲
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        new BaiduReader().readHTMLContent();
    }
}