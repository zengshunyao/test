package sort;

import java.util.Arrays;

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
public class Base {
    public static int count = 0;

    /**
     * @param arr
     */
    public static void print(int arr[]) {
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        ++count;
    }

    public void t() {

//        Arrays.sort();
    }
}
