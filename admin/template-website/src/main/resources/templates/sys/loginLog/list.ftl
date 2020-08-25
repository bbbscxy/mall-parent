<!DOCTYPE html>
<html>
<head>
    <title>管理</title>
    <#include "/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title"><i class="fa fa-calendar-o"></i>登录日志管理</div>
                <div class="box-tools pull-right">
                <@shiro.hasPermission name="sys:loginLog:form">
                    <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-type="form" data-title="新增" data-href="${ctx}/sysLoginLog/form"><i class="fa fa-plus"></i> 新增</a>
                </@shiro.hasPermission>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysLoginLog/dataList" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">用户名称：</label>
                        <div class="control-inline">
                            <input type="text" name="userName" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label"><i class="fa fa-calendar"></i>：</label>
                        <div class="control-inline" date-range-picker>
                            <input readonly="readonly" name="startTime" placeholder="开始时间" value="" class="form-control width-120" type="text" />
                            <input readonly="readonly" name="endTime" placeholder="结束时间" value="" class="form-control width-120" type="text" />
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-sm">查询</button>
                        <button type="reset" class="btn btn-default btn-sm">重置</button>
                    </div>
                </form>
                <table id="datatable" class="table table-hover table-striped table-bordered"></table>
            </div>
        </div>
    </div>
</div>
</body>
<#include "/include/footer.ftl">
<#include "/include/pagelist.ftl">
<script>
    $(function () {
        $("#datatable").datatable([
            {
                field: "userName", title: "用户名称", align: "center", width: "100",
                formatter: function (value, row, index) {
                    return '<span class="primary">'+value+'</span>';
                }
            },
            { field: "remoteIp", title: "IP地址", align: "center", width: "200" },
            // { field: "remoteAddress", title: "登录地址", align: "center", width: "200" },
            { field: "userAgent", title: "用户代理", align: "center" },
            { field: "createDate", title: "登录时间", align: "center", width: "200" },
        ])
    })
</script>
</html>