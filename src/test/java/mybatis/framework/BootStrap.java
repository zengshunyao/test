package mybatis.framework;

import mybatis.mapper.EmpMapper;
import mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;

import java.io.IOException;
import java.io.Reader;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/7/12 17:32
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class BootStrap {

    public static void main(String[] args) throws IOException {
        //使用MyBatis提供的Resources类加载mybatis的配置文件
        Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml");
        //构建sqlSession的工厂
        MySqlSessionFactory sessionFactory = new MySqlSessionFactoryBuilder().build(reader,
                new Environment("conf/develop", null, null), new MyConfiguration());

        MySqlSession session = sessionFactory.openSession();
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        Emp emp = mapper.selectByPrimaryKey(7369l);
        System.out.println(emp.toString());

        session.close();
    }
}
