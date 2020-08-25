<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <#include "${ctx}/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <form id="inputForm" action="${ctx}/sysDept/save" class="form-horizontal">
                <input type="hidden" name="id" value="${sysDept.id!}">
                <div class="box-body">
                    <div class="form-unit">基本信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 上级部门：
                                </label>
                                <div class="col-sm-8 icheck">
                                    <#if sysDept.parentId??>
                                        <input type="hidden" name="parentId" value="${sysDept.parentId}"/>
                                        <input type="hidden" name="parentIds" value="${sysDept.parentIds}"/>
                                        <input readonly="readonly" type="text" name="parentDeptName" value="${parentDeptName}" class="form-control required selectDept" autocomplete="off"/>
                                    <#else>
                                        <input type="hidden" name="parentId" value=""/>
                                        <input type="hidden" name="parentIds" value=""/>
                                        <input readonly="readonly" type="text" name="parentDeptName" value="" class="form-control required selectDept" autocomplete="off"/>
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 部门名称：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="deptName" value="${sysDept.deptName!}" minlength="2" maxlength="40" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-unit">其他信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required hide" aria-required="true">*</span> 排序：
                                </label>
                                <div class="col-sm-8 input-group" style="padding-left: 15px;padding-right: 15px;">
                                    <span class="input-group-btn">
                                       <button class="btn btn-default subBtn" type="button">-</button>
                                    </span>
                                    <input type="text" name="sort" value="${sysDept.sort!}" max="100" class="form-control " autocomplete="off"/>
                                    <span class="input-group-btn">
                                       <button class="btn btn-default addBtn" type="button">+</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required hide">*</span> 备注信息：
                                </label>
                                <div class="col-sm-8">
                                    <textarea style="resize: none;" rows="3" name="remarks" maxlength="200" class="form-control " autocomplete="off">${sysDept.remarks!}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<#include "${ctx}/include/footer.ftl">
<#include "${ctx}/include/form.ftl">
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
            content: "${ctx}/sysDept/select?type=parentDeptName",
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