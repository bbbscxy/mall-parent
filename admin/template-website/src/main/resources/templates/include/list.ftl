<script>
    //根据窗口调整表格高度
    $(window).resize(function() {
        $('#datatable').bootstrapTable('resetView', {
            height: tableHeight()
        })
    })
    function tableHeight() {
        return $(window).height() - 70;
    }

    //列表查询
    function search() {
        $("#datatable").bootstrapTable('refresh');
        return false;
    }

    //提取表格表头
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

        $.fn.datatable = function (params) {
            var columns = [{
                field: 'no',
                title: '',
                align: "center",
                width: 30,
                formatter: function (value, row, index) {
                    return index+1;
                }
            }];
            for(var i=0;i<params.length;i++){
                columns.push(params[i]);
            }
            var common = {
                columns: columns,
                url: $("#searchForm").attr("action"),
                height: tableHeight(),//高度调整
                locale: "zh-CN", //中文支持
                contentType: "application/x-www-form-urlencoded",//请求数据内容格式 默认是 application/json 自己根据格式自行服务端处理
                dataType: "json",//期待返回数据类型
                method: "GET",//请求方式
            };
            $("#datatable").bootstrapTable(common);
        }
    })
</script>