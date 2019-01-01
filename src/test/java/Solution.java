import org.omg.CORBA.TRANSACTION_MODE;
import scala.Int;

import java.lang.reflect.Array;
import java.util.*;

/**********************************************************************
 * &lt;p&gt;文件名：Solution.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2018/12/29 23:08 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class Solution {
    /*
     * @param n: An integer
     * @return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
        // write your code here, try to do it without arithmetic operators.
        //1,2,3,4,5,6,7,8,9,10.....100,.....,1000,.....
        //只要乘以5的倍数就会产生0
        //5,10,15,20,25,30...,100...,1000.=>5*(1,2,3,4,5,6,7,..,25..,625..)=>5*5*(1,2,3,4,5,)
        //n/5+n/25+n/625
        long count = 0;
//        long pwr = 25;
//        for (long temp = 5; temp <= n; temp+=5) {
//            // for循环内部的temp都是5的倍数，因此首先进行+1操作
//            count++;
//            pwr = 25;
//            // 判断是不是25、125、625...的倍数，并根据每次pwr的变化进行+1操作
//            while (temp % pwr == 0) {
//                count++;
//                pwr *= 5;
//            }
//        }


        while (n / 5 > 0) {
            count += n / 5;
            n = n / 5;
        }

        return count;
    }

    /**
     * @param k: An integer 0-9
     * @param n: An integer >=1
     * @return: An integer denote the count of digit k in 0..n
     */
    public int digitCounts(int k, int n) {
        // write your code here
        int count = 0;
        if (k == 0) {
            count++;
            while (n / 10 > 0) {
                count += n / 10;
                n = n / 10;
            }
            return count;
        }
        for (int i = 0, j = 0; i <= n; i++) {
            j = i;
//            123-> 3,2,1
            while (j > 0) {
                if ((j % 10 == k)) {
                    count++;
                }
                j /= 10;
            }
        }
        return count;
    }

    /**
     * @param n: An integer
     * @return: the nth prime number as description.
     */
    public int nthUglyNumber(int n) {
        // write your code here
        int num = 1;
        //1,2,3,4,5,6,8,9
        // num = 2^n*3^p*5^q

        //1也是丑数=>2^0*3^0*5^0
        //第n个丑数
        //当n=1   丑数=1
        //你n>1   丑数
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        int UglyNumbers[] = new int[8 * 1024];
        //丑数从小标1开始(含)存
        UglyNumbers[0] = 1;
        //第一个丑数
        UglyNumbers[1] = 1;
        int p = 1;
        int num2 = 0;
        int num3 = 0;
        int num5 = 0;
        while (true) {
            //解决 n=1
            if (p == n) {
                return UglyNumbers[p];
            }
            //找出没出现包含因子2的丑数
            while (true) {
                num2 = UglyNumbers[p2] * 2;
                if (num2 <= UglyNumbers[p]) {
                    p2++;
                } else {
                    break;
                }
            }
            //找出没出现包含因子3的丑数
            while (true) {
                num3 = UglyNumbers[p3] * 3;
                if (num3 <= UglyNumbers[p]) {
                    p3++;
                } else {
                    break;
                }
            }
            //找出没出现包含因子5的丑数
            while (true) {
                num5 = UglyNumbers[p5] * 5;
                if (num5 <= UglyNumbers[p]) {
                    p5++;
                } else {
                    break;
                }
            }

            num = Math.min(Math.min(num2, num3), num5);
            if (num2 == num) {
                p2++;
            } else {
                if (num3 == num) {
                    p3++;
                } else {
                    if (num5 == num) {
                        p5++;
                    }
                }
            }

            UglyNumbers[++p] = num;
            if ((p == n) || (p + 1 >= 8 * 1024)) {
                break;
            }
        }
        return UglyNumbers[p];
    }

    /**
     * @param n:    An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        // write your code here
//        System.out.println("init:" + Arrays.toString(nums));
        if (n > nums.length) {
            return 0;
        }
        return quick_sort(nums, 0, nums.length - 1, n);
    }

    int quick_sort(int nums[], int l, int r, int n) {
        if (l < r) {
            //先成挖坑填数法调整s[]
            int middle = adjustArray(nums, l, r);
            // 递归调用  (第n大的数==第s.length - n小的数)
            if (nums.length - n < middle) {
                return quick_sort(nums, l, middle - 1, n);
            }
            if (nums.length - n > middle) {
                return quick_sort(nums, middle + 1, r, n);
            }
            return nums[middle];
        }
        return nums[nums.length - n];
    }

    //返回调整后基准数的位置
    int adjustArray(int nums[], int l, int r) {
        int i = l, j = r;
        int x = nums[l]; //nums[l]即s[i]就是第一个坑
        while (i < j) {
            // 从右向左找小于x的数来填s[i]
            while (i < j && nums[j] >= x)
                j--;
            if (i < j) {
                nums[i] = nums[j]; //将s[j]填到s[i]中，nums[j]就形成了一个新的坑
                i++;
            }

            // 从左向右找大于或等于x的数来填s[j]
            while (i < j && nums[i] < x)
                i++;
            if (i < j) {
                nums[j] = nums[i]; //将s[i]填到s[j]中，nums[i]就形成了一个新的坑
                j--;
            }
        }
        //退出时，i等于j。将x填到这个坑中。
        nums[i] = x;
//        System.out.println("sort:" + Arrays.toString(nums));
        return i;
    }

    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // write your code here
        int pa = 0;
        int pb = 0;
        int ab[] = new int[A.length + B.length];
        int pab = 0;
        while (pab != A.length + B.length) {
            while (A.length > pa && B.length > pb && A[pa] <= B[pb]) {
                ab[pab++] = A[pa];
                pa++;
            }
            while (A.length > pa && B.length > pb && A[pa] > B[pb]) {
                ab[pab++] = B[pb];
                pb++;
            }
            //A完了
            if (A.length == pa) {
                while (pab != A.length + B.length) {
                    ab[pab++] = B[pb++];
                }
            }
            //B完了
            if (B.length == pb) {
                while (pab != A.length + B.length) {
                    ab[pab++] = A[pa++];
                }
            }
        }
        return ab;
    }

    public static Queue<TreeNode> queue = new LinkedList<>();//用于保存将树结构序列化成字符串时使用来保存
    public static Queue<TreeNode> dataqueue = new LinkedList<>();//用于将字符串结构反序列化成树时使用来保存节点
    public String string = "";

    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        //3,9,20,#,#,15,7
        if (root == null) {
            return "#";
        } else {
            queue.add(root);//根节点不为空，加入队列
            while (!queue.isEmpty()) {//直到队列为空，才跳出循环
                TreeNode treeNode = queue.poll();//每次取出队列的第一的节点
                if (treeNode == null)//如果为空，那么直接输出“#”，不再把该点的左右子节点加入队列
                    string = string + "#" + ",";
                else {//该节点不为空，则把该点的值加上在字符串后面
                    string = string + treeNode.val + ",";
                    queue.add(treeNode.left);//该节点不为空，那么它的左右子节点都要加进去，可能为空，可能不为空，加进去不判断
                    queue.add(treeNode.right);//从队列中取出来才判断是不是为空，为空则没有下一层了
                }
            }
        }

        string = string.lastIndexOf(",") == string.length() - 1 ?
                string.substring(0, string.length() - 1) : string;//将后面的“，”去掉
        return string.equals("#") ? "" : string;

    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if (data.equals("")) {
            return null;
        }
        if (data.equals("#")) {
            return null;
        }
        String nodes[] = data.split(",");
        TreeNode root, head, left, right;
        root = head = left = right = null;
        if (nodes[0].equals("#")) {
            return null;
        }
        root = head = new TreeNode(Integer.valueOf(nodes[0]));
        for (int i = 1; i < nodes.length; i += 2) {
            left = nodes[i].equals("#") ? null : new TreeNode(Integer.valueOf(nodes[i]));
            right = nodes[i + 1].equals("#") ? null : new TreeNode(Integer.valueOf(nodes[i + 1]));
            head.left = left;
            head.right = right;
            if (left != null) {
                dataqueue.add(left);
            }
            if (right != null) {
                dataqueue.add(right);
            }
            if (!dataqueue.isEmpty())
                head = dataqueue.poll();
        }
        return root;
    }

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }

    }

    /**
     * @param str:    An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        // write your code here
        if (str.length == 0) {
            return;
        }
        if (offset == 0) {
            return;
        }
        int i = (offset % str.length);
        int j = str.length - i;
        while (i-- != 0) {
            char ch = str[str.length - 1];
            for (int k = str.length - 1; k > 0; k--) {
                str[k] = str[k - 1];
            }
            str[0] = ch;
        }
    }

    /**
     * @param n: An integer
     * @return: A list of strings.
     */
    public List<String> fizzBuzz(int n) {
        // write your code here
        List<String> list = new LinkedList<String>();
        Integer i = new Integer(0);
        while (++i <= n) {
            if (i % 15 == 0) {
                list.add("fizz buzz");
                continue;
            }
            if (i % 5 == 0) {
                list.add("buzz");
                continue;
            }
            if (i % 3 == 0) {
                list.add("fizz");
                continue;
            }
            list.add(i.toString());
        }
        return list;
    }

    /*
     * @param str: A string
     * @return: all permutations
     */
    public List<String> stringPermutation2(String str) {
        // write your code here
        List<String> result = new ArrayList<>();

        if (str == null || str.length() == 0) {
            result.add("");
            return result;
        }

        //Sort Array beforehand for ordering purpose
        str = sortString(str);

        Set<String> set = new HashSet<>();
        boolean[] isUsed = new boolean[str.length()];
        String testStr = "";

        // DFS
        DFS(str, testStr, isUsed, set, result);
        return result;
    }

    private void DFS(String str,
                     String testStr,
                     boolean[] isUsed,
                     Set<String> set,
                     List<String> result) {
        if (testStr.length() == str.length()) {
            result.add(new String(testStr));
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (isUsed[i] == true || i != 0 && isUsed[i - 1] == false
                    && str.charAt(i) == str.charAt(i - 1)) {
                continue;
            }

            if (set.contains(testStr + str.charAt(i))) {
                continue;
            }

            // 字符串拼接的方法
            testStr = testStr + str.charAt(i);
            set.add(testStr);
            isUsed[i] = true;

            DFS(str, testStr, isUsed, set, result);

            testStr = testStr.substring(0, testStr.length() - 1);
            set.remove(testStr);
            isUsed[i] = false;
        }
    }

    private String sortString(String str) {
        char[] tempArray = str.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static void main(String[] args) {
        //0,0=>1
        //0,9=>1
        //1,12=>5
        //2,12=2
//        System.out.println(new Solution().digitCounts(2, 12));


        //1->1
        //2->2
        //3->3
        //4->4
        //5->5
        //6->6
        //7->8
        //8->9
        //9->10
        //10->12
//        System.out.println(new Solution().nthUglyNumber(41));


//        System.out.println(new Solution().kthLargestElement(3, new int[]{7, 8, 1, 2, 5, 4, 3}));

//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);
//        System.out.println(new Solution().serialize(root));
//        System.out.println(new Solution().serialize(new Solution().deserialize("3,9,20,#,#,15,7,#,#,#,#")));

//        char arr[] = "abcdefg".toCharArray();
//        new Solution().rotateString(arr, 3);
//        System.out.println(arr);
        System.out.println(Arrays.toString(new Solution().fizzBuzz(15).toArray()));
    }
}
