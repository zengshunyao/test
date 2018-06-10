package sort.base;

import java.util.Arrays;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么) 
 * @project_name：test
 * @author Lenovo
 * @date 2017/12/26 21:13 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class Base {
    public static int[] RadixSort(int[] ArrayToSort, int digit) {
        //low to high digit
        for (int k = 1; k <= digit; k++) {
            //temp array to store the sort result inside digit
            int[] tmpArray = new int[ArrayToSort.length];

            //temp array for countingsort
            int[] tmpCountingSortArray = new int[ArrayToSort.length];

            //CountingSort
            for (int i = 0; i < ArrayToSort.length; i++) {
                //split the specified digit from the element
                int tmpSplitDigit = ArrayToSort[i] / (int) Math.pow(10, k - 1)
                        - (ArrayToSort[i] / (int) Math.pow(10, k)) * 10;
                tmpCountingSortArray[tmpSplitDigit] += 1;
            }

            for (int m = 1; m < 10; m++) {
                tmpCountingSortArray[m] += tmpCountingSortArray[m - 1];
            }

            //output the value to result
            for (int n = ArrayToSort.length - 1; n >= 0; n--) {
                int tmpSplitDigit = ArrayToSort[n] / (int) Math.pow(10, k - 1)
                        - (ArrayToSort[n] / (int) Math.pow(10, k)) * 10;
                tmpArray[tmpCountingSortArray[tmpSplitDigit] - 1] = ArrayToSort[n];
                tmpCountingSortArray[tmpSplitDigit] -= 1;
            }

            //copy the digit-inside sort result to source array
            for (int p = 0; p < ArrayToSort.length; p++) {
                ArrayToSort[p] = tmpArray[p];
            }
        }
        return ArrayToSort;
    }

    static void main(String[] args) {
        int[] ary = new int[]{332, 653, 632, 755, 433, 722, 48};
        System.out.println(Arrays.toString(ary));

        int[] res = RadixSort(ary, 3);

        System.out.println(Arrays.toString(res));
    }
}
