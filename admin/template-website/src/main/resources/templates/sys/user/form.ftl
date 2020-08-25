<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <#include "/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <form id="inputForm" action="${ctx}/sysUser/save" class="form-horizontal">
                <input type="hidden" name="id" value="${sysUser.id!}"/>
                <input type="hidden" name="roleIds" value="${roleIds}"/>
                <div class="box-body">
                    <div class="form-unit">基本信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 账号：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="loginName" value="${sysUser.loginName!}" minlength="2" maxlength="40" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required " aria-required="true">*</span> 姓名：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" value="${sysUser.name!}" minlength="2" maxlength="40" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 允许登录：
                                </label>
                                <div class="col-sm-8 icheck">
                                    <label class="control-label">
                                        <#if sysUser.loginFlag??>
                                            <input type="radio" name="loginFlag" value="1" class="required" <#if sysUser.loginFlag == '1'>checked</#if> />允许
                                        <#else>
                                            <input type="radio" name="loginFlag" value="1" class="required" checked />允许
                                        </#if>
                                    </label>
                                    <label class="control-label">
                                        <input type="radio" name="loginFlag" value="0" class="required" <#if sysUser.loginFlag?? && sysUser.loginFlag == '0'>checked</#if> />禁止
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 部门名称：
                                </label>
                                <div class="col-sm-8 icheck">
                                    <#if sysUser.sysDept??>
                                        <input type="hidden" name="deptId" value="${sysUser.sysDept.id}"/>
                                        <input readonly="readonly" type="text" name="deptName" value="${sysUser.sysDept.deptName}" class="form-control required selectDept" autocomplete="off"/>
                                    <#else>
                                        <input type="hidden" name="deptId" value=""/>
                                        <input readonly="readonly" type="text" name="deptName" value="" class="form-control required selectDept" autocomplete="off"/>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-unit">附属信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required hide" aria-required="true">*</span> 电话：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="phone" value="${sysUser.phone!}" class="form-control " autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required hide" aria-required="true">*</span> 邮箱：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="email" value="${sysUser.email!}" class="form-control " autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-unit">角色信息</div>
                    <table id="roleTable" class="table table-hover table-striped table-bordered">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<#include "/include/footer.ftl">
<#include "/include/form.ftl">
<script>
    //选择部门
    $(".selectDept").click(function () {
        layer.open({
            type: 2,
            title: "选择部门",
            shadeClose: false,
            offset: '40px',
            shade: 0.1,
            area: ['300px', '400px'],
            content: "${ctx}/sysDept/select?type=deptName",
        });
    })

    $("#roleTable").bootstrapTable({
        url: '${ctx}/sysUser/roleList',
        singleSelect: true,
        columns: [
            {
                checkbox: true,
                align : 'center',
                formatter: function (i,row) {
                    var roleIds = '${roleIds}';
                    roleIds = roleIds.split(",");
                    if(roleIds.indexOf(row.id) == -1){
                        return { checked : false }
                    }else{
                        return { checked : true }
                    }
                }
            },
            { field: "name", title: "角色名称", align : 'center' },
            { field: "enname", title: "英文名称", align : 'center' },
        ]
    });

    //checkbox 事件
    $('#roleTable').on('uncheck.bs.table check.bs.table check-all.bs.table uncheck-all.bs.table',function(e,rows){
        var datas = $.isArray(rows) ? rows : [rows];
        if(e.type.indexOf('uncheck') != -1){
            //未选中
            var roleIds = $("input[name='roleIds']").val().split(",");
            $.each(datas,function(i,v){
                roleIds.splice(roleIds.indexOf(v.id),1);
            });
            $("input[name='roleIds']").val(roleIds);
        }else{
            //选中
            var roleIds = $("input[name='roleIds']").val().split(",");
            $.each(datas,function(i,v){
                roleIds.indexOf(v.id) == -1 ? roleIds.push(v.id) : -1;
            });
            $("input[name='roleIds']").val(roleIds);
        }
    });
</script>
</html>