package category.invocation.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Lenovo on 2015/5/23.
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;
    private Class clazz;

    public MyInvocationHandler() {
        super();
    }

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    public MyInvocationHandler(Object target, Class clazz) {
        super();
        this.target = target;
        this.clazz = clazz;
    }

    public Object getProxy() {
        Object obj = Proxy.newProxyInstance(Thread.currentThread()
                        .getContextClassLoader(), target.getClass().getInterfaces(),
                this);

//        if (obj instanceof (target.getClass())){
        return (obj);
//        }
//        return null;
    }

    public <T> T getProxy(Class<T> requiredType) throws Exception {
        Object bean = getProxy();
        if ((requiredType != null) && (!requiredType.isAssignableFrom(bean.getClass()))) {
            throw new Exception();
        }
        System.out.println(bean.getClass());
        return (T) bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("----- before -----");
        Object result = method.invoke(target, args);
        System.out.println("----- after -----");
        return result;
    }
}
