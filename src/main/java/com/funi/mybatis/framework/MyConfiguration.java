package com.funi.mybatis.framework;

import org.apache.ibatis.session.Configuration;

public class MyConfiguration extends Configuration {


    private Configuration configuration;

    public MyConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
