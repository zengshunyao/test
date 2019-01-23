package cn.ccsgroup.ccsframework.common.bean;

import cn.ccsgroup.ccsframework.common.enums.IEnum;

/**
 * IEnum到String转换器
 * <p>
 * Created by shunyao.zeng on 8/10/14.
 */
public class EnumStringConverter extends AbstractTypeConverter {

    public EnumStringConverter(Class<?> sourceClass, Class<?> targetClass) {
        super(sourceClass, targetClass);
    }

    @Override
    public Object convert(Class<?> actualSourceClass, Class<?> actualTargetClass, Object value) {

        if (value == null) {
            return null;
        }
        if (IEnum.class.isAssignableFrom(this.getSourceTypeClass())
                && this.getSourceTypeClass().isAssignableFrom(value.getClass())
                && this.getTargetTypeClass().equals(String.class)) {

            return ((IEnum) value).getCode();
        } else if (String.class.equals(this.getSourceTypeClass()) && this.getSourceTypeClass().equals(value.getClass())
                && IEnum.class.isAssignableFrom(this.getTargetTypeClass())) {

            IEnum[] iEnums = (IEnum[]) actualTargetClass.getEnumConstants();
            for (IEnum iEnum : iEnums) {
                if (iEnum.getCode().equals(String.valueOf(value))) {
                    return iEnum;
                }
            }
        }
        return value;
    }
}
