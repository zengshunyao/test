package com.funi.distributedcomputer.mongodb.dao.base;

import java.util.List;
import java.util.Map;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/18 11:27
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public interface MongoBaseDao<T,K> {
    //添加
    public void insert(T object, String collectionName);

    //根据条件查找
    public T findOne(Map<String, Object> params, String collectionName);

    //查找所有
    public List<T> findAll(Map<String, Object> params, String collectionName);

    //修改
    public void update(Map<String, Object> params, String collectionName);

    //创建集合
    public void createCollection(String collectionName);

    //根据条件删除
    public void remove(Map<String, Object> params, String collectionName);

}