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
                <div class="box-title"><i class="fa fa-building-o"></i>菜单管理</div>
                <div class="box-tools pull-right">
                <@shiro.hasPermission name="sys:menu:form">
                    <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-type="form" data-title="添加菜单" data-href="${ctx}/sysMenu/form?type=menu"><i class="fa fa-plus"></i> 新增</a>
                </@shiro.hasPermission>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysMenu/dataList"" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">菜单名称：</label>
                        <div class="control-inline">
                            <input type="text" name="menuName" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
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
        var colNames = ['id','菜单名称', '排序', '类型', '链接地址', '权限标识', '操作'];
        var colModel = [
            { name:'id',index:'id', hidden:true,formatter: function (value, row, index) { return value ? value : "-" }},
            {
                name:'name',index:'name', align: 'left', sortable: false,width: '200',
                formatter: function(val, options, row){
                    if(row.level == "1"){
                        return '<span class="primary"><i class="fa fa-square-o" style="margin-right: 5px;"></i>'+val+'</span>';
                    }else if(row.level == "2"){
                        return '<span class="primary"><i class="fa fa-square-o" style="margin-right: 5px;"></i>'+val+'</span>';
                    }else if(row.level == "3"){
                        return '<span class="primary">'+val+'</span>';
                    }else{
                        return '<span class="primary"><i class="fa fa-desktop" style="margin-right: 5px;"></i>'+val+'</span>';
                    }
                }
            },
            { name:'sort',index:'sort', align: 'center', sortable: false, width: '50' },
            {
                name:'sort',index:'sort', align: 'center', sortable: false, width: '50',
                formatter: function(val, options, row){
                    if(row.level == "3"){
                        return '<span class="primary">权限</span>';
                    }else{
                        return '菜单';
                    }
                }
            },
            { name:'href',index:'href', align: 'center', sortable: false,formatter: function (value, row, index) { return value ? value : "-" } },
            { name:'permission',index:'permission', align: 'center', sortable: false,formatter: function (value, row, index) { return value ? value : "-" } },
            {
                name:'operate',align: 'center', sortable: false, width: "70",
                formatter: function(val, options, row){
                    var str = "";
                    var type = "menu";
                    if(row.level == "3"){
                        type = "button";
                    }
                    if(type == "menu"){
                    <@shiro.hasPermission name="sys:menu:form">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="form" data-title="添加菜单" data-href="${ctx}/sysMenu/addChildMenu?type='+type+'&parentId='+row.id+'"><i class="fa fa-plus"></i></a>';
                    </@shiro.hasPermission>
                    }
                <@shiro.hasPermission name="sys:menu:form">
                    str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="form" data-title="修改菜单" data-href="${ctx}/sysMenu/form?type='+type+'&id='+row.id+'"><i class="fa fa-pencil"></i></a>';
                </@shiro.hasPermission>
                <@shiro.hasPermission name="sys:menu:delete">
                    str += '<a style="padding-right:15px;" class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="confirm" data-title="确认删除菜单吗" data-href="${ctx}/sysMenu/delete?id='+row.id+'"><i class="fa fa-trash-o"></i></a>';
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