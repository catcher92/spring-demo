<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common.jsp"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>增加</title>
</head>
<body>
    <form method="post" action="${basePath}/user/update/${user.id}">
        <input type="hidden" name="id" id="id" value="${user.id}">
        <label for="name">姓名：</label><input name="name" id="name" value="${user.name}"><br>
        <label for="name">年龄：</label><input name="age" id="age" value="${user.age}"><br>
        <input type="submit">
    </form>
</body>
</html>
