package cn.ccsgroup.ccsframework.common.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shunyao.zeng on 1/6/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface EnableCache {

    /**
     * 数据实体对象
     */
    Class<?>[] value();
}
