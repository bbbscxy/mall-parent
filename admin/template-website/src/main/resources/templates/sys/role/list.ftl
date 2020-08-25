<!DOCTYPE html>
<html>
<head>
    <title>角色管理</title>
    <#include "/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title"><i class="fa fa-user-o"></i>角色管理</div>
                <div class="box-tools pull-right">
                    <@shiro.hasPermission name="sys:role:form">
                        <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-type="form" data-title="新增角色" data-href="${ctx}/sysRole/form"><i class="fa fa-plus"></i> 新增</a>
                    </@shiro.hasPermission>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysRole/dataList" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">角色名称：</label>
                        <div class="control-inline">
                            <input type="text" name="name" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">英文名称：</label>
                        <div class="control-inline">
                            <input type="text" name="enname" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
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
                field: "name", title: "角色名称", width: "200",
                formatter: function (value, row, index) {
                    return '<span class="primary">'+value+'</span>';
                }
            },
            { field: "enname", title: "英文名称" },
            { field: "updateDate", title: "更新时间", align: "center", width: "200" },
            {
                title: "操作", align: "center", width: "100",
                formatter: function (value, row, index) {
                    var str = "";
                    <@shiro.hasPermission name="sys:role:form">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="form" data-title="修改角色" data-href="${ctx}/sysRole/form?id='+row.id+'"><i class="fa fa-pencil"></i></a>';
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="sys:role:delete">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="confirm" data-title="确认删除角色吗" data-href="${ctx}/sysRole/delete?id='+row.id+'"><i class="fa fa-trash-o"></i></a>';
                    </@shiro.hasPermission>
                    if(str == ""){ str = "-"; }
                    return str;
                }
            },
        ])
    })
</script>
</html>