package category.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Lenovo on 2015/5/23.
 */
public class StandardIO {
    public static void main(String[] args) throws IOException {
//        testOriginal();
        testScaner();
    }

    public static void testOriginal() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null && s.length() != 0)
            System.out.println(s);
    }

    public static void testScaner() throws IOException {
        Scanner in = new Scanner(System.in);
        String s;
        while ((s = in.next()) != null && s.length() != 0) {
            System.out.println(s);
        }
    }
}
