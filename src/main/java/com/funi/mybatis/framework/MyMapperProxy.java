package com.funi.mybatis.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyMapperProxy<T> implements InvocationHandler {
    private MySqlSession sqlSession;
    private Class<T> mapperInterface;


    public MyMapperProxy(MySqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().getClass().getName().equals(MyBootStarp.nameSpace)) {
            String sql = MyBootStarp.methodSqlMapperIng(method.getName());
            System.out.println(String.format("sql:{%s},param:{%s}", sql, args));
            sqlSession.selectOne(sql,args);
        }
        return null;
    }
}
