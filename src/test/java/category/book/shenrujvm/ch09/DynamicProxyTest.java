package category.book.shenrujvm.ch09;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**********************************************************************
 * &lt;p&gt;文件名：DynamicProxyTest.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2019/3/21 15:41
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class DynamicProxyTest {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        public Object bind(Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(
                    originalObj.getClass().getClassLoader(),
                    originalObj.getClass().getInterfaces(),
                    this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("此处增加before--------welcome hello");
            return method.invoke(this.originalObj, args);
        }
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
        System.out.println("IHello.getName:" + hello.getClass().getName());
        System.out.println(
                "$Proxy0 输出路径：" +
                        (System.getProperty("user.dir")
                                + File.separatorChar
                                + hello.getClass().getName()
                        ).replaceAll("\\.", "\\" + File.separator));
    }
}
