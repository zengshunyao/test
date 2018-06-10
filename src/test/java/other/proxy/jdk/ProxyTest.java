package other.proxy.jdk;

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
public class ProxyTest {

    //@Test
    public static void testProxy() throws Throwable {
        //创建目标对象
        TestService testService = new TestServiceImpl();
        //实例化invocationHandler
        MyInvocationHandler invocationHandler = new MyInvocationHandler(testService);
        //根据目标对象获得代理对象
        TestService testService1 = (TestService) invocationHandler.getProxy();
        //调用代理对象的方法
        testService1.add(1, 2);
    }

    public static void main(String[] args) throws Throwable {
        testProxy();
    }
}
