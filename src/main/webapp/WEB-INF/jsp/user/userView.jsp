<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common.jsp"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>展示</title>
</head>
<body>
<h2>
    员工号：${user.userId}<br>
    姓名：${user.name}<br>
    年龄:${user.age}<br>
    创建人:${user.createBy}<br>
    创建时间:<fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /> <br>
    更新人:${user.updateBy}<br>
    更新时间:<fmt:formatDate value="${user.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" />
</h2>
<c:if test="${not empty user.userId}"><input type="button" name="edit" value="编辑"></c:if>
</body>
<script>
    $("input[name='edit']").click(function () {
        window.location.href="${basePath}/user/update/${user.userId}";
    });
</script>
</html>
