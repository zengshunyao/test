package category.io.bio.application;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Lenovo on 2015/5/23.
 */
public class InputStreamTest {
    InputStream inputStream;
    AutoCloseable autoCloseable;
    StringBufferInputStream stringBufferInputStream;
    FileInputStream fileInputStream;
    PipedInputStream pipedInputStream;
    SequenceInputStream sequenceInputStream;
    FilterInputStream filterInputStream;
    OutputStreamWriter outputStreamWriter;
    Scanner scanner;
    RandomAccessFile randomAccessFile;

    public static String read(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s;
        StringBuffer sb = new StringBuffer();
        while ((s = br.readLine()) != null) {
            sb.append(s + "\n");
        }
        br.close();
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(read("src/InputStreamTest.java"));
        String path = new File("fdsf").getAbsolutePath().toString();
        System.out.println(path);
        System.out.println(read("E:\\Test\\IdeaProjects\\test\\src\\test\\java\\category\\io\\" +
                "InputStreamTest.java"));
    }
}
