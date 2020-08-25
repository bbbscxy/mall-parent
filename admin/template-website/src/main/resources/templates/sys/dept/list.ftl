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
                <div class="box-title"><i class="fa fa-map-o"></i>部门管理</div>
                <div class="box-tools pull-right">
                <@shiro.hasPermission name="sys:dept:form">
                    <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-type="form" data-title="添加部门" data-href="${ctx}/sysDept/form"><i class="fa fa-plus"></i> 新增</a>
                </@shiro.hasPermission>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysDept/dataList" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">部门名称：</label>
                        <div class="control-inline">
                            <input type="text" name="deptName" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-sm">查询</button>
                        <button type="reset" class="btn btn-default btn-sm">重置</button>
                    </div>
                </form>
                <table id="datatree" class="table table-hover table-striped table-bordered"></table>
            </div>
        </div>
    </div>
</div>
</body>
<#include "${ctx}/include/footer.ftl">
<#include "${ctx}/include/treeList.ftl">
<script>
    $(function () {
        var colNames = ['id','部门名称', '负责人', '创建时间', '操作'];
        var colModel = [
            { name:'id',index:'id', hidden:true},
            {
                name:'name',index:'name', align: 'left', sortable: false,
                formatter: function(val, options, row){
                    return '<span class="primary">'+val+'</span>';
                }
            },
            { name:'leaderName',index:'leaderName', align: 'left', sortable: false },
            { name: "createDate", index: "createDate", align: "center", width: "200" },
            {
                name:'operate',align: 'center', sortable: false, width: "55",
                formatter: function(val, options, row){
                    var str = "";
                     <@shiro.hasPermission name="sys:dept:form">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="form" data-title="添加部门" data-href="${ctx}/sysDept/addChildDept?id='+row.id+'"><i class="fa fa-plus"></i></a>';
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="form" data-title="修改" data-href="${ctx}/sysDept/form?id='+row.id+'"><i class="fa fa-pencil"></i></a>';
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="sys:dept:delete">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="confirm" data-title="确认删除吗" data-href="${ctx}/sysDept/delete?id='+row.id+'"><i class="fa fa-trash-o"></i></a>';
                    </@shiro.hasPermission>
                    if(str == ""){ str = "-"; }
                    return str;
                }
            }
        ];
        $("#datatree").treetable(colNames, colModel);
    })
</script>
</html>