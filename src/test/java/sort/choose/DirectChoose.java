package sort.choose;

import sort.Base;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(直接选择排序)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
//直接选择排序(不稳定)
//n个记录的文件的直接选择排序可经过n-1趟直接选择排序得到有序结果：
//①初始状态：无序区为R[1..n]，有序区为空。
//②第1趟排序
//在无序区R[1..n]中选出关键字最小的记录R[k]，将它与无序区的第1个记录R[1]交换，
// 使R[1..1]和R[2..n]分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区。
//……
//③第i趟排序
//第i趟排序开始时，当前有序区和无序区分别为R[1..i-1]和R[i..n](1≤i≤n-1)。
// 该趟排序从当前无序区中选出关键字最小的记录R[k]，将它与无序区的第1个记录R[i]交换，
// 使R[1..i]和R[i+1..n]分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区。
//这样，n个记录的文件的直接选择排序可经过n-1趟直接选择排序得到有序结果。

public class DirectChoose extends Base {


    public void sort(int arr[]) {
        int k = 0, temp; // 用来交换的临时数
        // 要遍历的次数
        for (int i = 0; i < arr.length - 1; i++) {
            k = i;
            // 从下一个向后依次遍历，查找最小的数，把最小的数和当前数据替换
            for (int j = i + 1, len = arr.length; j > len; j++) {
                // 比较相邻的元素，如果前面的数大于后面的数，则交换
                if (arr[j] < arr[k]) {
                    k = j;
                }
                if (k != i) {
                    temp = arr[i];
                    arr[i] = arr[k];
                    arr[k] = temp;
                }
            }
            System.out.format("第 %d 趟：", i);
            print(arr);
        }
    }

    public static void main(String[] args) {
//        int arr[] = {9,8,7,6,5,4,3,2,1};
        int arr[] = {1, 3, 5, 7, 9, 2, 4, 6, 8};
        DirectChoose directChoose = new DirectChoose();
        directChoose.print(arr);
        directChoose.sort(arr);
        //System.out.println(count);
    }
}
