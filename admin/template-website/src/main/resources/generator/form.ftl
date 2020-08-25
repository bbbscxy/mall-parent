<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <${r'#'}include "${r'${ctx}'}/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <form id="inputForm" action="${r'${ctx}'}/${moduleName}/save" class="form-horizontal">
                <input type="hidden" name="id" value="${r'${'}${moduleName}.id!${r'}'}">
                <div class="box-body">
                    <div class="form-unit">基本信息</div>
                <#list tableColumnList as tableColumn>
                <#if tableColumn.isEdit == "1">
                    <#if tableColumn.showType == "0">
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> ${tableColumn.displayName}：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="${tableColumn.javaField}" value="${r'${'}${moduleName}.${tableColumn.javaField}!${r'}'}" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    </#if>
                    <#if tableColumn.showType == "1">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="form-group">
                                    <label class="control-label col-sm-4" title="">
                                        <span class="required ">*</span> ${tableColumn.displayName}：
                                    </label>
                                    <div class="col-sm-8">
                                        <textarea style="resize: none;" rows="3" name="${tableColumn.javaField}" value="${r'${'}${moduleName}.${tableColumn.javaField}!${r'}'}" maxlength="200" class="form-control " autocomplete="off"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                    <#if tableColumn.showType == "2">
                        <div class="row">
                            <div class="col-xs-6">
                                <div class="form-group">
                                    <label class="control-label col-sm-4" title="">
                                        <span class="required ">*</span> ${tableColumn.displayName}：
                                    </label>
                                    <div class="col-sm-8">
                                        <select class="form-control selectpicker" name="loginFlag">
                                            <${r'#'}list dictList("${tableColumn.dictType}") as dict>
                                            <${r'#'}if ${r'$'}{tableColumn.displayName}?? ${r'&&'} ${r'$'}{tableColumn.displayName} == dict.value>
                                            <option value="${r'$'}{dict.value}" selected>${r'$'}{dict.name}</option>
                                            <${r'#'}else>
                                            <option value="${r'$'}{dict.value}">${r'$'}{dict.name}</option>
                                            </${r'#'}if>
                                            </${r'#'}list>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                </#if>
                </#list>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<${r'#'}include "${r'${ctx}'}/include/footer.ftl">
<${r'#'}include "${r'${ctx}'}/include/form.ftl">
</html>