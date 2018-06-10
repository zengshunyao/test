package sort.insert;

import sort.Base;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(希尔排序)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
//希尔排序（不稳定）
//希尔排序是基于插入排序的以下两点性质而提出改进方法的：
//1、插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率。
//2、但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。

//希尔排序的基本思想是
//（1）把记录按步长 gap 分组，对每组记录采用直接插入排序方法进行排序。
//（2）随着步长逐渐减小，所分成的组包含的记录越来越多，当步长的值减小到 1 时，
// 整个数据合成为一组，构成一组有序记录，则完成排序。
public class Shell extends Base {


    public void sort(int arr[]) {
        for (int d = arr.length / 2; d >= 1; d /= 2) {
//            for (int i = 0; i < arr.length && i + d < arr.length; i++) {
//                for (int k = i + d; k < arr.length; k += d) {
//                    int j = k - d;
//                    int key = arr[k];
//                    for (; j >= 0 && arr[j] > key; j -= d) {
//                        arr[j + d] = arr[j];
//                    }
//                    if (j + d != k) {
//                        arr[j + d] = key;
//                        System.out.println("d=" + d + ";i=" + (1 + i) + ";k=" + (k + 1) + ";");
//                        print(arr);
//                    }
//                }
//            }
            for (int i = d; i < arr.length; i++) {
                int j;
                int pivot = arr[i];
                // 对距离为 gap 的元素组进行排序
                for (j = i - d; j >= 0 && pivot < arr[j]; j = j - d) {
                    arr[j + d] = arr[j];
                }
                if (j + d != i) {
                    arr[j + d] = pivot;
                    System.out.println("d=" + d + ";i=" + (1 + i) + ";");
                    this.print(arr);
                }
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9, 2, 4, 6, 8};
        Shell shell = new Shell();
        shell.print(arr);
        shell.sort(arr);
        shell.print(arr);
    }
}
