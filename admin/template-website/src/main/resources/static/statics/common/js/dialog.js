/**
 * Created by Administrator on 2019/7/22.
 */
//弹窗
function dialog(_this, callback){
    var type = $(_this).attr("data-type");
    var title = $(_this).attr("data-title");
    var href = $(_this).attr("data-href");
    if("show" == type){
        layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            offset: '100px',
            shade: 0.3,
            area: ['800px', '600px'],
            content: href,
        });
    }
    if("form" == type){
        layer.open({
            type: 2,
            title: title,
            shadeClose: false,
            offset: '100px',
            shade: 0.3,
            area: ['800px', '600px'],
            content: href,
            btn: ['保存', '关闭'],
            yes: function(index, layero){
                var iframeWin = window.frames.parent[layero.find('iframe')[0]['name']]
                iframeWin.submitForm();
            },
            btn2: function(index, layero){}
        });
    }
    if("confirm" == type){
        layer.confirm(title,{
            offset: '200px',
            btn: ['确认','取消']
        }, function(){
            //loading
            var index = layer.load(1, {
                shade: [0.3,'#fff'], //0.1透明度的白色背景
            });
            $.ajax({
                url: href,
                type: "post",
                success: function(result){
                    layer.close(index);
                    layer.msg(result.msg);
                    if(callback){
                        callback();
                    }
                }
            })
        }, function(){});
    }
}

//列表弹窗
function tableDialog(_this){
    var type = $(_this).attr("data-type");
    var title = $(_this).attr("data-title");
    var href = $(_this).attr("data-href");
    var width = $(_this).attr("data-width");
    var height = $(_this).attr("data-height");
    if("form" == type){
        if(width){
            width = width + 'px';
        }else{
            width = '800px';
        }
        if(height){
            height = height + 'px';
        }else{
            height = '600px';
        }
        window.parent.layer.open({
            type: 2,
            title: title,
            shadeClose: false,
            offset: '100px',
            shade: 0.3,
            area: [width, height],
            content: href,
            btn: ['保存', '关闭'],
            yes: function(index, layero){
                var iframeWin = window.frames.parent[layero.find('iframe')[0]['name']]
                iframeWin.submitForm();
            },
            btn2: function(index, layero){}
        });
    }
    if("show" == type){
        if(width){
            width = width + 'px';
        }else{
            width = '800px';
        }
        if(height){
            height = height + 'px';
        }else{
            height = '600px';
        }
        window.parent.layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            offset: '100px',
            shade: 0.3,
            area: [width, height],
            content: href,
        });
    }
    if("confirm" == type){
        window.parent.layer.confirm(title,{
            offset: '200px',
            btn: ['确认','取消']
        }, function(){
            //loading
            var index = window.parent.layer.load(1, {
                shade: [0.3,'#fff'], //0.1透明度的白色背景
            });
            $.ajax({
                url: href,
                type: "post",
                success: function(result){
                    window.parent.layer.close(index);
                    window.parent.layer.msg(result.msg);
                    console.log(result)
                    if(result.code == "SUCCESS"){
                        window.parent.layer.closeAll();
                        window.frames.parent['iframepage'].contentWindow.search();
                    }
                }
            })
        }, function(){});
    }
}