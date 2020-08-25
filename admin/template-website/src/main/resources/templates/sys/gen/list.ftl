<!DOCTYPE html>
<html>
<head>
    <title>数据库列表</title>
    <#include "/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title"><i class="fa fa-snowflake-o"></i>数据库列表</div>
                <div class="box-tools pull-right">
                    <@shiro.hasPermission name="sys:gen:render">
                        <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-type="confirm" data-title="初始化所有表?" data-href="${ctx}/genTable/initTableList"><i class="fa fa-refresh"></i> 初始化表</a>
                        <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-type="confirm" data-title="生成所有代码?" data-href="${ctx}/genTable/render"><i class="fa fa-bug"></i> 生成代码</a>
                    </@shiro.hasPermission>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/genTable/dataList" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">表名：</label>
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
                field: "name", title: "表名", width: "200",
                formatter: function (value, row, index) {
                    return '<span style="color: #3c8dbc">'+value+'</span>';
                }
            },
            { field: "comments", title: "表说明" },
            { field: "updateDate", title: "更新时间", align: "center", width: "200" },
            {
                title: "操作", align: "center", width: "100",
                formatter: function (value, row, index) {
                    var str = "";
                    <@shiro.hasPermission name="sys:gen:render">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="show" data-title="预览生成代码" data-width="1050" data-href="${ctx}/genTable/preRender?id='+row.id+'"><i class="fa fa-code"></i></a>';
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="sys:gen:config">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="form" data-title="配置" data-width="950" data-href="${ctx}/genTable/configList?id='+row.id+'"><i class="fa fa-pencil"></i></a>';
                    </@shiro.hasPermission>
                    if(str == ""){ str = "-"; }
                    return str;
                }
            },
        ])
    })
</script>
</html>