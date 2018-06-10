package cn.ccsgroup.ccsframework.common.interceptor;

import cn.ccsgroup.ccsframework.common.result.RunBinder;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * mvc拦截器
 * <p/>
 * Created by shunyao.zeng on 4/9/14.
 */
public class RunBinderMvcInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        MDC.put("threadKey", String.valueOf(System.nanoTime()));
        RunBinder.clear();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView)
            throws Exception {

        //清理资源，防止OOM
        RunBinder.clear();
        MDC.clear();
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
