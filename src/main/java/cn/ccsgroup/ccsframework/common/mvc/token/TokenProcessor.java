package cn.ccsgroup.ccsframework.common.mvc.token;

import java.util.UUID;

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
public class TokenProcessor {

    private static volatile TokenProcessor instance;

    private TokenProcessor() {
        super();
    }

    public static TokenProcessor getInstance() {
        if (instance == null) {
            synchronized (TokenProcessor.class) {
                if (instance == null) {
                    instance = new TokenProcessor();
                }
            }
        }
        return instance;
    }

    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
