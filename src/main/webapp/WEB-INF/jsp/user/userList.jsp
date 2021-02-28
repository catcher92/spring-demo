<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common.jsp"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>列表</title>
    <style>
        table, tr, td, th{
            text-align: center;
            border:1px solid #000;
        }
    </style>
</head>
<body>
    <h2>总人数：<span class="num">${fn:length(users)}</span></h2>
    <table cellspacing="0">
        <thead>
            <tr>
                <th>员工号</th>
                <th>姓&nbsp;名</th>
                <th>年&nbsp;龄</th>
                <th>操&nbsp;作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>
                        <shiro:hasPermission name="${PERMISSIONS_USER_GET}">
                            <input type="button" name="view" value="查看" />&nbsp;
                        </shiro:hasPermission>
                        <shiro:hasPermission name="${PERMISSIONS_USER_UPDATE}">
                            <input type="button" name="edit" value="编辑" />&nbsp;
                        </shiro:hasPermission>
                        <shiro:hasPermission name="${PERMISSIONS_USER_DELETE}">
                            <input type="button" name="del" value="删除" />
                        </shiro:hasPermission>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
<script>
    $("input[name='view']").click(function () {
        window.location.href="${basePath}/user/get/"+$(this).parent().siblings().eq(0).text();
    });
    $("input[name='edit']").click(function () {
        window.location.href="${basePath}/user/update/"+$(this).parent().siblings().eq(0).text();
    });
    $("input[name='del']").click(function () {
        var $this =  $(this);
        $.ajax({
            url:'${basePath}/user/delete/'+$this.parent().siblings().eq(0).text(),
            method:'post',
            async:false,
            success:function (msg) {
                if ("success"==msg) {
                    $this.parent().parent().remove();
                    if($(".num").text()>0) {
                        $(".num").text($(".num").text()-1);
                    }
//                    window.location.reload();
                }
            }
        });
    });
</script>
</html>
