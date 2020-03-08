package lock.aqs.utils;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**********************************************************************
 * &lt;p&gt;文件名：UnsafeInstance.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyaojava
 * @create 2020/3/8 18:49 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class UnsafeInstance {
    /**
     *
     * @return
     */
    public static Unsafe reflectGetUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getField("theUnsafe");
            theUnsafe.setAccessible(true);

            Object obj = theUnsafe.get(null);
            return obj instanceof Unsafe ? Unsafe.class.cast(obj) : null;
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
    }
}
