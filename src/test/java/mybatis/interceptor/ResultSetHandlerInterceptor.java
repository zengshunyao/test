package mybatis.interceptor;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.Properties;

/**********************************************************************
 * &lt;p&gt;文件名：ResultSetHandlerInterceptor.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyaojava
 * @create 2020/3/5 20:56 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
@Intercepts(//handleResultSets、handleOutputParameters
        {
                @Signature(
                        type = ResultSetHandler.class,
                        method = "handleResultSets",
                        args = {Statement.class}
                ),
                @Signature(
                        type = ResultSetHandler.class,
                        method = "handleOutputParameters",
                        args = {CallableStatement.class}
                )
        }
)
public class ResultSetHandlerInterceptor implements Interceptor {
    private Properties properties = new Properties();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResultSetHandlerInterceptor() {
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
