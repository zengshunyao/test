package com.funi.distributedcomputer.mongodb.dao;

import com.funi.distributedcomputer.mongodb.dao.base.BaseDaoSupport;
import com.funi.distributedcomputer.mongodb.entity.User;
import com.funi.distributedcomputer.mongodb.util.QueryRule;
import org.springframework.stereotype.Repository;

import javax.activation.DataSource;

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
