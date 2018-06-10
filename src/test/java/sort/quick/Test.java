package sort.quick;

import java.util.Arrays;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(快速排序（稳定）)
 * @project_name：test
 * @author Lenovo
 * @date 2017/12/21 21:45 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class Test {

    public static void sort(int[] array, int low, int high) {

        //数据检查
        if (low >= high || array.length == 0 || low < 0 || high >= array.length) {
            return;
        }

        int i = low;//i
        int j = high;//j
        int key = array[i];
        while (i < j) {
            while (i < j && array[j] >= key) {
                j--;
            }
            if (i < j) {
                array[i] = array[j];//替换
                System.out.println("i=" + (i + 1) + ";j=" + (j + 1) + ";key=" + key);
                print(array);
            }
            //var tmp=array[i];
            while (i < j && array[i] <= key) {
                i++;
            }
            if (i < j) {
                array[j] = array[i];//替换
                System.out.println("i=" + (i + 1) + ";j=" + (j + 1) + ";key=" + key);
                print(array);
            }
        }

        //一轮全部完成  key替换到指定位置,确保前面数字都比它小,后边的都比它大
        if (i == j) {
            array[i] = key;//替换
            System.out.println("i=" + (i + 1) + ";j=" + (j + 1) + ";key=" + key);
            print(array);
        }

        //
        if (i > low) {
            sort(array, low, i - 1);
        }

        //
        if (j < high) {
            sort(array, j + 1, high);
        }
    }

    public static void print(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {49, 38, 60, 80, 1, 15, 5, 37, 49};
        //int[] array = {49, 38, 49};
        Test.print(array);
        Test.sort(array, 0, array.length - 1);
        Test.print(array);
    }
}
