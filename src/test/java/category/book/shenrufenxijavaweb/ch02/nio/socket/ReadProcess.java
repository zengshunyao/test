package category.book.shenrufenxijavaweb.ch02.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/5/8 23:47
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class ReadProcess {
    public static void main(String[] args) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = SocketChannel.open();
        //socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress=new InetSocketAddress("http://jenkov.com", 80);
        socketChannel.socket().connect(inetSocketAddress);
        while (!socketChannel.finishConnect()) {
            int bytesRead = socketChannel.read(buf);
            System.out.println();
            while (bytesRead > 0) {
                System.out.println("read " + bytesRead + " byte");
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.clear();
            }
            System.out.println();
        }
    }
}
