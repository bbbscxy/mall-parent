<!DOCTYPE html>
<html>
<head>
    <title>推送消息管理</title>
<#include "/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title"><i class="fa fa-bookmark-o"></i>推送消息管理</div>
                <div class="box-tools pull-right">
                <@shiro.hasPermission name="sys:msg:form">
                    <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-type="form" data-title="新增" data-href="${ctx}/sysMsg/form"><i class="fa fa-plus"></i> 新增</a>
                </@shiro.hasPermission>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysMsg/dataList" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">消息标题：</label>
                        <div class="control-inline">
                            <input type="text" name="title" value="" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">推送状态：</label>
                        <div class="control-inline">
                            <select class="form-control selectpicker" data-width="120" name="status">
                                <option value="" selected>-- 请选择 --</option>
                            <#list dictList("sys.msg.pushStatus") as dict>
                                <option value="${dict.value}">${dict.name}</option>
                            </#list>
                            </select>
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
                field: "title", title: "消息标题", align: "center",
                formatter: function (value, row, index) {
                    return '<span class="primary">'+value+'</span>';
                }
            },
            { field: "href", title: "消息链接", align: "center"},
            {
                field: "status", title: "推送状态", align: "center", width: "120",
                formatter: function (value, row, index) {
                    var val = value;
                <#list dictList("sys.msg.pushStatus") as dict>
                    if('${dict.value}' == value){ val = '${dict.name}' }
                </#list>
                    return val;
                }
            },
            { field: "updateDate", title: "更新时间", align: "center", width: "200" },
            {
                title: "操作", align: "center", width: "100",
                formatter: function (value, row, index) {
                    var str = "";
                    if(row.status == "0"){
                    <@shiro.hasPermission name="sys:msg:form">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="form" data-title="修改" data-href="${ctx}/sysMsg/form?id='+row.id+'"><i class="fa fa-pencil"></i></a>';
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="sys:msg:delete">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="confirm" data-title="确认删除吗" data-href="${ctx}/sysMsg/delete?id='+row.id+'"><i class="fa fa-trash-o"></i></a>';
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="sys:msg:push">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="confirm" data-title="确认推送到所有用户吗" data-href="${ctx}/sysMsg/push?id='+row.id+'"><i class="fa fa-send-o"></i></a>';
                    </@shiro.hasPermission>
                    }
                    if(str == ""){ str = "-"; }
                    return str;
                }
            },
        ])
    })
</script>
</html>