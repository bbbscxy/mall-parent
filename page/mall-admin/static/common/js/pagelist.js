//根据窗口调整表格高度
$(window).resize(function() {
    $('#datatable').bootstrapTable('resetView', {
        height: tableHeight()
    })
})
function tableHeight() {
    return $(window).height() - $("#searchForm").height() - 75;
}

//列表查询
function search() {
    $("#datatable").bootstrapTable('refreshOptions', { pageNumber: 1, silent:true });
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
                //获取每页显示的数量
                var pageSize=$("#datatable").bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber=$("#datatable").bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                return pageSize * (pageNumber - 1) + index + 1;
            }
        }];
        for(var i=0;i<params.length;i++){
            columns.push(params[i]);
        }
        var common = {
            columns: columns,
            url: $("#searchForm").attr("action"),
            height: tableHeight(),//高度调整
            pagination: true,//是否分页
            pageSize: 20,//单页记录数
            pageList: [5, 10, 20, 50],//分页步进值
            locale: "zh-CN", //中文支持
            sidePagination: "server",//服务端分页
            contentType: "application/x-www-form-urlencoded",//请求数据内容格式 默认是 application/json 自己根据格式自行服务端处理
            dataType: "json",//期待返回数据类型
            method: "GET",//请求方式
            queryParams: function(params){
                var param = {};
                var form = $('#searchForm').serializeObject();
                param['pageNum'] = parseInt(params.offset)/parseInt(params.limit)+1; // 页码
                param['pageSize'] = params.limit; // 条数
                $.extend(param, form);
                return param;
            },
            responseHandler : function (res) {
                return {
                    "total": res.total,
                    "rows": res.list
                }
            }
        };
        $("#datatable").bootstrapTable(common);
    }
})