package distributedcomputer.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(SE和SEI的实现类)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/7/16 10:41
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
@WebService
public interface SayService {
    /**
     * SEI的方法
     * @param name
     * @return
     * @throws Exception
     */
    @WebMethod
    public String hello(String name) throws Exception;
}

