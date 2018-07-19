package com.funi.mybatis.framework;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

public class MySqlSession extends DefaultSqlSession implements SqlSession {
    //    private final Executor executor;
//    private final boolean dirty;
//    private final boolean autoCommit;
    private Configuration configuration;

    public MySqlSession(Configuration configuration, Executor executor, boolean autoCommit) {
        super(configuration, executor, autoCommit);
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);
//        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MyMapperProxy<T>(this,clazz,this.configuration.getmeth));
    }
}
