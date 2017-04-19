<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset="utf-8"/>
    <title>增加</title>
</head>
<body>
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">新增用户</h4>
    </div>
    <div class="modal-body">
        <form method="post" class="form-horizontal" action="${basePath}/user/add">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">姓名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control required" name="name" id="name" placeholder="姓名">
                </div>
            </div>
            <div class="form-group">
                <label for="age" class="col-sm-2 control-label">年龄</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control required digits" name="age" id="age" placeholder="年龄">
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="submit" type="button" class="btn btn-primary">保存</button>
    </div>
</body>
<script>
    var myValidate = function () {
        var handlerSubmit = function () {
            $("form").validate({
                submitHandler:function(form){
                    $(form).ajaxSubmit({
                        success:function (data) {

                        }
                    });
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
            $("#submit").click(function () {
                if ($('form').validate().form()) {
                    $('form').submit();
                }
                return false;
            });
        }
        return{
            init:function () {
                handlerSubmit();
            }
        };
    }();

    myValidate.init();
</script>
</html>
