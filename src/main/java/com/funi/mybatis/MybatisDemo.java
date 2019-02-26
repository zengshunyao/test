package com.funi.mybatis;

import com.funi.mybatis.mapper.UserInfoMapper;
import com.funi.mybatis.pojo.UserInfo;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;

import java.io.InputStream;

@MapperScan()
public class MybatisDemo {

//    @Select()
//    protected  void test();

    public static void main(String[] args) {
        //mybatis的配置文件
        String resource = "mybatis/mybatis-conf.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MybatisDemo.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
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
        session.commit();

        session.close();
        System.out.println(user);
        MapperProxy mapperProxy;
    }
}
