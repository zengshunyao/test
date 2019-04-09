package category.jdk.Java17;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.LongAdder;

/**********************************************************************
 * &lt;p&gt;文件名：ForkJoinDemo.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/4/9 23:21 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class ForkJoinDemo2 {
    final static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        System.out.println("当前CPU处理器数量：" + ForkJoinPool.commonPool().getParallelism());
        System.out.println("当前CPU处理器数量：" + Runtime.getRuntime().availableProcessors());

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        List nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        AddTask addTask = new AddTask(nums);

        forkJoinPool.invoke(addTask);

        forkJoinPool.shutdown();

        System.out.println("计算结果：" + ForkJoinDemo2.longAdder.intValue());

    }

    static class AddTask extends RecursiveAction {

        public AddTask(List<Integer> nums) {
            this.nums = nums;
        }

        private List<Integer> nums;

        @Override
        protected void compute() {
            int size = nums.size();
            if (size > 1) {
                int middle = size / 2;

                List<Integer> leftLists = nums.subList(0, middle);

                AddTask leftAddTask = new AddTask(leftLists);

                List<Integer> rightLists = nums.subList(middle, size);

                AddTask rightAddTask = new AddTask(rightLists);

                this.invokeAll(leftAddTask, rightAddTask);
            } else {
                int cur = nums.get(0);
//                System.out.println(cur);
                ForkJoinDemo2.longAdder.add(cur);
            }
        }
    }
}
