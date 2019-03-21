package category.jvm.ch10.$10_2;

/**********************************************************************
 * &lt;p&gt;文件名：BoxMain.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2019/3/21 17:34
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class BoxMain {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Long h = 3L;
        System.out.println(c == d);//true
        System.out.println(e == f);//false
        System.out.println(c == (a + b));//true
        System.out.println(c.equals(a + b));//true
        int ab = (a + b);
        System.out.println(g == (a + b));//true
        System.out.println(g.equals(a + b));//false

        //
        System.out.println(g.getClass());
        System.out.println(g.equals(c));
        System.out.println(g == h);
        System.out.println(g == d.intValue());
        System.out.println(g.intValue() == d.longValue());
    }
}
