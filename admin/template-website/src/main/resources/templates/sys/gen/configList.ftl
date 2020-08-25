<!DOCTYPE html>
<html>
<head>
    <title>字段列表</title>
<#include "${ctx}/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title">字段列表</div>
                <div class="box-tools pull-right">
                </div>
            </div>
            <div class="box-body">
                <form id="searchForm" action="${ctx}/genTable/configDataList?tableId=${tableId}" class="form-inline" onsubmit="return search();">
                </form>
                <table id="datatable" class="table table-hover table-striped table-bordered"></table>
            </div>
        </div>
    </div>
</div>
</body>
<#include "${ctx}/include/footer.ftl">
<#include "${ctx}/include/list.ftl">
<script>
    /**
     * 获取select选择的值
     */
    function selectData(name) {
        var element = $('[name="'+name+'"] :selected');
        var data = [];
        for(var i=0; i<element.length; i++){ data.push($(element[i]).val()) }
        return data.join(",");
    }

    /**
     * 获取checkbox选择的值
     */
    function checkData(name) {
        var element = $('input[name="'+name+'"]');
        var data = [];
        for(var i=0; i<element.length; i++){
            if($(element[i]).is(':checked')){
                data.push("1");
            }else{
                data.push("0");
            }
        }
        return data.join(",");
    }

    /**
     * 获取input的值
     */
    function inputData(name) {
        var element = $('input[name="'+name+'"]');
        var data = [];
        for(var i=0; i<element.length; i++){ data.push($(element[i]).val()) }
        return data.join(",");
    }

    /**
     * 初始化select代码
     */
    function selectHtml(name, data, selectValue) {
        var str = '<select class="form-control" name="'+name+'">';
        for(var i=0; i<data.length; i++){
            if(selectValue == data[i].value){
                str +=    '<option value="'+data[i].value+'" selected>'+data[i].name+'</option>';
            }else{
                str +=    '<option value="'+data[i].value+'">'+data[i].name+'</option>';
            }
        }
        str +=    '</select>';
        return str;
    }

    /**
     * 提交表单
     */
    function submitForm() {
        window.parent.layer.load(1, { shade: [0.3,'#fff'] });
        $.ajax({
            url: "${ctx}/genTable/config",
            type: "POST",
            data: {
                tableId: '${tableId}',
                isList: checkData("isList"),
                isEdit: checkData("isEdit"),
                isQuery: checkData("isQuery"),
                queryType: selectData("queryType"),
                showType: selectData("showType"),
                dictType: inputData("dictType"),
                displayName: inputData("displayName")
            },
            success: function(result){
                window.parent.layer.closeAll();
                window.parent.layer.msg(result.msg, { time: 2000 });
            }
        })
    }

    $(function () {
        $("#datatable").datatable([
            {
                field: "name", title: "名称", align: "center",
                formatter: function (value, row, index) {
                    return '<span style="color: #3c8dbc">'+value+'</span>';
                }
            },
            { field: "jdbcType", title: "JDBC类型", align: "center" },
            {
                field: "isList", title: "列表", align: "center",
                formatter: function (value, row, index) {
                    if(!value || "1" == value){
                        return '<input checked type="checkbox" name="isList" value="1"/>';
                    }else{
                        return '<input type="checkbox" name="isList" value="0"/>';
                    }
                }
            },
            {
                field: "isEdit", title: "编辑", align: "center",
                formatter: function (value, row, index) {
                    if(!value || "1" == value){
                        return '<input checked type="checkbox" name="isEdit" value="1"/>';
                    }else{
                        return '<input type="checkbox" name="isEdit" value="0"/>';
                    }
                }
            },
            {
                field: "isQuery", title: "查询", align: "center",
                formatter: function (value, row, index) {
                    if(value && "1" == value){
                        return '<input checked type="checkbox" name="isQuery" value="1"/>';
                    }else{
                        return '<input type="checkbox" name="isQuery" value="0"/>';
                    }
                }
            },
            {
                field: "queryType", title: "查询方式", align: "center",
                formatter: function (value, row, index) {
                    var data = [];
                    <#list dictList("sys.genField.queryType") as dict>
                    data.push({ name: '${dict.name}', value: '${dict.value}' })
                    </#list>
                    return selectHtml("queryType", data, value);
                }
            },
            {
                field: "showType", title: "展示方式", align: "center",
                formatter: function (value, row, index) {
                    var str = '<select class="form-control" name="showType">';
                    var data = [];
                    <#list dictList("sys.genField.showType") as dict>
                    data.push({ name: '${dict.name}', value: '${dict.value}' })
                    </#list>
                    return selectHtml("showType", data, value);
                }
            },
            {
                field: "dictType", title: "字典名称", align: "center", width: 140,
                formatter: function (value, row, index) {
                    if(!value){ value = ""; }
                    var str = '<input type="text" class="form-control" name="dictType" value="'+value+'" autocomplete="off"/>';
                    return str;
                }
            },
            {
                field: "displayName", title: "显示名称", align: "center", width: 140,
                formatter: function (value, row, index) {
                    if(!value){ value = row.comments; }
                    var str = '<input type="text" class="form-control" name="displayName" value="'+value+'" autocomplete="off"/>';
                    return str;
                }
            },
        ])
    })

    $('#datatable').on('load-success.bs.table', function (e) {
        $('input').iCheck({ checkboxClass: 'icheckbox_square-blue', radioClass: 'iradio_square-blue', increaseArea: '20%' });
    });
</script>
</html>