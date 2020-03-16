package jvm;

/**********************************************************************   
 * &lt;p&gt;文件名：StrIntern.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyaojava
 * @create 2020/3/17 0:55 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class StrIntern {

    public static void main(String[] args) {
        //一次放开一个多行注释运行
        //false,true,true
//        String s = new String("1");
//        s.intern();//
//        String s2 = "1";//常量池中
//        System.out.println(s == s2);
//        String s3 = new String("1") + new String("1");
//        new StringBuilder().append("").toString();
//        s3.intern();//
//        String s4 = "11";
//        System.out.println(s3.intern() == s4);
//        System.out.println(s3 == s4);



        //false,false,true
        String s = new String("1");
        String s2 = "1";
        s.intern();
        System.out.println(s == s2);
        String s3 = new String("1") + new String("1");
        String s4 = "11";
        s3.intern();
        System.out.println(s3 == s4);
        System.out.println(s3.intern() == s4);

        //+连接但编译器不优化
        //true,true
//        String s1 = new String("xy") + "z";
//        String s2 = s1.intern();
//        System.out.println(s1 == s1.intern());
//        System.out.println(s1 + "-----" + s2);
//        System.out.println(s2 == s1.intern());


        // 一般情况
        //false,false,true
//        String s1 = new String("xyz");
//        String s2 = s1.intern();
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s1.intern());
//        System.out.println(s1 + " " + s2);
//        System.out.println(s2 == s1.intern());


        //编译器优化
        //true,true
//        String s1 = "xy" + "z";
//        String s2 = s1.intern();
//        System.out.println(s1 == s1.intern());
//        System.out.println(s1 + " " + s2);
//        System.out.println(s2 == s1.intern());
    }
}
