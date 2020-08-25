<!DOCTYPE html>
<html>
<head>
    <title>预览生成代码</title>
    <#include "/include/header.ftl">
    <style>
        .nav-tabs{
            position: fixed;
            background: #fff;
            width: 100%;
        }
        .nav-tabs li{
            width: 14%;
        }
        xmp{
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="main-content">
        <div class="box box-main" style="padding: 0px;">
            <div class="box-body" style="padding: 0px;">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#sql" data-toggle="tab">sql</a></li>
                    <li><a href="#model" data-toggle="tab">model</a></li>
                    <li><a href="#dao" data-toggle="tab">dao</a></li>
                    <li><a href="#service" data-toggle="tab">service</a></li>
                    <li><a href="#controller" data-toggle="tab">controller</a></li>
                    <li><a href="#list" data-toggle="tab">list</a></li>
                    <li><a href="#form" data-toggle="tab">form</a></li>
                </ul>

                <div class="tab-content" style="margin-top: 50px;padding: 0px 10px">
                    <div class="tab-pane active" id="sql">
                        <xmp>${data['sql']}</xmp>
                    </div>
                    <div class="tab-pane" id="model">
                        <pre>${data['model']}</pre>
                    </div>
                    <div class="tab-pane" id="dao">
                        <pre>${data['dao']}</pre>
                    </div>
                    <div class="tab-pane" id="service">
                        <pre>${data['service']}</pre>
                    </div>
                    <div class="tab-pane" id="controller">
                        <pre>${data['controller']}</pre>
                    </div>
                    <div class="tab-pane" id="list">
                        <xmp>${data['list']}</xmp>
                    </div>
                    <div class="tab-pane" id="form">
                        <xmp>${data['form']}</xmp>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<#include "/include/footer.ftl">
</html>