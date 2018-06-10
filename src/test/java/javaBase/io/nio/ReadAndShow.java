package javaBase.io.nio;


import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 读取并显示
 */
public class ReadAndShow {
    static public void main(String args[]) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("readandshow.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        fileChannel.read(byteBuffer);

        byteBuffer.flip();

        int i = 0;
        while (byteBuffer.remaining() > 0) {
            byte b = byteBuffer.get();
            System.out.println("Character " + i + ": " + ((char) b));
            i++;
        }
        fileInputStream.close();
    }
}
