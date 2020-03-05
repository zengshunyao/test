package mybatis.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**********************************************************************
 * &lt;p&gt;文件名：StatementHandlerInterceptor.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyaojava
 * @create 2020/3/5 20:59 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
@Intercepts(//prepare、parameterize、batch、update、query
        {
                @Signature(
                        type = StatementHandler.class,
                        method = "prepare",
                        args = {Connection.class, Integer.class}
                ),
                @Signature(
                        type = StatementHandler.class,
                        method = "parameterize",
                        args = {Statement.class}
                ),
                @Signature(
                        type = StatementHandler.class,
                        method = "batch",
                        args = {Statement.class}
                ),
                @Signature(
                        type = StatementHandler.class,
                        method = "update",
                        args = {Statement.class}
                ),
                @Signature(
                        type = StatementHandler.class,
                        method = "query",
                        args = {Statement.class, ResultHandler.class}
                )
        }
)
public class StatementHandlerInterceptor implements Interceptor {
    private Properties properties = new Properties();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public StatementHandlerInterceptor() {
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
