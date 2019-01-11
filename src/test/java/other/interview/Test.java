package other.interview;

import java.util.LinkedHashMap;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class Test {
    public static void main(String[] args) {
//        test4();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("sss", null);
    }

    public static void test1() {
        int[] a = new int[4];
        a[0] = 1;
        a[3] = 4;
        for (int i = -0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void test2() {
        int i = 5;
        boolean flag = (i++ > 5) && (++i > 6 || test3());
        System.out.println(i);
        System.out.println(flag);
    }

    public static boolean test3() {
        return false;
    }

    public static boolean test4() {
        String s = null;
        //assert s != null;
        //int a= s != null ? s.length() : 0;
//        if (s != null) {
        try {
            s.length();
//        }
        } catch (NullPointerException e) {
            System.out.println("end1");
        } catch (RuntimeException e) {
            System.out.println("end2");
        } catch (Exception e) {
            System.out.println("end3");
        } finally {
            System.out.println("end4");
        }
        return false;
    }
}
