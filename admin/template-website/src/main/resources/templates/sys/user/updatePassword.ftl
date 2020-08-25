<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <#include "/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <form id="inputForm" action="${ctx}/sysUser/updatePassword" class="form-horizontal">
                <div class="box-body">
                    <div class="form-unit">基本信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 旧密码：
                                </label>
                                <div class="col-sm-8">
                                    <input type="password" name="oldPassword" value="" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required " aria-required="true">*</span> 新密码：
                                </label>
                                <div class="col-sm-8">
                                    <input type="password" id="newPassword" name="newPassword" value="" minlength="6" maxlength="20" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required hide" aria-required="true">*</span> 确认新密码：
                                </label>
                                <div class="col-sm-8">
                                    <input type="password" name="confirmPassword" value="" class="form-control required" equalTo="#newPassword" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<#include "/include/footer.ftl">
<script>
    function submitForm() {
        if($('#inputForm').valid()){
            //loading
            var index = window.parent.layer.load(1, {
                shade: [0.3,'#fff'], //0.1透明度的白色背景
            });
            $.ajax({
                url: $("#inputForm").attr("action"),
                type: "POST",
                data: $('#inputForm').serialize(),
                success: function(result){
                    window.parent.layer.close(index);
                    if(result.code == "SUCCESS"){
                        window.parent.location.href = "${ctx}/";
                    }
                }
            })
        }
    }
</script>
</html>