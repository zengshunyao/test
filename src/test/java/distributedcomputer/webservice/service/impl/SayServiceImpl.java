package distributedcomputer.webservice.service.impl;

import distributedcomputer.webservice.service.SayService;

import javax.jws.WebService;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/7/16 10:42
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
@WebService
public class SayServiceImpl implements SayService {
    @Override
    public String hello(String name) throws Exception {
        System.out.println("当年线程：" + Thread.currentThread().getName());
        System.out.println(this.hashCode());
        return "hello world !" + name;
    }
}
