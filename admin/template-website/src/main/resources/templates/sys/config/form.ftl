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
            <form id="inputForm" action="${ctx}/sysConfig/save" class="form-horizontal">
                <input type="hidden" name="id" value="${sysConfig.id!}">
                <div class="box-body">
                    <div class="form-unit">基本信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 配置名称：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" value="${sysConfig.name!}" minlength="2" maxlength="40" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required " aria-required="true">*</span> 配置标识：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="code" value="${sysConfig.code!}" minlength="2" maxlength="40" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required " aria-required="true">*</span> 配置值：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="value" value="${sysConfig.value!}" maxlength="100" class="form-control required" autocomplete="off"/>
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