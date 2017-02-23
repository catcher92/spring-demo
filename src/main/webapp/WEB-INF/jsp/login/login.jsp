<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common.jsp"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>登录页面</title>
    <script src="${rp}/static/plugins/jquery/jquery.min.js"></script>
    <style>
        body {
            background-image: url("${rp}/static/img/background.jpg");
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
    <div>
        <form method="post" action="${basePath}/login/login">
            <label for="username">用户名：</label><input name="username" id="username"><br>
            <label for="password">密&nbsp;码：</label><input type="password" name="password" id="password"><br>
            <input id="rememberMe" type="checkbox"> 下次自动登录<br>
            <input type="hidden" name="rememberMe" value="false" />
            <input type="submit">
        </form>
    </div>

</body>
<script>
    $().ready(function () {
        $("#rememberMe").click(function () {
            $("input[name=rememberMe]").val($("#rememberMe").prop("checked"));
        });
    });
</script>
</html>
