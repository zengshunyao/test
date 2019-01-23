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
    /**
     * before HandlerAdapter invokes the handler.
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        MDC.put("threadKey", String.valueOf(System.nanoTime()));
        RunBinder.clear();
        return true;
    }

    /**
     * after HandlerAdapter invokes the handler.
     * But before the DispatcherServlet renders the view.
     *
     * @param request
     * @param response
     * @param o            你的controller
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object o,
                           ModelAndView modelAndView)
            throws Exception {
        //清理资源，防止OOM
        RunBinder.clear();
        MDC.clear();
    }

    /**
     * after rendering the view
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
