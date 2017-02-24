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
    <h2>总人数：${fn:length(users)} <br></h2>
    <table cellspacing="0">
        <thead>
            <tr>
                <th>员工号</th>
                <th>姓&nbsp;名</th>
                <th>年&nbsp;龄</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
