package com.util.spring;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;

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
public final class ApplicationContextHolder {

    private static WebApplicationContext webApplicationContext = null;

    /**
     * 懒汉模式
     *
     * @param servletContextEvent
     * @return
     */
    public static WebApplicationContext getWebApplicationContext(ServletContextEvent servletContextEvent) {
        if (webApplicationContext == null) {
            synchronized (ApplicationContextHolder.class) {
                if (webApplicationContext == null) {
                    webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
                }
            }
        }
        return webApplicationContext;
    }


}
