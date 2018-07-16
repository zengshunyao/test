package distributedcomputer.rmi.client;

import distributedcomputer.rmi.service.SayService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/7/16 10:40
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class RMIClient {
    public static void main(String[] args) {
        try {
            //RMIService的地址这里都是在本地执行
            String hostName = "localhost";
            //RMIService监听的端口
            int port = 9999;
            Registry registry = LocateRegistry.getRegistry(hostName, port);
            //RMIServer注册时暴露出来的名称
            SayService hello = (SayService) registry.lookup("rmi://MyTest/SayService");
            String message = hello.hello("bug");
            System.out.println(hello.hashCode());
            System.out.println("当年线程：" + Thread.currentThread().getName());
            System.out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
