package com.funi.jdk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**********************************************************************
 * &lt;p&gt;文件名：ParentalAppointment.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2019/1/24 9:55
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class ParentalAppointment {
    /**
     * 获得JDBC连接
     *
     * @return JDBC连接
     */
    public final static Connection getConnection() throws SQLException {
        try {
            //加载MySql的驱动类
            //用系统类加载器加载了com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("找不到驱动程序类 ，加载驱动失败！");
            e.printStackTrace();
        }
        final String url = "jdbc:mysql://localhost:3306/test";
        final String username = "root";
        final String password = "root";
        try {
            final Connection con = DriverManager.getConnection(url, username, password);
//            DriverManager.getConnection();
            return con;
        } catch (SQLException se) {
            System.err.println("数据库连接失败！");
            se.printStackTrace();
            throw se;
        }
    }
}
