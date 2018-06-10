package sort.insert;

import sort.Base;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(直接插入排序)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
//直接插入排序（稳定）
//直接插入排序的算法思路：
//（1） 设置监视哨r[0]，将待插入纪录的值赋值给r[0]；
//（2） 设置开始查找的位置j；
//（3） 在数组中进行搜索，搜索中将第j个纪录后移，直至r[0].key≥r[j].key为止；
//（4） 将r[0]插入r[j+1]的位置上。
public class DirectInsert extends Base {
    public void sort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int key = arr[i];
            for (; j >= 0 && arr[j] > key; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9, 2, 4, 6, 8};
        DirectInsert insert = new DirectInsert();
        insert.print(arr);
        insert.sort(arr);
        insert.print(arr);
    }
}
