package com.funi.distributedcomputer.mongodb.util;

public class EntityOperation<T> {

    private Class<T> entityClass;

    public EntityOperation(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }
}
