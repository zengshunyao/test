package mybatis.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.util.Properties;

/**********************************************************************
 * &lt;p&gt;文件名：ParameterHandlerPlugin.java &lt;/p&gt;
 * &lt;p&gt;文件描述：sql插件(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyaojava
 * @create 2020/3/5 20:43 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */

@Intercepts(//getParameterObject、setParameters
        {
                @Signature(
                        type = ParameterHandler.class,
                        method = "getParameterObject",
                        args = {}
                ),
                @Signature(
                        type = ParameterHandler.class,
                        method = "setParameters",
                        args = {PreparedStatement.class}
                )
        }
)
public class ParameterHandlerInterceptor implements Interceptor {
    private Properties properties = new Properties();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ParameterHandlerInterceptor() {
        logger.info("mybatis constructor {}----->{}", this.getClass().getSimpleName(), CountHelper.incrementAndGet());
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("mybatis intercept {}:{} before----->{}", this.getClass().getSimpleName(), invocation.getMethod().getName(), CountHelper.incrementAndGet());
        // implement pre processing if need
        Object returnObject = invocation.proceed();
        logger.info("mybatis intercept {}:{} after----->{}", this.getClass().getSimpleName(), invocation.getMethod().getName(), CountHelper.incrementAndGet());
        // implement post processing if need
        return returnObject;
    }

    @Override
    public Object plugin(Object target) {
        logger.info("mybatis plugin {}:{}----->{}", this.getClass().getSimpleName(), target.getClass().getSimpleName(), CountHelper.incrementAndGet());
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = new Properties();
        logger.info("mybatis setProperties {}:{}", this.getClass().getSimpleName(), CountHelper.incrementAndGet());
    }
}
