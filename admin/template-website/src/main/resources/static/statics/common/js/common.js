/**
 * Created by Administrator on 2019/7/22.
 */
$(function () {
    //初始化select框
    $("select").select2();

    //初始化radio
    $('input').iCheck({ checkboxClass: 'icheckbox_square-blue', radioClass: 'iradio_square-blue', increaseArea: '20%' });

    //初始化时间框
    $('[date-range-picker]').daterangepicker({
        startDate: moment(),
        endDate: moment(),
        ranges : {
            '上周': [moment().startOf('week').subtract(6, 'days'),moment().startOf('week')],
            '本周': [moment().startOf('week').add( 1,'days'),moment().endOf('week').add(1, 'days')],
            '上月': [moment().subtract(1,'months').startOf('months'),moment().subtract(1,'months').endOf('months')],
            '本月': [moment().startOf('months'),moment().endOf('months')],
        },
        showDropdowns: true,
        opens:'right',
        timePicker: false,
        timePicker24Hour: false,
        timePickerSeconds: false,
        timePickerIncrement: 1,
        autoUpdateInput: false,
        format: 'YYYY-MM-DD',
        applyClass : 'btn btn-primary',
        locale: {
            applyLabel : '确定',
            cancelLabel : '取消',
            fromLabel : '起始时间',
            toLabel : '结束时间',
            customRangeLabel : '自定义',
            daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
            monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月','七月', '八月', '九月', '十月', '十一月', '十二月' ],
            firstDay : 1
        }
    });

    $('[date-range-picker]').on('apply.daterangepicker', function(ev, picker) {
        $("input[name='startTime']").val(picker.startDate.format('YYYY-MM-DD'));
        $("input[name='endTime']").val(picker.endDate.format('YYYY-MM-DD'));
    });

    //重置reset方法
    $("button[type='reset']").click(function(){
        $(':input').attr("value",'');
        $('select').val(null).trigger("change");
        search();
    });
})

//数组位置
function indexInArray(arr,value){
    for(var i = 0; i < arr.length; i++){
        if(value === arr[i]){
            return i;
        }
    }
    return -1;
}

//是否在数组中
function isInArray(arr,value){
    for(var i = 0; i < arr.length; i++){
        if(value === arr[i]){
            return true;
        }
    }
    return false;
}