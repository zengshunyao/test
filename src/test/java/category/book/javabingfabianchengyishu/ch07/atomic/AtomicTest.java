package category.book.javabingfabianchengyishu.ch07.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/17 16:52
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class AtomicTest {
//    @Test
    public void main() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.addAndGet(9);
    }

    static int[] value = new int[]{1, 2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
        System.out.println();
    }

    public void t(){
        AtomicMarkableReference atomicMarkableReference=new AtomicMarkableReference(null,true);
    }
}
