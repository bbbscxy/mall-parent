<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="${ctxStatic}/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctxStatic}/common/css/login.css"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css"/>
</head>
<body>
<div class="be-content pren">
    <div class="ioc_text">
        <img src="${ctxStatic}/common/img/logo.png" alt="">
        <span>通用框架系统</span>
    </div>
    <div>
        <form id="loginForm" action="${ctx}/login" method="POST" onsubmit="return login();">
            <div class="br-content">
                <div class="input-group mb-4 bootint">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa fa-user"></i></span>
                    </div>
                    <input type="text" name="loginName" value="admin" class="form-control" placeholder="账号">
                </div>
                <div class="input-group mb-4 bootint">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fa fa-unlock-alt"></i></span>
                    </div>
                    <input type="password" name="password" value="123456" class="form-control" placeholder="密码">
                </div>
                <div style="padding-top: 10px">
                    <input type="submit" class="btn" value="登录">
                </div>
                <div class="be-con">
                    <span>Copyright © 2018 - 2019 <a href="">登陆</a></span>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script type="text/javascript" src="${ctxStatic}/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-validate/jquery.validate.min.js" ></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-validate/additional-methods.min.js" ></script>
<script type="text/javascript" src="${ctxStatic}/plugins/jquery-validate/localization/messages_zh.js" ></script>
<script type="text/javascript" src="${ctxStatic}/plugins/layer/layer.js" ></script>
<script type="text/javascript">
    $(function () {
        if (window != top){
            top.location.href = location.href;
        }

        $("#loginForm").validate({
            rules: {
                loginName:{
                    required: true,
                },
                password: {
                    required: true,
                }
            },
            messages:{
                loginName:{
                    required: "账号不能为空",
                },
                password: {
                    required: "密码不能为空"
                }
            }
        });
    });

    /**
     * 登录
     */
    function login() {
        var flag = $('#loginForm').valid();
        if(flag){
            //loading
            var index = window.parent.layer.load(1, {
                shade: [0.3,'#fff'], //0.1透明度的白色背景
            });
            $.ajax({
                url: $("#loginForm").attr("action"),
                type: "POST",
                data: $('#loginForm').serialize(),
                success: function(result){
                    layer.close(index);
                    layer.msg(result.msg, {time:1000})
                    if(result.code == "SUCCESS"){
                        window.location.href = "${ctx}/";
                    }
                }
            })
        }
        return false;
    }
</script>
</html>
