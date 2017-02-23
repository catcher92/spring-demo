<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="common.jsp"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>spring-demo首页</title>
</head>
<body>
    <shiro:user>
        <h2>欢迎[<shiro:principal />]登录</h2>
        <h2>${message}</h2>
        <h2>服务器信息：<%=session.getServletContext().getServerInfo()%></h2>
        <h2><a href="${basePath}/logout">退出系统</a></h2>
    </shiro:user>

    <h2>${loginResult}</h2>
</body>
</html>
