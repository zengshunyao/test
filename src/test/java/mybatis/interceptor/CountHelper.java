package mybatis.interceptor;

import java.util.concurrent.atomic.AtomicInteger;

/**********************************************************************
 * &lt;p&gt;文件名：CountHelper.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyaojava
 * @create 2020/3/5 20:48 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public final class CountHelper {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static int incrementAndGet() {
        return atomicInteger.incrementAndGet();
    }
}
