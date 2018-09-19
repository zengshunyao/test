package com.funi.distributedcomputer.mongodb.dao.base;

import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/18 11:33
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public abstract class MongoBaseDaoSupport<T, K> implements MongoBaseDao<T, K> {

    /**
     *
     */
    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(T object, String collectionName) {

    }

    @Override
    public T findOne(Map<String, Object> params, String collectionName) {
        return null;
    }

    @Override
    public List<T> findAll(Map<String, Object> params, String collectionName) {
        return null;
    }

    @Override
    public void update(Map<String, Object> params, String collectionName) {

    }

    @Override
    public void createCollection(String collectionName) {

    }

    @Override
    public void remove(Map<String, Object> params, String collectionName) {

    }
}
