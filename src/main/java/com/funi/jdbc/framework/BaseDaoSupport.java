package com.funi.jdbc.framework;

import javax.sql.DataSource;
import java.io.Serializable;

public abstract class BaseDaoSupport<T extends Serializable,PK extends Serializable> {


    //讲究，我写这个类是给别人去继承的
    //所以，这个类一般不会单独自己new出来
    //而且这个类里面的所有方法都应该设置为protected

    /**
     * 设置数据源(一般动态注入)
     * @param dataSource
     */
    protected  abstract void setDataSource(DataSource dataSource);


}
