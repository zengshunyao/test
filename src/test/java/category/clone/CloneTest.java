package category.clone;

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
public class CloneTest implements Cloneable{
    private String name;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public CloneTest() {
        name = "1212";
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        CloneTest cloneTest = new CloneTest();
        System.out.println(cloneTest.name);
        System.out.println(cloneTest.hashCode());

        CloneTest cloneTest1=(CloneTest)cloneTest.clone();
        System.out.println(cloneTest1.name);
        System.out.println(cloneTest1.name.hashCode());
    }
}
