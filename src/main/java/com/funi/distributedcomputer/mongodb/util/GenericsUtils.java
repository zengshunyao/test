package com.funi.distributedcomputer.mongodb.util;

import java.lang.reflect.ParameterizedType;

public final class GenericsUtils {

    public static Class getSuperClassGenericType(Class<? extends Object> clazz, int index) {

        Class entityClass = (Class) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
        return entityClass;
    }

    public static Class getSuperClassGenericType(Class<? extends Object> clazz) {
        return getSuperClassGenericType(clazz, 0);
    }

    public static void main(String[] args) {

    }
}
