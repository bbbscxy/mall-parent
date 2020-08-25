<!DOCTYPE html>
<html>
<head>
    <title>组织机构</title>
<#include "/include/header.ftl">
</head>
<body>
    <div id="deptTree" class="ztree" style="margin: 10px 0px 0px 20px;"></div>
</body>
<#include "/include/footer.ftl">
<script>
    $(function () {
        //渲染ztree树
        $.ajax({
            url: "${ctx}/sysDept/zTreeDataList",
            type: "GET",
            success: function(result){
                if(result.code == "SUCCESS"){
                    $.fn.zTree.init($("#deptTree"), {
                        data: {
                            simpleData: {
                                enable: true
                            }
                        },
                        callback: {
                            onClick: function (event, treeId, treeNode) {
                                var type = '${type}';
                                if("deptName" == type){
                                    parent.$("input[name='deptId']").val(treeNode.id);
                                    parent.$("input[name='deptName']").val(treeNode.name);
                                }else{
                                    parent.$("input[name='parentId']").val(treeNode.id);
                                    parent.$("input[name='parentIds']").val(treeNode.id+","+treeNode.pIds);
                                    parent.$("input[name='parentDeptName']").val(treeNode.name);
                                }
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