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
//        String s = new String("1");
//        String s2 = "1";
//        s.intern();
//        System.out.println(s == s2);
//        String s3 = new String("1") + new String("1");
//        String s4 = "11";
//        s3.intern();
//        System.out.println(s3 == s4);
//        System.out.println(s3.intern() == s4);

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


        String str1 = new StringBuilder("i'm").append(" T").toString();
        System.out.println(str1.intern() == str1);

        //
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

//        这段代码在JDK1.6中运行，会得到两个false，而在JDK1.7和1.8中运行会得到一个true和一个false。这个差异的原因是：
//
//        在JDK1.6中，intern()方法会把首次遇到的字符串实例复制到永久代中，返回的也是永久代中这个字符串实例的引用，
//        而由StringBuilder创建的字符串实例在Java堆上，所以必然不是同一个引用。

//        而JDK1.7中已经将运行时常量池从永久代移除，在Java 堆（Heap）中开辟了一块区域存放运行时常量池。
//        因此intern()返回的引用和由StringBuilder创建的那个字符串实例是同一个。
//        对 str2的比较返回false是因为“java”这个字符串在执行 StringBuilder.toString()之前已经出现过，
//        字符串常量池中已经有它的引用了，不符合“首次出现”的原则，而“i'm T”这个字符串则是首次出现的，因此返回true。
//
//        链接：https://www.jianshu.com/p/5fdf752efc06
    }
}
