<!DOCTYPE html>
<html>
<head>
    <title>菜单列表</title>
    <#include "/include/header.ftl">
</head>
<body>
    <div id="menuTree" class="ztree" style="margin: 10px 0px 0px 20px;"></div>
</body>
<#include "/include/footer.ftl">
<script>
    $(function () {
        //渲染ztree树
        $.ajax({
            url: "${ctx}/sysMenu/zTreeDataList",
            type: "GET",
            success: function(result){
                if(result.code == "SUCCESS"){
                    $.fn.zTree.init($("#menuTree"), {
                        data: {
                            simpleData: {
                                enable: true
                            }
                        },
                        callback: {
                            onClick: function (event, treeId, treeNode) {
                                parent.$("input[name='parentId']").val(treeNode.id);
                                parent.$("input[name='parentIds']").val(treeNode.id+","+treeNode.pIds);
                                parent.$("input[name='parentMenuName']").val(treeNode.name);
                                parent.layer.close(parent.layer.getFrameIndex(window.name))
                            }
                        },
                    }, result.data);
                }
            }
        })
    })
</script>
</html>