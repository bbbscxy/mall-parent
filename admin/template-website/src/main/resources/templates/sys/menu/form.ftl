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
            <form id="inputForm" action="${ctx}/sysMenu/save" class="form-horizontal">
                <input type="hidden" name="id" value="${sysMenu.id!}">
                <#if type == 'menu'>
                    <input type="hidden" name="isShow" value="1">
                <#else>
                    <input type="hidden" name="isShow" value="0">
                </#if>

                <div class="box-body">
                    <div class="form-unit">基本信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 上级菜单：
                                </label>
                                <div class="col-sm-8 icheck">
                                    <#if sysMenu.parentId??>
                                        <input type="hidden" name="parentId" value="${sysMenu.parentId}"/>
                                        <input type="hidden" name="parentIds" value="${sysMenu.parentIds}"/>
                                        <input readonly="readonly" type="text" name="parentMenuName" value="${parentMenuName}" class="form-control required selectMenu" autocomplete="off"/>
                                    <#else>
                                        <input type="hidden" name="parentId" value="0"/>
                                        <input type="hidden" name="parentIds" value="0"/>
                                        <input readonly="readonly" type="text" name="parentMenuName" value="顶层菜单" class="form-control required selectMenu" autocomplete="off"/>
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                <#if type == 'menu'>
                                    <span class="required ">*</span> 菜单名称：
                                <#else>
                                    <span class="required ">*</span> 权限名称：
                                </#if>
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" value="${sysMenu.name!}" minlength="2" maxlength="40" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                <#if type == 'menu'>
                    <div class="form-unit">其他信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required hide" aria-required="true">*</span> 菜单链接：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="href" value="${sysMenu.href!}" maxlength="100" class="form-control " autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required " aria-required="true">*</span> 排序：
                                </label>
                                <div class="col-sm-8 input-group" style="padding-left: 15px;padding-right: 15px;">
                                    <span class="input-group-btn">
                                       <button class="btn btn-default subBtn" type="button">-</button>
                                    </span>
                                    <input type="text" name="sort" value="${sysMenu.sort!}" max="100" class="form-control required" autocomplete="off"/>
                                    <span class="input-group-btn">
                                       <button class="btn btn-default addBtn" type="button">+</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                <#else>
                    <div class="form-unit">其他信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required hide" aria-required="true">*</span> 权限编码：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="permission" value="${sysMenu.permission!}" maxlength="100" class="form-control " autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </#if>
            </form>
        </div>
    </div>
</div>
</body>
<#include "/include/footer.ftl">
<#include "/include/form.ftl">
<script>
//选择菜单
$(".selectMenu").click(function () {
    layer.open({
        type: 2,
        title: "选择菜单",
        shadeClose: false,
        offset: '40px',
        shade: 0.1,
        area: ['300px', '400px'],
        content: "${ctx}/sysMenu/select",
    });
})

$(".subBtn").click(function () {
    var sort = $("input[name='sort']");
    var num = sort.val();
    if(!isNaN(num)){
        if(num == ""){ num = 0 }
        if(num < 1){ num = 1 }
        sort.val(parseInt(num)-1);
    }else{
        sort.val(0);
    }
})
$(".addBtn").click(function () {
    var sort = $("input[name='sort']");
    var num = sort.val();
    if(!isNaN(num)){
        if(num == ""){ num = 0 }
        sort.val(parseInt(num)+1);
    }else{
        sort.val(0);
    }
})
</script>
</html>