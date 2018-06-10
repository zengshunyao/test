package category.invocation.jdk;

import category.invocation.UserService;

/**
 * Created by Lenovo on 2015/5/23.
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
//        UserService userService = new UserServiceImpl();
        UserService userService = null;
        try {
            userService = (UserService) Class.forName("test.invocation.UserServiceImpl", true, Thread.currentThread().getContextClassLoader()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        MyInvocationHandler invocationHandler = new MyInvocationHandler(
                userService);

//        UserService proxy = (UserService) invocationHandler.getProxy();
        UserService proxy = null;
        try {
            proxy = invocationHandler.getProxy(UserService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        proxy.add();
    }
}
