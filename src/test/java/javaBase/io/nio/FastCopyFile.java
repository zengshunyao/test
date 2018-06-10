package javaBase.io.nio;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

/**
 * 快速拷贝文件
 */
public class FastCopyFile {
    static public void main(String args[]) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java FastCopyFile infile outfile");
            System.exit(1);
        }

        String infile = args[0];
        String outfile = args[1];

        FileInputStream fileInputStream = new FileInputStream(infile);
        FileOutputStream fileOutputStream = new FileOutputStream(outfile);

        FileChannel fileInputChannel = fileInputStream.getChannel();
        FileChannel fileOutputChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            buffer.clear();

            int r = fileInputChannel.read(buffer);

            if (r == -1) {
                break;
            }

            buffer.flip();

            fileOutputChannel.write(buffer);
        }
    }
}
