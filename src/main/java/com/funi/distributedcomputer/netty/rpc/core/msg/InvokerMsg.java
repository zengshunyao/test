package com.funi.distributedcomputer.netty.rpc.core.msg;

import java.io.Serializable;
import java.util.Arrays;

public class InvokerMsg implements Serializable {

    private String className;    //服务名称
    private String methodName;    //调用哪个方法
    private Class<?>[] parames;    //参数列表
    private Object[] values;    //参数值

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParames() {
        return parames;
    }

    public void setParames(Class<?>[] parames) {
        this.parames = parames;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "InvokerMsg{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parames=" + (parames == null ? null : Arrays.asList(parames)) +
                ", values=" + (values == null ? null : Arrays.asList(values)) +
                '}';
    }
}
