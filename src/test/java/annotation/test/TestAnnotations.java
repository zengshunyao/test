package annotation.test;

import annotation.Test_Target;

/**
 * Created by Lenovo on 2015/7/5.
 */
public class TestAnnotations {
    // 在方法上使用注解,OK.
    // 中间也可以不换行,换2行之类,Java忽略多余的换行
    //@Test
    @Test_Target(doTestTarget = "Hello World !")
    public void doTestTarget() {
        System.out.printf("Testing Target annotation");
    }
}
