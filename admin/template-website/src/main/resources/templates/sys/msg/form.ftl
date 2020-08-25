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
            <form id="inputForm" action="${ctx}/sysMsg/save" class="form-horizontal">
                <input type="hidden" name="id" value="${sysMsg.id!}">
                <div class="box-body">
                    <div class="form-unit">基本信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 消息标题：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="title" value="${sysMsg.title!}" maxlength="100" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required hide">*</span> 消息链接：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="href" value="${sysMsg.href!}" class="form-control " autocomplete="off"/>
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
<#include "/include/form.ftl">
</html>