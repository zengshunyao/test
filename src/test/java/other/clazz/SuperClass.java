package other.clazz;

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
public class SuperClass {
    static {
        System.out.println("superclass init");
    }

    public static int value = 123;
    public static final String HELLOWORLD = "hello world";
}

class SubClass extends SuperClass {
    static {
        System.out.println("subclass init");
    }
}
