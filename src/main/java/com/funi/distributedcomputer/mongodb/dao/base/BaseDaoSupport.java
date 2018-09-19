package com.funi.distributedcomputer.mongodb.dao.base;

import com.funi.distributedcomputer.mongodb.util.EntityOperation;
import com.funi.distributedcomputer.mongodb.util.GenericsUtils;
import com.funi.distributedcomputer.mongodb.util.QueryRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.activation.DataSource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseDaoSupport<T extends Serializable, PK extends Serializable>
        implements MongoBase<T> {


    private EntityOperation<T> entityOperation;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public BaseDaoSupport() {
        Class entityClass = GenericsUtils.getSuperClassGenericType(this.getClass(), 0);
        System.out.println(entityClass);
    }

    protected abstract void setDateSource(DataSource dateSource);

    protected abstract String getPKColumn();

    @Override
    public void insert(T object, String collectionName) {

    }

    @Override
    public void createCollection(String collectionName) {

    }

    @Override
    public void remove(Map params, String collectionName) {

    }

    @Override
    public void update(Map params, String collectionName) {

    }

    @Override
    public List<T> find(QueryRule queryRule) {
        //问题1：首先想办法拿到mongoTemplate，而且要通过一个有好的方式注入进来

        //问题2：如何将用户的QueryRule操作转换为Template的操作

        //问题3：MongoDB肯定要实现读写分离以及动态数据源如何路由

        Criteria criteria = new Criteria();
        criteria.where(" name").is("zengshunyao");
        Query query = new Query(criteria);
        this.mongoTemplate.find(query, entityOperation.getEntityClass());
        return null;
    }

    @Override
    public T findOne(Map params, String collectionName) {
        mongoTemplate.findOne(null, null);
        return null;
    }
}
