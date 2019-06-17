package category.tmp;

import java.util.HashMap;

/**********************************************************************
 * &lt;p&gt;文件名：Solution.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/5/28 20:43 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class Solution {
    //
    public int[] twoSum(int[] nums, int target) {

        if (nums == null || nums.length < 2) {
            return new int[]{-1, -1};
        }
        int[] res = new int[]{-1, -1};

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        Integer a = 0;
        Integer b = 0;
        do {
            a = l1.val * String.valueOf(a).length();
        } while ((l1 = l1.next) != null);

        do {
            b = l1.val * String.valueOf(b).length();
        } while ((l1 = l1.next) != null);

        int c = a + b;
        ListNode ret = new ListNode(0);
        ListNode tmp = ret;
        do {
            tmp = tmp.next = new ListNode(a % 10);
        }
        while ((c = c / 10) != 0);
        return ret.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode tmp = new ListNode(0);
        ListNode ret = tmp;
        int jinwei = 0;
        do {
            tmp.next = new ListNode(jinwei + l1.val + l2.val);
            if (tmp.val >= 10) {
                jinwei = tmp.val / 10;
                tmp.val = tmp.val % 10;
            } else {
                jinwei = 0;
            }
            l1 = l1.next;
            l2 = l2.next;
        } while (l1 != null && l2 != null);

        while (l1 != null) {
            tmp.next = new ListNode(jinwei + l1.val);
            if (tmp.val >= 10) {
                jinwei = tmp.val / 10;
                tmp.val = tmp.val % 10;
            } else {
                jinwei = 0;
            }
            l1 = l1.next;
        }
        while (l2 != null) {
            tmp.next = new ListNode(jinwei + l2.val);
            if (tmp.val >= 10) {
                jinwei = tmp.val / 10;
                tmp.val = tmp.val % 10;
            } else {
                jinwei = 0;
            }
            l2 = l2.next;
        }
        return ret.next;
    }

    public int singleNumber(int[] nums) {
        //任何证书都是32个bit位
        int ones = 0;//记录只出现过1次的bits
        int twos = 0;//记录只出现过2次的bits
        int threes;
        for (int i = 0; i < nums.length; i++) {
            int t = nums[i];
            twos |= ones & t;//要在更新ones前面更新twos
            ones ^= t;
            threes = ones & twos;//ones和twos中都为1即出现了3次
            ones &= ~threes;//抹去出现了3次的bits
            twos &= ~threes;//抹去出现了2次的bits
        }
        return ones;
    }

    public static void main(String[] args) {
        //1^2^3=  001^010=>011^3=0
//        System.out.println(new Solution().singleNumber(new int[]{3, 3, 3, 2, 2, 2, 1}));
//        System.out.println(new Solution().addTwoNumbers2(new int[]{2, 7, 11, 15}, 9));

    }
}
