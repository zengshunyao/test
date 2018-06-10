import java.awt.*;

/**
 * Created by Lenovo on 2015/6/9.
 */
public class PassByValue {

    public static void modifyValue(Point pt, int j) {
        //pt，j为引参
        pt.setLocation(5, 5);       // 1
        j = 15;
        System.out.println(pt.hashCode());
        System.out.println("During modifyPoint" + "p=" + pt + "and j = " + j);
    }

    public static void main(String[] args) {
        Point p = new Point();     // 2
        int j = 10;
        System.out.println("Before modifyPoint" + "p = " + p + "and j = " + j);
        modifyValue(p, j);          // 3
        System.out.println("After modifyPoint" + "p = " + p + "and j = " + j);
        System.out.println(p.hashCode());
    }
}
