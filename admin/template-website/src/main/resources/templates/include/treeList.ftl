<link rel="stylesheet" href="${ctxStatic}/plugins/bootstrap-table/jquery.treegrid.min.css" type="text/css">
<script type="text/javascript" src="${ctxStatic}/plugins/bootstrap-table/jquery.treegrid.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugins/bootstrap-table/bootstrap-table-treegrid.js"></script>

<script>
    //根据窗口调整表格高度
    $(window).resize(function() {
        $("#datatree").setGridWidth($(window).width()-20);
    })

    //列表查询
    function search() {
        $("#datatree").jqGrid('setGridParam', {
            postData: $('#searchForm').serializeObject()
        }).trigger("reloadGrid");
        return false;
    }

    $(function() {
        /**
         * 序列化表单
         */
        $.fn.serializeObject = function() {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function() {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [ o[this.name] ];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        }

        /**
         * 树表格
         * @param colNames
         * @param columns
         */
        $.fn.treetable = function (colNames, columns) {
            $("#datatree").jqGrid({
                url : $("#searchForm").attr("action"),
                datatype: "json",
                treeGrid: true,
                treeGridModel: 'adjacency',
                tree_root_level : 0,
                loadonce: false,
                gridview : true,
                ExpandColumn : 'name',
                ExpandColClick : true,
                colNames: colNames,
                colModel: columns ,
                jsonReader: {
                    root: "data",
                    repeatitems : false
                },
                treeReader : {
                    level_field: "level",
                    parent_id_field: "parentId",
                    leaf_field: "leaf",
                    expanded_field: "expanded"
                },
                shrinkToFit: true,
                height: $(window).height() - $("#searchForm").height() - 140,
                autowidth:true,
                loadComplete: function () {
                    $("#datatree").setGridWidth($(window).width()-20);
                }
            });
        }
    })
</script>