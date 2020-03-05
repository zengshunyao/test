package mybatis.interceptor;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**********************************************************************
 * &lt;p&gt;文件名：ExecutorInterceptor.java &lt;/p&gt;
 * &lt;p&gt;文件描述：Executor 拦截器测试(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyaojava
 * @create 2020/3/5 20:37 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
@Intercepts(//update, query, flushStatements, commit, rollback,getTransaction, close, isClosed
        {
                @Signature(
                        type = org.apache.ibatis.executor.Executor.class,
                        method = "update",
                        args = {MappedStatement.class, Object.class}
                ),
                @Signature(
                        type = org.apache.ibatis.executor.Executor.class,
                        method = "query",
                        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
                ),
                @Signature(
                        type = org.apache.ibatis.executor.Executor.class,
                        method = "flushStatements",
                        args = {}
                ),
                @Signature(
                        type = org.apache.ibatis.executor.Executor.class,
                        method = "commit",
                        args = {boolean.class}
                ),
                @Signature(
                        type = org.apache.ibatis.executor.Executor.class,
                        method = "rollback",
                        args = {boolean.class}
                ),
                @Signature(
                        type = org.apache.ibatis.executor.Executor.class,
                        method = "getTransaction",
                        args = {}
                ),
                @Signature(
                        type = org.apache.ibatis.executor.Executor.class,
                        method = "close",
                        args = {boolean.class}
                ),
                @Signature(
                        type = org.apache.ibatis.executor.Executor.class,
                        method = "isClosed",
                        args = {}
                )
        }
)
public class ExecutorInterceptor implements Interceptor {
    private Properties properties = new Properties();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ExecutorInterceptor() {
        logger.info("mybatis constructor {}----->{}", this.getClass().getSimpleName(), CountHelper.incrementAndGet());
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("mybatis intercept {}:{} before----->{}", this.getClass().getSimpleName(), invocation.getMethod().getName(), CountHelper.incrementAndGet());

        Object[] args = invocation.getArgs();

        //onlv query
        if (invocation.getMethod().getName().equals("query")) {
            MappedStatement ms = args[0] instanceof MappedStatement ? MappedStatement.class.cast(args[0]) : null;
            Object object = args[1];
            if (ms != null) {
                logger.info("sql语句：" + ms.getSqlSource().getBoundSql(object).getSql());
            }
            if (object != null) {
                logger.info("参数：" + object.toString());
            }
        }

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
