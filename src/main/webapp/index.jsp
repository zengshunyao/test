<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欢迎访问</title>
    <link rel="schema.DC" href="http://purl.org/DC/elements/1.0/">
    <link rel="SHORTCUT ICON" href="//www.ibm.com/favicon.ico">
    <script src="<%=basePath%>js/jquery-3.2.1.min.js" type="text/javascript"></script>
</head>
<body>
<h2>Hello World!</h2>
<from action="<%=basePath%>TestServlet" method="post">
    <input type="text" id="name" name="name" value="123"/><br/>
    <input type="submit" value="保存"/>
    <input type="reset" value="取消"/>
</from>
<input type="button" value="测试" onclick="save()"/>
</body>
</html>
<script type="text/javascript">
    function save() {
        $.post("SSlController/testSave", {name: $('#name').val()},
                function (data) {
                    alert(data);
                    console.log(data);
                },
                "json");//这里返回的类型有：json,html,xml,text
    }
</script>
