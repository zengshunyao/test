package cn.ccsgroup.ccsframework.common.interceptor;

import cn.ccsgroup.ccsframework.common.annotation.AvoidDuplicateSubmission;
import cn.ccsgroup.ccsframework.common.mvc.token.TokenProcessor;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(避免表单重复提交)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class AvoidDuplicateSubmissionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOG = Logger.getLogger(AvoidDuplicateSubmissionInterceptor.class);

    //TOKEN key
    private final static String TOKEN_KEY = "token";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
//        User user = UserUtil.getUser();
        Object user = null;//UserUtil.getUser();
        if (user != null) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Method method = handlerMethod.getMethod();

            AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
            if (annotation != null) {
                final boolean needSaveSession = annotation.needSaveToken();
                if (needSaveSession) {
                    request.getSession(false).setAttribute(TOKEN_KEY, TokenProcessor.getInstance().generateToken());
                }

                final boolean needRemoveSession = annotation.needRemoveToken();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
//                        LOG.warn("please don't repeat submit,[user:" + user.getUsername() + ",url:"
//                                + request.getServletPath() + "]");
                        return false;
                    }
                    request.getSession(false).removeAttribute(TOKEN_KEY);
                }
            }
        }
        return true;
    }

    /**
     * @param request
     * @return
     */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        final String serverToken = (String) request.getSession(false).getAttribute(TOKEN_KEY);
        if (serverToken == null) {
            return true;
        }
        final String clientToken = request.getParameter(TOKEN_KEY);
        if (clientToken == null) {
            return true;
        }
        if (!serverToken.equals(clientToken)) {
            return true;
        }
        return false;
    }
}
