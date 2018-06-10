package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by Lenovo on 2015/7/5.
 */
@Target(ElementType.METHOD)
public @interface Test_Target {
    String doTestTarget() default "";
}
