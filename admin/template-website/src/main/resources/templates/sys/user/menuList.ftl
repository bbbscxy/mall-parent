<!DOCTYPE html>
<html>
<head>
    <title>角色权限</title>
    <#include "${ctx}/include/header.ftl">
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-body">
                <ul id="menuTree" class="ztree" style="margin-left: 20px;"></ul>
            </div>
        </div>
    </div>
</div>
</body>
<#include "${ctx}/include/footer.ftl">
<#include "${ctx}/include/list.ftl">
<script>
    $(function () {
        $(function () {
            $.ajax({
                url: "${ctx}/sysUser/menuZTreeDataList?roleId="+'${roleId}',
                type: "GET",
                success: function(result){
                    if(result.code == "SUCCESS"){
                        $.fn.zTree.init($("#menuTree"), {
                            check: { enable: false },
                            data: {
                                simpleData: {
                                    enable: true
                                }
                            },
                            view:{
                                showLine: true,
                            }
                        }, result.data);
                    }
                }
            })
        })
    })
</script>
</html>