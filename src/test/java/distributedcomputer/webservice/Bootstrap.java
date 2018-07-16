package distributedcomputer.webservice;

import distributedcomputer.webservice.service.impl.SayServiceImpl;

import javax.xml.ws.Endpoint;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/7/16 14:23
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class Bootstrap {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/my/hello", new SayServiceImpl());
        System.out.println("publish success!");
        //http://localhost:8888/my/hello?wsdl 浏览器访问
    }
}
