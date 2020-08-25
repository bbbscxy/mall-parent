<!DOCTYPE html>
<html>
<head>
    <title>组织机构</title>
    <#include "/include/header.ftl">
    <style>
        .layout-border{
            border-left: 1px solid #eee;
        }
        .cacheTable{
            table-layout: fixed;
        }
        .cacheTable tr{
            cursor: pointer;
        }
        .cacheTable tr td {
            overflow:hidden;
            white-space:nowrap;
            text-overflow:ellipsis;
            -o-text-overflow:ellipsis;
            -moz-text-overflow: ellipsis;
            -webkit-text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<div class="ui-layout-west">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title">
                    <i class="fa fa-map-o"></i>缓存集合
                </div>
            </div>
            <div class="box-body">
                <table id="cacheNameDatatable" class="table table-hover table-bordered cacheTable">
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="ui-layout-center layout-border">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title">
                    <i class="fa fa-map-o"></i>缓存key
                </div>
            </div>
            <div class="box-body">
                <table id="cacheKeyDatatable" class="table table-hover table-bordered cacheTable">
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="ui-layout-east layout-border">
    <div class="main-content">
        <div class="box box-main">
            <div class="box-header">
                <div class="box-title">
                    <i class="fa fa-map-o"></i>缓存value
                </div>
            </div>
            <div class="box-body">
                <pre id="cacheValue" style="padding-left: 20px;"></pre>
            </div>
        </div>
    </div>
</div>
</body>
<#include "/include/footer.ftl">
<#include "/include/list.ftl">
<script>
    $('body').layout({
        west__initClosed: false,
        west__size: 200,
        east__size: 600,
    });

    /**
     * 渲染cacheKeyList
     */
    function initCacheKeyList(cacheName){
        $.ajax({
            url: '${ctx}/sysCache/cacheKeyList?cacheName='+cacheName,
            type: "GET",
            success: function(cacheKeyList){
                var trs = "";
                for(var i in cacheKeyList){
                    if(i == 0){
                        trs += '<tr class="active" onclick="selectCacheKey(this,\''+cacheName+'\',\''+cacheKeyList[i]+'\')"><td>'+cacheKeyList[i]+'</td></tr>';
                    }else{
                        trs += '<tr onclick="selectCacheKey(this,\''+cacheName+'\',\''+cacheKeyList[i]+'\')"><td>'+cacheKeyList[i]+'</td></tr>';
                    }
                }
                $("#cacheKeyDatatable tbody").html(trs);
                if(cacheKeyList.length > 0){
                    initCacheValue(cacheName, cacheKeyList[0]);
                }
            }
        })
    }

    function selectCacheName(_this, cacheName) {
        $("#cacheKeyDatatable tbody").html("");
        $("#cacheValue").html("");
        $(_this).addClass("active").siblings().removeClass("active");
        initCacheKeyList(cacheName);
    }
    
    function selectCacheKey(_this, cacheName, cacheKey) {
        $("#cacheValue").html("");
        $(_this).addClass("active").siblings().removeClass("active");
        initCacheValue(cacheName, cacheKey);
    }

    /**
     * 渲染cacheValue
     */
    function initCacheValue(cacheName, cacheKey){
        $.ajax({
            url: '${ctx}/sysCache/cacheValue?cacheName='+cacheName+"&cacheKey="+cacheKey,
            type: "GET",
            success: function(value){
                $('#cacheValue').jsonViewer(value, {});
            }
        })
    }
    
    $(function () {
        $("#cacheValue").height(tableHeight());

        $.ajax({
            url: '${ctx}/sysCache/cacheNameList',
            type: "GET",
            success: function(cacheNameList){
                var trs = "";
                for(var i in cacheNameList){
                    if(cacheNameList[i].indexOf("authorizationCache") == -1){
                        if(i == 0){
                            trs += '<tr class="active" onclick="selectCacheName(this,\''+cacheNameList[i]+'\')"><td>'+cacheNameList[i]+'</td></tr>';
                        }else{
                            trs += '<tr onclick="selectCacheName(this,\''+cacheNameList[i]+'\')"><td>'+cacheNameList[i]+'</td></tr>';
                        }
                    }
                }
                $("#cacheNameDatatable tbody").html(trs);
                if(cacheNameList.length > 0){
                    initCacheKeyList(cacheNameList[0]);
                }
            }
        })
    })
</script>