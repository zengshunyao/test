package javaBase.io.nio;// $Id$


import java.nio.ByteBuffer;

/**
 * 片式缓冲[部分]
 */
public class SliceBuffer {
    static public void main(String args[]) throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0; i < byteBuffer.capacity(); ++i) {
            byteBuffer.put((byte) i);
        }

        byteBuffer.position(3);
        byteBuffer.limit(7);

        ByteBuffer slice = byteBuffer.slice();

        for (int i = 0; i < slice.capacity(); ++i) {
            byte b = slice.get(i);
            b *= 11;
            slice.put(i, b);
        }

        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());

        while (byteBuffer.remaining() > 0) {
            System.out.println(byteBuffer.get());
        }
    }
}
