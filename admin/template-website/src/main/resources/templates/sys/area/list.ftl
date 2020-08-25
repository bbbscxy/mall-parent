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
                <div class="box-title"><i class="fa fa-building-o"></i>地区管理</div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysArea/dataList" class="form-inline" onsubmit="return search();">
                    <div class="form-group">
                        <label class="control-label">区域名称：</label>
                        <div class="control-inline">
                            <input type="text" name="areaName" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
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
        var colNames = ['id','区域名称', '区域编码', '级别'];
        var colModel = [
            {name:'id',index:'id', hidden:true},
            {
                name:'name',index:'name', align: 'left', sortable: false,
                formatter: function(val, options, row){
                    return '<span class="primary">('+row.code+')&nbsp;&nbsp;'+val+'</span>';
                }
            },
            { name:'code',index:'code', align: 'center', sortable: false },
            {
                name:'level',index:'level', align: 'center', sortable: false,
                formatter: function(val, options, row){
                    if(val == "0"){
                        return "省份";
                    }else if(val == "1"){
                        return "城市";
                    }else{
                        return "区域";
                    }
                }
            }
        ];
        $("#datatree").treetable(colNames, colModel);

    })
</script>
</html>