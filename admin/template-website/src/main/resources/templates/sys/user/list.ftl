<!DOCTYPE html>
<html>
<head>
    <title>组织机构</title>
    <#include "/include/header.ftl">
</head>
<body>
<div class="ui-layout-west">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title">
                    <i class="fa fa-map-o"></i>组织机构
                </div>
            </div>
            <div class="ui-layout-content">
                <div id="deptTree" class="ztree"></div>
            </div>
        </div>
    </div>
</div>
<div class="ui-layout-center">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title"><i class="fa fa-user-o"></i>用户管理</div>
                <div class="box-tools pull-right">
                <@shiro.hasPermission name="sys:user:form">
                    <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-type="form" data-title="新增用户" data-href="${ctx}/sysUser/form"><i class="fa fa-plus"></i> 新增</a>
                </@shiro.hasPermission>
                <@shiro.hasPermission name="sys:user:form">
                    <a href="javascript:void(0);" onclick="exportFile()" class="btn btn-default"><i class="fa fa-file-excel-o"></i> 导出</a>
                </@shiro.hasPermission>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/sysUser/dataList" class="form-inline" onsubmit="return search();">
                    <input type="hidden" name="deptId"/>
                    <div class="form-group">
                        <label class="control-label">账号：</label>
                        <div class="control-inline">
                            <input type="text" name="loginName" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">用户姓名：</label>
                        <div class="control-inline">
                            <input type="text" name="name" value="" maxlength="100" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label">是否允许登录：</label>
                        <div class="control-inline">
                            <select class="form-control" style="width: 120px;" name="loginFlag">
                                <option value="" selected>全部</option>
                            <#list dictList("sys.loginFlag") as dict>
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
    $('body').layout({
        west__initClosed: false, // 是否默认关闭
        west__size: 150,
        spacing_open:8,
    });
</script>
<script>
    /**
     * 导出
     */
    function exportFile() {
        window.parent.layer.confirm("确认导出用户列表？",{
            btn: ['确认','取消']
        }, function(){
            window.location.href = '${ctx}/sysUser/export?loginName='+$("input[name='loginName']").val()+"&name="+$("input[name='name']").val();
            window.parent.layer.closeAll();
        }, function(){});
    }

    $(function () {
        //渲染ztree树
        $.ajax({
            url: "${ctx}/sysDept/zTreeDataList",
            type: "GET",
            success: function(result){
                if(result.code == "SUCCESS"){
                    $.fn.zTree.init($("#deptTree"), {
                        data: {
                            simpleData: {
                                enable: true
                            }
                        },
                        callback: {
                            onClick: function (event, treeId, treeNode) {
                                $("input[name='deptId']").val(treeNode.id);
                                search();
                            }
                        },
                    }, result.data);
                }
            }
        })

        //渲染表格
        $("#datatable").datatable([
            {
                field: "loginName", title: "账号", width: "200",
                formatter: function (value, row, index) {
                    return '<span class="primary">'+value+'</span>';
                }
            },
            {
                field: "name", title: "用户名称", width: "200",
                formatter: function (value, row, index) {
                    return '<span class="primary">'+value+'</span>';
                }
            },
            { field: "sysDept.deptName", title: "归属部门", width: "200" },
            {
                field: "loginFlag", title: "状态", align: "center", width: "120",
                formatter: function (value, row, index) {
                    var val = value;
                <#list dictList("sys.loginFlag") as dict>
                    if('${dict.value}' == value){ val = '${dict.name}' }
                </#list>
                    return val;
                }
            },
            {
                field: "sysRole.name", title: "用户角色", align: "center", width: "160",
                formatter: function (value, row, index) {
                    if(!value){
                        return "-";
                    }else{
                        return '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="show" data-title="用户权限" data-href="${ctx}/sysUser/menuList?roleId='+row.sysRole.id+'">'+value+'</a>';
                    }
                }
            },
            { field: "updateDate", title: "更新时间", align: "center", width: "200" },
            {
                title: "操作", align: "center", width: "90",
                formatter: function (value, row, index) {
                    var str = "";
                <@shiro.hasPermission name="sys:user:form">
                    str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="form" data-title="修改用户" data-href="${ctx}/sysUser/form?id='+row.id+'"><i class="fa fa-pencil"></i></a>';
                </@shiro.hasPermission>
                <@shiro.hasPermission name="sys:user:delete">
                    str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="confirm" data-title="确定删除用户吗？" data-href="${ctx}/sysUser/delete?id='+row.id+'"><i class="fa fa-trash-o"></i></a>';
                </@shiro.hasPermission>
                    if(str == ""){ str = "-"; }
                    return str;
                }
            },
        ])
    })
</script>