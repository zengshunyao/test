package distributedcomputer.rmi.server;

import distributedcomputer.rmi.service.SayService;
import distributedcomputer.rmi.service.impl.SayServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/7/16 10:39
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class RMIServer {
    public static void main(String[] args) {
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry();
            registry.list();
            //说明已经有RMIService了不需要在创建一个新的了
            System.out.println("Register the exist server!");
        } catch (RemoteException re) {
//            re.printStackTrace();
            try {
                int port = 9999;//RMIService监听的端口，自己指定！
                //在本地创建
                registry = LocateRegistry.createRegistry(port);
                //创建了一个新的RMIService
                System.out.println("Create Registry Server!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            SayService service = new SayServiceImpl();
            // rmi://MyTest/SayService就是对外暴露出的名称
            registry.rebind("rmi://MyTest/SayService", service);
            System.out.println("SayService server start!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //由于是测试用的因此写在了Main方法中如果正常main执行完毕服务会被关闭，
            // 因此需要sleep一会，正常开发过程中这个部分是启动一个新的线程因此不必担心这个问题
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.out.println("SayService server close!");
        }
    }
}
