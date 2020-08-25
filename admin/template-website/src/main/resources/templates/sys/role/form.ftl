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
            <form id="inputForm" action="${ctx}/sysRole/save" class="form-horizontal">
                <input type="hidden" name="id" value="${sysRole.id!}">
                <input type="hidden" name="menuIds" value="${menuIds}"/>
                <div class="box-body">
                    <div class="form-unit">基本信息</div>
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required ">*</span> 角色名称：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" value="${sysRole.name!}" minlength="2" maxlength="40" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div class="form-group">
                                <label class="control-label col-sm-4" title="">
                                    <span class="required " aria-required="true">*</span> 英文名称：
                                </label>
                                <div class="col-sm-8">
                                    <input type="text" name="enname" value="${sysRole.enname!}" minlength="2" maxlength="40" class="form-control required" autocomplete="off"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-unit">权限信息</div>
                    <ul id="menuTree" class="ztree" style="margin-left: 20px;"></ul>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<#include "/include/footer.ftl">
<#include "/include/form.ftl">
<script type="text/javascript">
    //获取所有的选中节点
    function getNodeIds() {
        var zTree = $.fn.zTree.getZTreeObj("menuTree");
        var nodes=zTree.getCheckedNodes(true);
        var menuIds = [];
        for(var i=0; i<nodes.length; i++){
            menuIds.push(nodes[i].id);
        }
        $("input[name='menuIds']").val(menuIds);
    }

    $(function () {
        $.ajax({
            url: "${ctx}/sysRole/zTreeDataList?roleId="+'${sysRole.id!}',
            type: "GET",
            success: function(result){
                if(result.code == "SUCCESS"){
                    $.fn.zTree.init($("#menuTree"), {
                        check: { enable: true },
                        data: {
                            simpleData: {
                                enable: true
                            }
                        },
                        view:{
                            showLine: true,
                        },
                        callback: {
                            onClick: function (e, treeId, treeNode, clickFlag) {
                                var zTree = $.fn.zTree.getZTreeObj("menuTree");
                                zTree.checkNode(treeNode, !treeNode.checked, true);
                                getNodeIds();
                            },
                            onCheck: function (e, treeId, treeNode, clickFlag) {
                                getNodeIds();
                            }
                        },
                    }, result.data);
                    var zTree = $.fn.zTree.getZTreeObj("menuTree");
                    zTree.setting.check.chkboxType = { "Y":"ps", "N":"ps" };
                }
            }
        })
    })
</script>
</html>