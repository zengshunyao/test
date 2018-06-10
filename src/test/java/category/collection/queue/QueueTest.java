package category.collection.queue;

import java.util.Collection;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/6/10 12:39
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */

/**
 * java.util.concurrent 中加入了 BlockingQueue 接口和五个阻塞队列类。
 * 它实质上就是一种带有一点扭曲的 FIFO 数据结构。不是立即从队列中添加或者删除元素，
 * 线程执行操作阻塞，直到有空间或者元素可用。
 * 五个队列所提供的各有不同：
 * *ArrayBlockingQueue ：一个由数组支持的有界队列。
 * *LinkedBlockingQueue ：一个由链接节点支持的可选有界队列。
 * *PriorityBlockingQueue ：一个由优先级堆支持的无界优先级队列。
 * *DelayQueue ：一个由优先级堆支持的、基于时间的调度队列。
 * *SynchronousQueue ：一个利用 BlockingQueue 接口的简单聚集（rendezvous）机制。
 */
public class QueueTest {

    public static void main(String[] args) {
        Collection collection;
        ArrayBlockingQueue arrayBlockingQueue;
        Map map;
        Queue queue;

    }
}
