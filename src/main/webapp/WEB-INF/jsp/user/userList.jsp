<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../common.jsp"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>列表</title>
    <%--bootstrap table--%>
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="panel panel-group">
            <div class="panel panel-primary">
                <div class="panel-heading">总人数：${fn:length(users)}</div>
                <div class="panel-body">
                    <div id="toolbar">
                        <button type="button" class="btn btn-default btn-sm" href="${rp}/user/add" data-toggle="modal" data-target="#operModal">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                        </button>
                        <button type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</button>
                        <button type="button" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</button>
                    </div>
                </div>
            </div>
        </div>
        <table id="userList" data-toggle="table" class="table table-bordered table-hover"
               data-show-footer="false" data-url="${rp}/user/getAll" data-method="post" data-cache="false"
               data-side-pagination="server" data-pagination="true" data-page-list="[10, 20, 50]">
            <thead>
                <tr>
                    <th data-checkbox="true">选择</th>
                    <th data-field="id">员工号</th>
                    <th data-field="name">姓名</th>
                    <th data-field="age">年龄</th>
                    <th data-field="birthday">生日</th>
                </tr>
            </thead>
        </table>
    </div>

    <%--操作界面--%>
    <div class="modal fade" id="operModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

            </div>
        </div>
    </div>
</body>
</html>
