package com.funi.distributedcomputer.mongodb.dao;

import com.funi.distributedcomputer.mongodb.dao.base.BaseDaoSupport;
import com.funi.distributedcomputer.mongodb.entity.User;
import com.funi.distributedcomputer.mongodb.util.QueryRule;
import org.springframework.stereotype.Repository;

import javax.activation.DataSource;
/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/18 11:35
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
@Repository
public class UserDao extends BaseDaoSupport<User, Long> {

    public void getById(String id) {
        QueryRule queryRule = QueryRule.getInstance();
//        queryRule.

        super.findOne(null, null);
    }
//
//    public static void main(String[] args) {
//        new UserDao();
//    }

    @Override
    protected void setDateSource(DataSource dateSource) {

    }

    @Override
    protected String getPKColumn() {
return "id";
    }
}
