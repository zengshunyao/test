package sort.insert;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(折半插入排序（二分插入排序）)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
//折半插入排序的算法思想：
//算法的基本过程：
//（1）计算 0 ~ i-1 的中间点，用 i 索引处的元素与中间值进行比较，如果 i 索引处的元素大，
// 说明要插入的这个元素应该在中间值和刚加入i索引之间，反之，就是在刚开始的位置 到中间值的位置，这样很简单的完成了折半；
//（2）在相应的半个范围里面找插入的位置时，不断的用（1）步骤缩小范围，不停的折半，
// 范围依次缩小为 1/2 1/4 1/8 .......快速的确定出第 i 个元素要插在什么地方；
//（3）确定位置之后，将整个序列后移，并将元素插入到相应位置。
public class BinaryInsert {
}
