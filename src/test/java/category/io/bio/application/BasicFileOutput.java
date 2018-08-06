package category.io.bio.application;

import java.io.*;

/**
 * Created by Lenovo on 2015/5/23.
 */
public class BasicFileOutput {
    static String file = "D:\\basic.java";

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new StringReader(
//                InputStreamTest.read("src/BasicFileOutput.java")));
                InputStreamTest.read("E:\\Test\\IdeaProjects\\test\\src\\main\\java\\com\\study\\test\\io\\BasicFileOutput.java")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                file)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }
        out.close();
        System.out.println(InputStreamTest.read(file));
    }
}
