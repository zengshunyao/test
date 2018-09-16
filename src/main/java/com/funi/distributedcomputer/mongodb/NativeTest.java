package com.funi.distributedcomputer.mongodb;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class NativeTest {
    public static void main(String[] args) {
        //JDBC Connection
        Mongo mongo = new Mongo("127.0.0.1", 27017);

        //建立连接
        DB db = new DB(mongo, "bug-demo");

//        System.out.println(db.getCollectionNames());

        //集合
        DBCollection collection = db.getCollection("t_user");

        //游标
        DBCursor cursor = collection.find();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
}
