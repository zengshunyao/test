package sort.merge;

import java.util.Arrays;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(归并排序)
 * @project_name：test
 * @author Lenovo
 * @date 2017/12/22 11:47 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class Test {
    private static int step = 0;

    public static void main(String[] args) {
        int[] data = new int[]{5, 3, 6, 2, 1, 9, 4, 8, 7};
        System.out.println("排序前的数组：");
        print(data);
//        mergeSort(data);
        sort(data, 0, data.length - 1);
        System.out.println("排序后的数组：");
        print(data);
    }

    public static void mergeSort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    public static void sort(int[] data, int left, int right) {
        if (left >= right)
            return;
        // 找出中间索引
        int center = (left + right) / 2;
        // 对左边数组进行递归
        sort(data, left, center);
        // 对右边数组进行递归
        sort(data, center + 1, right);
        // 合并
        merge(data, left, center, right);
        System.out.println("第" + (step++) + "步:left=" + left + ",center=" + center + ",right=" + right + ";");
        print(data);
    }

    /**
     * 将两个数组进行归并，归并前面2个数组已有序，归并后依然有序
     *
     * @param data      数组对象
     * @param leftBegin 左数组的第一个元素的索引
     * @param leftEnd   左数组的最后一个元素的索引，center+1是右数组第一个元素的索引
     * @param right     右数组最后一个元素的索引
     */
    public static void merge(int[] data, int leftBegin, int leftEnd, int right) {
        // 临时数组
        int[] tmpArr = new int[data.length];
        // 右数组第一个元素索引
        int rightBegin = leftEnd + 1;
        // index 记录临时数组的索引
        int index = leftBegin;
        // 缓存左数组第一个元素的索引
        int begin = leftBegin;

        while (leftBegin <= leftEnd && rightBegin <= right) {
            // 从两个数组中取出最小的放入临时数组
            if (data[leftBegin] <= data[rightBegin]) {
                tmpArr[index++] = data[leftBegin++];
            } else {
                tmpArr[index++] = data[rightBegin++];
            }
        }
        // 剩余部分依次放入临时数组（实际上两个while只会执行其中一个）
        while (rightBegin <= right) {
            tmpArr[index++] = data[rightBegin++];
        }
        while (leftBegin <= leftEnd) {
            tmpArr[index++] = data[leftBegin++];
        }

        // 将临时数组中的内容拷贝回原数组中
        // （原left-right范围的内容被复制回原数组）
//        while (tmp <= right) {
//            data[tmp] = tmpArr[tmp++];
//        }
        System.arraycopy(tmpArr, begin, data, begin, right - begin + 1);
    }

    public static void print(int[] data) {
        System.out.println(Arrays.toString(data));
    }
}
