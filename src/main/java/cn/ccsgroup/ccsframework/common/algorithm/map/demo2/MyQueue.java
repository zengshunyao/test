package cn.ccsgroup.ccsframework.common.algorithm.map.demo2;

import java.util.concurrent.LinkedBlockingQueue;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/6/8 12:20
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class MyQueue<E> extends LinkedBlockingQueue<E> {
    public boolean EnQueue(E j) {
        return this.offer(j);
    }

    public boolean empty() {
        return this.isEmpty();
    }

    public E DeQueue() {
        return this.poll();
    }
}
