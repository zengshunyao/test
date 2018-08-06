package category.io.bio.application;

import java.io.StringReader;

/**
 * Created by Lenovo on 2015/5/23.
 */
public class MemoryInput {
    public static void main(String[] args) throws Exception {
        StringReader in = new StringReader(
                InputStreamTest.read("src/MemoryInput.java"));
        int c;
        while ((c = in.read()) != -1)
            System.out.println((char) c);
    }
}
