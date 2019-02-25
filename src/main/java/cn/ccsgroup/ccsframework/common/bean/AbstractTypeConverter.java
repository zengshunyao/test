package cn.ccsgroup.ccsframework.common.bean;

/**
 * Created by shunyao.zeng on 2016-6-31.
 */
public abstract class AbstractTypeConverter implements TypeConverter {

    /**
     * 源类型
     */
    private Class<?> sourceClass;

    /**
     * 目标类型
     */
    private Class<?> targetClass;

    public AbstractTypeConverter() {
    }

    public AbstractTypeConverter(Class<?> sourceClass, Class<?> targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    @Override
    public Class<?> getSourceTypeClass() {
        return this.sourceClass;
    }

    @Override
    public Class<?> getTargetTypeClass() {
        return this.targetClass;
    }

//    @Override
//    public abstract Object convert(Class<?> actualSourceClass, Class<?> actualTargetClass, Object value);

}
