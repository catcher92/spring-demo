<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="../common.jsp"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>增加</title>
</head>
<body>
    <form method="post" action="${basePath}/user/update/${user.userId}">
        <input type="hidden" name="id" id="id" value="${user.id}">
        <input type="hidden" name="userId" id="userId" value="${user.userId}">
        <label for="name">姓名：</label><input name="name" id="name" value="${user.name}"><br>
        <label for="name">年龄：</label><input name="age" id="age" value="${user.age}"><br>
        <input type="submit"> &nbsp;<input id="audit" type="button" value="审核">&nbsp;<input id="back" type="button" value="返回">
    </form>
</body>
<script>
    $("#audit").click(function () {
        $.ajax({
            url:"${basePath}/user/audit/"+$("#userId").val(),
            method:"post",
            success:function (msg) {
                if ("success"==msg) {
                    window.location.reload();
                }
            }
        });
    });
    $("#back").click(function () {
        history.back();
    });
</script>
</html>
