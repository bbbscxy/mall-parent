<!DOCTYPE html>
<html>
<head>
  <title>通用项目模板</title>
  <#include "include/header.ftl">
  <style>
      .slimScrollDiv{
          height: 170px !important;
      }
      .topNav{
          color:#fff;
          float: left;
          padding: 15px;
      }
      .rightNav{
          color:#fff;
          float: right;
          padding: 15px;
      }
      .topNav:hover, .topNav:focus, .topNav.active{
          color: #fff;
          background: #367fa9;
      }
  </style>
  <script>
      function changeFrameHeight(){
          var ifm= document.getElementById("iframepage");
          ifm.height=document.documentElement.clientHeight-55;
      }
      //注销
      function logoutCallback() {
          window.location.href = "${ctx}/";
      }
  </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <header class="main-header">
    <a href="${ctx}/" class="logo">
      <span class="logo-mini">通用项目框架</span>
      <span class="logo-lg">通用项目框架</span>
    </a>

    <nav class="navbar navbar-static-top">
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <#list topMenuList as menu>
          <a href="javascript:void(0)" data-id="${menu.id}" class="topNav">${menu.name}</a>
      </#list>
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <li>
              <span class="rightNav">
                  当前在线人数：&nbsp;<span id="onlineCount"></span>
              </span>
          </li>
          <li>
            <a href="javascript:void(0);" onclick="dialog(this)" data-title="消息通知" data-type="show" data-href="${ctx}/sysUserMsg/list">
                <i class="fa fa-bell-o"></i>
                <span class="label label-warning">
                    <span class="msgCount">${msgCount}</span>
                </span>
            </a>
          </li>
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${ctxStatic}/common/img/user.jpg" class="user-image"><span class="hidden-xs">${currentUser().name}</span>
            </a>
            <ul class="dropdown-menu" style="width: 160px;">
                <li style="padding-top: 10px;"><a href="javascript:void(0)" onclick="dialog(this)" data-title="更换头像" data-type="form" data-href="${ctx}/sysUser/updateAvatarForm"><i class="fa fa-user-o"></i>更换头像</a></li>
                <li class="divider"></li>
                <li><a href="javascript:void(0)" onclick="dialog(this)" data-title="修改密码" data-type="form" data-href="${ctx}/sysUser/updatePasswordForm"><i class="fa fa-key"></i>修改密码</a></li>
                <li class="divider"></li>
                <li style="padding-bottom: 10px;"><a href="javascript:void(0)" onclick="dialog(this, logoutCallback);" data-title="确认退出当前用户？" data-type="confirm" data-href="${ctx}/logout"><i class="fa fa-sign-out"></i>注销</a></li>
            </ul>
          </li>
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <aside class="main-sidebar">
    <section class="sidebar">
      <div class="user-panel">
        <div class="pull-left image">
          <img src="${ctxStatic}/common/img/user.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${currentUser().loginName}</p>
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
        </div>
      </div>
      <ul class="sidebar-menu">
          <#if leftMenuList??>
          <#list leftMenuList as menu>
          <#if menu.isShow == "1">

          <#if menu_index = 0>
          <li class="active treeview">
          <#else>
          <li class="treeview">
          </#if>
              <a href="#">
              <#if menu.icon??>
                  <i class="fa ${menu.icon}"></i>
              <#else>
                  <i class="fa fa-desktop"></i>
              </#if>
                  <span>${menu.name}</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                  <#if menu.childMenuList??>
                  <#list menu.childMenuList as childMenu>
                      <#if childMenu.isShow == '1'>
                      <li>
                          <a href="${ctx}${childMenu.href}" target="right">
                          <#if childMenu.icon??>
                              <i class="fa ${childMenu.icon!}"></i>
                          <#else>
                              <i class="fa fa-square-o"></i>
                          </#if>
                          ${childMenu.name}
                          </a>
                      </li>
                      </#if>
                  </#list>
                  </#if>
              </ul>
          </li>
          </#if>
          </#list>
          </#if>
      </ul>
    </section>
  </aside>

  <div class="content-wrapper">
      <iframe src="${ctx}/desktop" id="iframepage" name="right" frameBorder=0 scrolling="auto" width="100%" onLoad="changeFrameHeight()"></iframe>
  </div>

  <aside class="control-sidebar control-sidebar-dark">
	<div class="skin-top"></div>
	<div id="switchSkin"></div>
  </aside>
  <div class="control-sidebar-bg"></div>
</div>
</body>
<#include "include/footer.ftl">
<script type="text/javascript" src="${ctxStatic}/plugins/websocket/socket.js"></script>
<script>
    window.onresize=function(){
        changeFrameHeight();
    }

    $(function () {
        //头部菜单事件
        $(".topNav").click(function () {
            $.cookie('topId', $(this).attr("data-id"), -1);
            window.location.href = '${ctx}/';
        })

        //左侧菜单事件
        $(".treeview-menu li").click(function () {
            $(this).addClass("active");
            $(this).siblings().removeClass("active");
        })

        //获取选中菜单
        var topId = $.cookie('topId');
        $(".topNav").each(function () {
            if($(this).attr("data-id") == topId){
                $(this).addClass("active");
            }else{
                $(this).removeClass("active");
            }
        })

        var IframeOnClick = {
            resolution: 200,
            iframes: [],
            interval: null,
            Iframe: function() {
                this.element = arguments[0];
                this.cb = arguments[1];
                this.hasTracked = false;
            },
            track: function(element, cb) {
                this.iframes.push(new this.Iframe(element, cb));
                if (!this.interval) {
                    var _this = this;
                    this.interval = setInterval(function() { _this.checkClick(); }, this.resolution);
                }
            },
            checkClick: function() {
                if (document.activeElement) {
                    var activeElement = document.activeElement;
                    for (var i in this.iframes) {
                        if (activeElement === this.iframes[i].element) { // user is in this Iframe
                            if (this.iframes[i].hasTracked == false) {
                                this.iframes[i].cb.apply(window, []);
                                this.iframes[i].hasTracked = true;
                            }
                        } else {
                            this.iframes[i].hasTracked = false;
                        }
                    }
                }
            }
        };

        //iframe点击事件
        IframeOnClick.track(document.getElementById("iframepage"), function() {
            $(".dropdown").removeClass('open');
            $("aside").removeClass('control-sidebar-open');
        });
    })
</script>
<script>
    var userId = '${currentUser().id}';
    if("1" != userId){
        var websocket = null;
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://"+window.location.host+"/websocket/"+userId);
        }
        websocket.onopen = onOpen;
        websocket.onmessage = onMessage;
        websocket.onerror = onError;
        websocket.onclose = onClose;
    }

    function onMessage(event) {
        var result = JSON.parse(event.data);
        if(result.type == "ONLINE_COUNT"){
            $("#onlineCount").html(result.data);
        }
        if(result.type == "NOTIFY"){
            $(".msgCount").html(parseInt($(".msgCount").html())+1);
        }
    }
    function onOpen(event) {}
    function onError() {}
    function onClose() {}
</script>
</html>
