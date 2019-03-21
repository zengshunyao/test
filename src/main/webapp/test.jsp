<%--
  Created by IntelliJ IDEA.
  User: zengshunyao
  Date: 2019/3/21
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="category.book.shenrujvm.ch09.$9_3_3.JavaClassExecuter" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.InputStream" %>
<%
    InputStream inputStream = new FileInputStream("/opt/TestClass.class");
    byte[] b = new byte[inputStream.available()];
    inputStream.read(b);
    inputStream.close();
    out.println(JavaClassExecuter.executer(b));
%>
