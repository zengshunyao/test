package category.nio;// $Id$

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFile {
    public static void main(String args[]) throws Exception {
        args = new String[2];
        args[0] = new String("E:/in.txt");
        args[1] = new String("E:/out.txt");
        if (args.length < 2) {
            System.err.println("Usage: java CopyFile infile outfile");
            System.exit(1);
        }

        run(args);
        run4(args);
    }

    public static void run(String args[]) throws IOException {
        long begin = System.currentTimeMillis();
        String infile = args[0];
        String outfile = args[1];

        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);

        FileChannel fcin = fin.getChannel();
        FileChannel fcout = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            buffer.clear();
            int r = fcin.read(buffer);
            if (r == -1) {
                break;
            }
            buffer.flip();
            fcout.write(buffer);
        }
        System.out.println("spend:" + (System.currentTimeMillis() - begin));
    }

    public static void run4(String args[]) throws IOException {
        long begin = System.currentTimeMillis();
        String infile = args[0];
        String outfile = args[1];

        FileInputStream fin = new FileInputStream(infile);
        FileOutputStream fout = new FileOutputStream(outfile);


        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = fin.read(buf)) != -1) {
            fout.write(buf, 0, len);
        }
        System.out.println("spend:" + (System.currentTimeMillis() - begin));
    }
}
