package category.invocation.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class MyInterceptor implements MethodInterceptor {
    private Object target;

    public MyInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args,
                            MethodProxy invocation) throws Throwable {
        //1.记录日志 2.时间统计开始   3.安全检查
        //预处理
        //前置条件判断
        boolean ok = true;
        Object retVal = null;
        if (!ok) {//不满足条件
            throw new RuntimeException("出错了");
        } else {//调用目标对象的某个方法
            retVal = invocation.invoke(target, args);
        }
        //4.时间统计结束
        return retVal;
    }

    public static Object proxy(Object target) {
        return Enhancer.create(target.getClass(), new MyInterceptor(target));
    }
}
