package com.funi.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        //加入这一段可以在磁盘中生成 代理类，让我们看到代理类的真面目        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        final Sourceable source = new Source();
        Sourceable test = (Sourceable) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Sourceable.class}, new InvocationHandler() {
            //@Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //System.out.println("test");
                if ("method".equals(method.getName())) {
                    return method.invoke(source, null);
                }
                return null;
            }
        });
        test.method();
    }
}
