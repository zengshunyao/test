package javaBase.io.nio;

import java.nio.ByteBuffer;

/**
 * 创建一个缓冲buffer
 */
public class CreateBuffer {
    static public void main(String args[]) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put((byte) 'a');
        byteBuffer.put((byte) 'b');
        byteBuffer.put((byte) 'c');

        byteBuffer.flip();

        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
    }
}
