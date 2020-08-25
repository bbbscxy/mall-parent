<!DOCTYPE html>
<html>
<head>
    <title>管理</title>
    <${r'#'}include "${r'${ctx}'}/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title">管理</div>
                <div class="box-tools pull-right">
                <${r'@'}shiro.hasPermission name="${packageModuleName}:${moduleName}:form">
                    <a href="javascript:void(0);" onclick="tableDialog(this)" class="btn btn-default" data-type="form" data-title="新增" data-href="${r'${ctx}'}/${moduleName}/form"><i class="fa fa-plus"></i> 新增</a>
                </${r'@'}shiro.hasPermission>
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${r'${ctx}'}/${moduleName}/dataList" class="form-inline" onsubmit="return search();">
                <#list tableColumnList as tableColumn>
                <#if tableColumn.isQuery == "1">
                    <#if tableColumn.showType == "0">
                    <div class="form-group">
                        <label class="control-label">${tableColumn.displayName}：</label>
                        <div class="control-inline">
                            <input type="text" name="${tableColumn.javaField}" value="" class="form-control width-120" autocomplete="off" />
                        </div>
                    </div>
                    </#if>
                    <#if tableColumn.showType == "2">
                    <div class="form-group">
                        <label class="control-label">${tableColumn.displayName}：</label>
                        <div class="control-inline">
                            <select class="form-control" style="width: 120px;" name="${tableColumn.javaField}">
                                <option value="" selected>全部</option>
                                <${r'#'}list dictList("${tableColumn.dictType}") as dict>
                                    <option value="${r'$'}{dict.value}">${r'$'}{dict.name}</option>
                                </${r'#'}list>
                            </select>
                        </div>
                    </div>
                    </#if>
                    <#if tableColumn.showType == "3">
                    <div class="form-group">
                        <label class="control-label"><i class="fa fa-calendar"></i>：</label>
                        <div class="control-inline" date-range-picker>
                            <input readonly="readonly" name="startTime" placeholder="开始时间" value="" class="form-control width-120" type="text" />
                            <input readonly="readonly" name="endTime" placeholder="结束时间" value="" class="form-control width-120" type="text" />
                        </div>
                    </div>
                    </#if>
                </#if>
                </#list>
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
<${r'#'}include "${r'${ctx}'}/include/footer.ftl">
<${r'#'}include "${r'${ctx}'}/include/pagelist.ftl">
<script>
    $(function () {
        $("#datatable").datatable([
        <#list tableColumnList as tableColumn>
        <#if tableColumn.isList == "1">
            <#if tableColumn_index == 0>
            {
                field: "${tableColumn.javaField}", title: "${tableColumn.displayName}", width: "200",
                formatter: function (value, row, index) {
                    return '<span class="primary">'+value+'</span>';
                }
            },
            <#else>
            <#if tableColumn.showType == "2">
            {
                field: "${tableColumn.javaField}", title: "${tableColumn.displayName}", align: "center", width: "200",
                formatter: function (value, row, index) {
                    var val = value;
                    <${r'#'}list dictList("${tableColumn.dictType}") as dict>
                        if('${r'$'}{dict.value}' == value){ val = '${r'$'}{dict.name}' }
                    </${r'#'}list>
                    return val;
                }
            },
            <#else>
            { field: "${tableColumn.javaField}", title: "${tableColumn.displayName}", align: "center", width: "200" },
            </#if>
            </#if>
        </#if>
        </#list>
            {
                title: "操作", align: "center", width: "100",
                formatter: function (value, row, index) {
                    var str = "";
                    <${r'@'}shiro.hasPermission name="${packageModuleName}:${moduleName}:form">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="form" data-title="修改" data-href="${r'${ctx}'}/${moduleName}/form?id='+row.id+'"><i class="fa fa-pencil"></i></a>';
                    </${r'@'}shiro.hasPermission>
                    <${r'@'}shiro.hasPermission name="${packageModuleName}:${moduleName}:delete">
                        str += '<a class="list-btn" href="javascript:void(0);" onclick="tableDialog(this)" data-type="confirm" data-title="确认删除吗" data-href="${r'${ctx}'}/${moduleName}/delete?id='+row.id+'"><i class="fa fa-trash-o"></i></a>';
                    </${r'@'}shiro.hasPermission>
                    if(str == ""){ str = "-"; }
                    return str;
                }
            },
        ])
    })
</script>
</html>