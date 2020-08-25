<!DOCTYPE html>
<html>
<head>
    <title>管理</title>
    <#include "${ctx}/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title">操作日志管理</div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysOperateLog/dataList" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">操作用户：</label>
                        <div class="control-inline">
                            <input type="text" name="userName" value="" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">标题内容：</label>
                        <div class="control-inline">
                            <input type="text" name="title" value="" class="form-control width-120" autocomplete="off" />
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
<#include "${ctx}/include/footer.ftl">
<#include "${ctx}/include/pagelist.ftl">
<script>
    $(function () {
        $("#datatable").datatable([
            {
                field: "userName", title: "操作用户", align: "center", width: "100",
                formatter: function(value, row, index){
                    return '<span class="primary">'+value+'</span>';
                }
            },
            { field: "remoteIp", title: "IP地址", align: "center", width: "100" },
            { field: "userAgent", title: "用户代理", align: "center", width: "200" },
            { field: "title", title: "标题内容", align: "center" },
            { field: "requestUri", title: "请求地址", align: "center" },
            { field: "method", title: "请求方法", align: "center", width: "80" },
            { field: "createDate", title: "访问时间", align: "center", width: "160" },
            {
                title: "操作", align: "center", width: "70",
                formatter: function(value, row, index){
                    var str = "";
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-height="500" data-type="show" data-title="请求参数" data-href="${ctx}/sysOperateLog/params?id='+row.id+'"><i class="fa fa-file-text-o"></i></a>';
                    return str;
                }
            }
        ])
    })
</script>
</html>