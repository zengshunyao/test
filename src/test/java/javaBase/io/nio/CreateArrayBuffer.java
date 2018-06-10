package javaBase.io.nio;

import java.nio.ByteBuffer;

/**
 * 字节数组
 */
public class CreateArrayBuffer {
    static public void main(String args[]) throws Exception {
        byte array[] = new byte[1024];

        ByteBuffer byteBuffer = ByteBuffer.wrap(array);

        byteBuffer.put((byte) 'a');
        byteBuffer.put((byte) 'b');
        byteBuffer.put((byte) 'c');

        byteBuffer.flip();

        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
    }
}
