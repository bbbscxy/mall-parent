<!DOCTYPE html>
<html>
<head>
    <title>消息管理</title>
    <#include "/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title"><i class="fa fa-user-o"></i>用户管理</div>
                <div class="box-tools pull-right" style="top: 10px;right: 33px;">
                    <a href="javascript:readAll();">全部已读</a>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysUserMsg/dataList" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">消息标题：</label>
                        <div class="control-inline">
                            <input type="text" name="title" value="" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">阅读状态：</label>
                        <div class="control-inline">
                            <select class="form-control" style="width: 120px;" name="loginFlag">
                                <option value="" selected>全部</option>
                            <#list dictList("sys.msg.readStatus") as dict>
                                <option value="${dict.value}">${dict.name}</option>
                            </#list>
                            </select>
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
    //发送请求
    function readPost(id) {
        $.ajax({
            url: "${ctx}/sysUserMsg/read?id="+id,
            type: "POST",
            success: function(result){
                if(result.code == "SUCCESS"){
                    search();
                }
            }
        })
    }

    //已读
    function read(id) {
        readPost(id);
    }

    //全部已读
    function readAll() {
        readPost("");
    }

    $(function () {
        $("#datatable").datatable([
            {
                field: "sysMsg.title", title: "消息标题", align: "center",
                formatter: function (value, row, index) {
                    return '<span class="primary">'+value+'</span>';
                }
            },
            {
                field: "status", title: "阅读状态", align: "center", width: "200",
                formatter: function (value, row, index) {
                    var val = value;
                <#list dictList("sys.msg.readStatus") as dict>
                    if('${dict.value}' == value){ val = '${dict.name}' }
                </#list>
                    return val;
                }
            },
            { field: "createDate", title: "创建时间", align: "center", width: "200" },
            {
                title: "操作", align: "center", width: "100",
                formatter: function (value, row, index) {
                    if(row.status == "0"){
                        return '<a class="primary" href="javascript:read('+'\''+row.id+'\''+');">已读</a>';
                    }else{
                        return '-';
                    }
                }
            },
        ])
    })
</script>
</html>