package javaBase.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 拷贝文件
 */
public class CopyFile {
    static public void main(String args[]) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java CopyFile infile outfile");
            System.exit(1);
        }

        String infile = args[0];
        String outfile = args[1];

        FileInputStream fileInputStream = new FileInputStream(infile);
        FileOutputStream fileOutputStream = new FileOutputStream(outfile);

        FileChannel fileInputChannel = fileInputStream.getChannel();
        FileChannel fileOutputChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            byteBuffer.clear();
            int r = fileInputChannel.read(byteBuffer);
            if (r == -1) {
                break;
            }
            byteBuffer.flip();
            fileOutputChannel.write(byteBuffer);
        }
    }
}
