<!DOCTYPE html>
<html>
<head>
    <title>字典管理</title>
    <#include "/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title"><i class="fa fa-sun-o"></i>字典管理</div>
                <shiro.hasPermission class="box-tools pull-right">
                    <@shiro.hasPermission name="sys:dict:form">
                        <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-type="form" data-title="新增字典" data-href="${ctx}/sysDict/form"><i class="fa fa-plus"></i> 新增</a>
                    </@shiro.hasPermission>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysDict/dataList" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">字典类型：</label>
                        <div class="control-inline">
                            <input type="text" name="code" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">字典名称：</label>
                        <div class="control-inline">
                            <input type="text" name="label" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
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
                field: "code", title: "字典标识", width: "200",
                formatter: function (value, row, index) {
                    return '<span class="primary">'+value+'</span>';
                }
            },
            {
                field: "label", title: "字典标签", width: "200",
                formatter: function (value, row, index) {
                    return '<span class="primary">'+value+'</span>';
                }
            },
            {
                field: "type", title: "字典类型", align: "center", width: "200",
                formatter: function (value, row, index) {
                    var val = value;
                    <#list dictList("sys.dictType") as dict>
                        if('${dict.value}' == value){ val = '${dict.name}' }
                    </#list>
                    return val;
                }
            },
            { field: "updateDate", title: "更新时间", align: "center", width: "200" },
            { field: "remarks", title: "字典描述", align: "left", width: "400" },
            {
                title: "操作", align: "center", width: "100",
                formatter: function (value, row, index) {
                    var str = "";
                    <@shiro.hasPermission name="sys:dict:form">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="form" data-title="修改字典" data-href="${ctx}/sysDict/form?id='+row.id+'"><i class="fa fa-pencil"></i></a>';
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="sys:dict:delete">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="confirm" data-title="确认删除字典吗" data-href="${ctx}/sysDict/delete?id='+row.id+'"><i class="fa fa-trash-o"></i></a>';
                    </@shiro.hasPermission>
                    if(str == ""){ str = "-"; }
                    return str;
                }
            },
        ])
    })
</script>
</html>