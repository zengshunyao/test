package com.funi.distributedcomputer.mongodb;

import com.funi.distributedcomputer.mongodb.entity.User;
import com.mongodb.MongoClient;
//import org.mongodb.morphia.Datastore;
//import org.mongodb.morphia.Key;
//import org.mongodb.morphia.Morphia;
//import org.mongodb.morphia.query.Query;

import java.util.Date;

public class MorphiaTest {
    public static void main(String[] args) {
//        Morphia morphia = new Morphia();
//        Datastore datastore = morphia.createDatastore(new MongoClient("127.0.0.1", 27017),
//                "bug-demo");
        //1、新增
        User user = new User();
        user.setName("王老板");
        user.setAge(18);
        user.setCreateTime(new Date(System.currentTimeMillis()));
        user.setFrom("四川");
        user.setGender("男");
        user.setLike("日本AV");
//        Key<User> key = datastore.save(user);
//        System.out.println(key);

        //2、查询
//        Query<User> userQuery = datastore.createQuery(User.class);
//        System.out.println(userQuery.get());
    }
}
