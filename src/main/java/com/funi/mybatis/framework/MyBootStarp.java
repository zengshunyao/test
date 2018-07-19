package com.funi.mybatis.framework;

public class MyBootStarp {

    public static String  nameSpace ="com.funi.mybatis.mapper.UserInfoMapper" ;

    public static String methodSqlMapperIng(String methodname){
        return "select id, first_name, last_name, sex, score, copy_id\n" +
                "    from userinfo\n" +
                "    where id = #{id,jdbcType=INTEGER}";
    }
    public static void main(String[] args) {/*
        //mybatis的配置文件
        String resource = "mybatis-conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisDemo.class.getClassLoader().getResourceAsStream(resource);
        MyConfiguration configuration = new MyConfiguration(new XMLConfigBuilder(is, null, null).parse());
        //构建sqlSession的工厂
//        SqlSessionFactory sessionFactory = new DefaultSqlSessionFactory(configuration.getConfiguration());
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        /*
//        SqlSession session = sessionFactory.openSession();
        MySqlSession session = new MySqlSession(configuration.getConfiguration(),
                new SimpleExecutor(configuration.getConfiguration(), null), false);
        String statement = "com.funi.mybatis.mapper.UserInfoMapper.selectByPrimaryKey";//映射sql的标识字符串
//        String statement = "com.funi.mybatis.mapper.UserInfoMapper.insert";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
//        UserInfo user = session.selectOne(statement, 4);
        UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);
        UserInfo user = userInfoMapper.selectByPrimaryKey(4);
//        UserInfo user=new UserInfo();
//        user.setFirstName("qaz");
//        user.setLastName("123");
//        user.setScore(99);
//        user.setSex("m");
//        user.setCopyId(1);
//        session.insert(statement, user);
//        session.commit();

        session.close();
        System.out.println(user);*/
    }
}
