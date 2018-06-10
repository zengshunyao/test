package sort.quick;

import sort.Base;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(快速排序)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
//快速排序（稳定）
//描述：
//一趟快速排序的算法是：
//1）设置两个变量i、j，排序开始的时候：i=0，j=N-1；
//2）以第一个数组元素作为关键数据，赋值给key，即key=A[0]；
//3）从j开始向前搜索，即由后开始向前搜索(j--)，找到第一个小于key的值A[j]，将A[j]和A[i]互换；
//4）从i开始向后搜索，即由前开始向后搜索(i++)，找到第一个大于key的A[i]，将A[i]和A[j]互换；
//5）重复第3、4步，直到i=j； (3,4步中，没找到符合条件的值，即3中A[j]不小于key,
// 4中A[i]不大于key的时候改变j、i的值，使得j=j-1，i=i+1，直至找到为止。找到符合条件的值，
// 进行交换的时候i， j指针位置不变。另外，i==j这一过程一定正好是i+或j-完成的时候，此时令循环结束）。

public class Quick extends Base {

    public static void main(String[] args) {
//        int arr[] = {9,8,7,6,5,4,3,2,1};
        int arr[] = {1, 3, 5, 7, 9, 2, 4, 6, 8};
        //Quick quick = Quick;
        Quick.print(arr);
        Quick.sort(arr, 0, arr.length - 1);
        System.out.println(count);
    }

    /**
     * @param arr  数组
     * @param low  低位
     * @param high 高位
     */
    public static void sort(int arr[], int low, int high) {
        if (low >= high || arr.length == 0 || low < 0 || high >= arr.length) {
            return;
        }
        int l = low;//i
        int h = high;//j
        int key = arr[low];//即key

        while (l < h) {
            while (l < h && arr[h] >= key)
                h--;

            if (l < h) {
                swap(arr, h, l);
                l++;
                System.out.println("l=" + (l + 1) + ";h=" + (h + 1) + ";key=" + key);
                print(arr);
            }

            while (l < h && arr[l] <= key)
                l++;

            if (l < h) {
                swap(arr, h, l);
                h--;
                System.out.println("l=" + (l + 1) + ";h=" + (h + 1) + ";key=" + key);
                print(arr);
            }
        }
        if (l > low) sort(arr, low, l - 1);
        if (h < high) sort(arr, l + 1, high);
    }
}
