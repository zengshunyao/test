package category.book.shenrujvm.ch08;

/**********************************************************************
 * &lt;p&gt;文件名：TestClassLoader.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2019/3/25 17:13
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class TestClassLoader {
    public static void main(String[] args) {
        System.out.println(Child.a + Child.b);
    }
}

class Parent {
    public static int a = 1;

    static {
        System.out.println("parent init");
    }
}

class Child extends Parent {
    public static int b = 2;

    static {
        System.out.println("Child init");
    }
}
