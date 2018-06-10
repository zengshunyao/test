package sort.bubble;

import sort.Base;

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
public class Bubble extends Base {
    public void sort(int arr[]) {
        int temp = 0; // 用来交换的临时数
        // 要遍历的次数
        for (int i = 0; i < arr.length - 1; i++) {
            // 从后向前依次的比较相邻两个数的大小，遍历一次后，把数组中第i小的数放在第i个位置上
            for (int j = arr.length - 1; j > i; j--) {
                // 比较相邻的元素，如果前面的数大于后面的数，则交换
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
            System.out.format("第 %d 趟：", i);
            print(arr);
        }
    }

    public static void main(String[] args) {
//        int arr[] = {9,8,7,6,5,4,3,2,1};
        int arr[] = {1, 3, 5, 7, 9, 2, 4, 6, 8};
        Bubble bubble = new Bubble();
        bubble.print(arr);
        bubble.sort(arr);
        //System.out.println(count);
    }
}
