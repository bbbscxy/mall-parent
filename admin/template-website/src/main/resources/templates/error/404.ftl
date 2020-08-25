<!DOCTYPE html>
<html>
<head>
    <title>404</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <style>
        *{ padding:0;margin:0 }
        a{ text-decoration:none }
        .notfoud-container .img-404{
            height:155px;
            background:url(${ctxStatic}/common/img/404.png) center center no-repeat;
            -webkit-background-size:150px auto;
            margin-top:40px;
            margin-bottom:20px
        }
        .notfoud-container .notfound-p{
            line-height:22px;
            font-size:17px;
            padding-bottom:15px;
            border-bottom:1px solid #f6f6f6;
            text-align:center;color:#262b31
        }
        .notfoud-container .notfound-reason{
            color:#9ca4ac;font-size:13px;line-height:13px;
            text-align:left;width:210px;margin:0 auto
        }
        .notfoud-container .notfound-reason p{
            margin-top:13px
        }
        .notfoud-container .notfound-reason ul li{
            margin-top:10px;margin-left:36px
        }
    </style>
</head>
<body>

<div class="notfoud-container">
    <div class="img-404">
    </div>
    <p class="notfound-p">哎呀迷路了...</p>
    <div class="notfound-reason">
        <p>可能的原因：</p>
        <ul>
            <li>原来的页面不存在了</li>
            <li>我们的服务器被外星人劫持了</li>
        </ul>
    </div>
</div>
</body>
</html>