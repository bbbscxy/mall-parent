<!DOCTYPE html>
<html>
<head>
    <title>请求参数</title>
    <#include "${ctx}/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-body">
                <pre id="cacheValue" style="padding-left: 20px;height: 425px;"></pre>
            </div>
        </div>
    </div>
</div>
</body>
<#include "${ctx}/include/footer.ftl">
<#include "${ctx}/include/list.ftl">
<script>
    $(function () {
        $('#cacheValue').jsonViewer(JSON.parse('${params}'), {});
    })
</script>
</html>