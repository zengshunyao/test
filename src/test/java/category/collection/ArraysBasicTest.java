package category.collection;

import java.util.Arrays;

/**
 * Created by Lenovo on 2015/5/20.
 */
public class ArraysBasicTest {
    //@Test
    public void testSortInteger() {
        int data[] = {10, 8, 9, 1, 2, 5, 98, 3, 7, 66};
        Arrays.sort(data);
        for (int i : data) {
            System.out.print(i + " ");
        }
    }

    //@Test
    public void testSortChar() {
        char data[] = {'D', 'B', 'E', 'C', 'H', 'A', 'Y', 'G', 'I', 'O'};
        Arrays.sort(data);
        for (char i : data) {
            System.out.print(i + " ");
        }
        Arrays.asList(data);
    }
}
