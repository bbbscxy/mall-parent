<!DOCTYPE html>
<html>
<head>
    <title>配置管理</title>
    <#include "/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title"><i class="fa fa-gg"></i>配置管理</div>
                <div class="box-tools pull-right">
                    <@shiro.hasPermission name="sys:config:form">
                        <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-height="500" data-type="form" data-title="新增配置" data-href="${ctx}/sysConfig/form"><i class="fa fa-plus"></i> 新增</a>
                    </@shiro.hasPermission>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysConfig/dataList" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">配置标识：</label>
                        <div class="control-inline">
                            <input type="text" name="code" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">配置名称：</label>
                        <div class="control-inline">
                            <input type="text" name="name" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
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
            field: "code", title: "配置标识", width: "300",
            formatter: function (value, row, index) {
                return '<span class="primary">'+value+'</span>';
            }
        },
        {
            field: "name", title: "配置名称", width: "200" ,
        },
        { field: "value", title: "配置值", align: "left", width: "400" },
        { field: "updateDate", title: "更新时间", align: "center", width: "200" },
        {
            title: "操作", align: "center", width: "100",
            formatter: function (value, row, index) {
                var str = "";
                <@shiro.hasPermission name="sys:config:form">
                    str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-height="500" data-type="form" data-title="修改配置" data-href="${ctx}/sysConfig/form?id='+row.id+'"><i class="fa fa-pencil"></i></a>';
                </@shiro.hasPermission>
                <@shiro.hasPermission name="sys:config:delete">
                    str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="confirm" data-title="确认删除配置吗" data-href="${ctx}/sysConfig/delete?id='+row.id+'"><i class="fa fa-trash-o"></i></a>';
                </@shiro.hasPermission>
                if(str == ""){ str = "-"; }
                return str;
            }
        },
    ])
})
</script>
</html>