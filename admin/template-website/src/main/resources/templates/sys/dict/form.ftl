<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        .middle{
            vertical-align: middle !important;
        }
    </style>
    <#include "/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <form id="inputForm" action="${ctx}/sysDict/save" class="form-horizontal">
                <input type="hidden" name="id" value="${sysDict.id!}">
                <div class="box-body">
                    <div class="form-unit">基本信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 字典标签：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="label" value="${sysDict.label!}" minlength="2" maxlength="40" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required " aria-required="true">*</span> 字典标识：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="code" value="${sysDict.code!}" minlength="2" maxlength="40" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required " aria-required="true">*</span> 字典类型：
                                </label>
                                <div class="col-sm-8">
                                    <select class="form-control selectpicker" name="type">
                                        <#list dictList("sys.dictType") as dict>
                                            <#if sysDict.type?? && sysDict.type == dict.value>
                                            <option value="${dict.value}" selected>${dict.name}</option>
                                            <#else>
                                            <option value="${dict.value}">${dict.name}</option>
                                            </#if>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required hide" aria-required="true">*</span> 字典描述：
                                </label>
                                <div class="col-sm-8">
                                    <textarea style="resize: none;" rows="3" name="remarks" value="${sysDict.remarks!}" maxlength="200" class="form-control " autocomplete="off"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-unit">字典信息</div>
                    <div style="padding-left: 20px;padding-right: 10px;">
                        <table class="table">
                            <thead>
                                <th>字典名称</th>
                                <th>字典值</th>
                                <th>排序</th>
                                <th><a href="javascript:void(0)" onclick="addDict();"><i class="fa fa-plus-square"></i></a></th>
                            </thead>
                            <tbody id="dictInfo">
                            <#if (sysDictChildList?size>0)>
                                <#list sysDictChildList as sysDictChild>
                                    <tr>
                                        <td><input minlength="1" maxlength="40" class="form-control required" type="text" name="name" value="${sysDictChild.name!}" autocomplete="off"/></td>
                                        <td><input minlength="1" maxlength="100" class="form-control required" type="text" name="value" value="${sysDictChild.value!}" autocomplete="off"/></td>
                                        <td><input min="1" max="100" class="form-control required" type="text" name="sort" value="${sysDictChild.sort!}" autocomplete="off"/></td>
                                        <td class="middle"><a href="javascript:void(0)" onclick="delDict(this);"><i class="fa fa-minus-square"></i></a></td>
                                    </tr>
                                </#list>
                            </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<#include "/include/footer.ftl">
<#include "/include/form.ftl">
<script>
/**
 * 添加字典
  */
function addDict() {
    var sb='<tr>';
    sb+='    <td><input minlength="1" maxlength="40" class="form-control required" type="text" name="name" value="" autocomplete="off"/></td>';
    sb+='    <td><input minlength="1" maxlength="100" class="form-control required" type="text" name="value" value="" autocomplete="off"/></td>';
    sb+='    <td><input min="1" max="100" class="form-control required" type="text" name="sort" value="" autocomplete="off"/></td>';
    sb+='    <td><a href="javascript:void(0)" onclick="delDict(this);"><i class="fa fa-minus-square"></i></a></td>';
    sb+='</tr>';
    $("#dictInfo").prepend(sb);
}
/**
 * 删除字典
 */
function delDict(_this) {
    $(_this).parent().parent().remove();
}
</script>
</html>