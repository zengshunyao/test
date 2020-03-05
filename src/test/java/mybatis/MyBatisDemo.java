package mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import mybatis.mapper.EmpMapper;
import mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/7/11 17:21
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class MyBatisDemo {
    public static void main(String[] args) {
        selectOne();
//        findAll();
    }

    /**
     * 查询一条
     */
    public static void selectOne() {
        try {
            //使用MyBatis提供的Resources类加载mybatis的配置文件
            Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            boolean autoCommit = false;
            SqlSession session = sessionFactory.openSession(autoCommit);


            EmpMapper mapper = session.getMapper(EmpMapper.class);

            Emp emp = mapper.selectByPrimaryKey(7369L);
            System.out.println(emp.toString());

//            Emp emp2 = mapper.selectByPrimaryKey(7369L);
//            System.out.println(emp2.toString());

            if (!autoCommit) {
//                session.commit();
            }

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询一条 测试session
     */
    public static void selectOneTestTwoSession() {
        try {
            //使用MyBatis提供的Resources类加载mybatis的配置文件
            Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            {
                SqlSession session = sessionFactory.openSession();
                EmpMapper mapper = session.getMapper(EmpMapper.class);
                Emp emp = mapper.selectByPrimaryKey(7369L);
                System.out.println(emp.toString());

                Emp emp2 = mapper.selectByPrimaryKey(7369L);
                System.out.println(emp2.toString());
//            session.commit();
                session.close();
            }

            {
                SqlSession session = sessionFactory.openSession();
                EmpMapper mapper = session.getMapper(EmpMapper.class);
                Emp emp = mapper.selectByPrimaryKey(7369L);
                System.out.println(emp.toString());

                Emp emp2 = mapper.selectByPrimaryKey(7369L);
                System.out.println(emp2.toString());
//            session.commit();
                session.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询一堆
     */
    public static void findAll() {
        //使用MyBatis提供的Resources类加载mybatis的配置文件
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");

            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sessionFactory.openSession();
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            PageHelper.startPage(1, 10, false);
            List<Emp> list = mapper.findAll();
            System.out.println(list.toString());

            Page<Emp> emps = PageHelper
                    .startPage(1, 10, false)
                    .doSelectPage(() -> mapper.findAll());
            System.out.println(emps.toString());

            session.close();
//            session.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
