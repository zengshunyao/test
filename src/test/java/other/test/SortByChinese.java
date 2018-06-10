package other.test;

import javax.servlet.FilterConfig;
import java.text.Collator;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;

/**********************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么) 
 * @project_name：${project_name}
 * @author ${user}  
 * @date ${date} ${time} 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class SortByChinese {
    //测试
    public static void main(String[] args) {

        String[] a = {"张三", "李四", "刘翔", "刘六", "郭晶晶", "姚明"};
        getSortOfChinese(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * @param a String[]
     * @return String[]
     */
    public static String[] getSortOfChinese(String[] a) {
        // Collator 类是用来执行区分语言环境这里使用CHINA
        Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
        // JDKz自带对数组进行排序。
        Arrays.sort(a, cmp);
        return a;
    }

    public void test(){
        AbstractList a;
        FilterConfig filterConfig;
//        StandardWrapperValue standardWrapperValue;
//        ApplicationFilterChain applicationFilterChain;
    }
}
