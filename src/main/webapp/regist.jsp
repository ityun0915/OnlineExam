﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
    body{
        margin-top:20px;
        margin:0 auto;
    }
    .carousel-inner .item img{
        width:100%;
        height:300px;
    }
    .container .row div{
        /* position:relative;
        float:left; */
    }

    font {
        color: #3164af;
        font-size: 18px;
        font-weight: normal;
        padding: 0 10px;
    }
</style>
</head>

<script type="text/javascript">

    function ajaxCheck() {
        var username = document.getElementById("username").value;
        var span = document.getElementById("span01");

        $.ajax({
            type : "post",
            url : "${pageContext.request.contextPath}/selUser",//提交地址
            data : "username="+username,//提交的数据
            dataType : "json",//返回json格式数据
            success : function (data) {
                if (data.code != 100 ){
                    span.innerHTML = "<div style='font-size: large ; color: blue' >用户名可用!</div>";

                }else{
                    span.innerHTML = "<div style='font-size: large ; color: red' >用户名已存在!</div>";

                }
            }
        })


    }

</script>

<body>

<%@include file="header.jsp"%>



<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/img/regist_bg.jpg');">
    <div class="row">

        <div class="col-md-2"></div>




        <div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
            <font>用户注册</font>USER REGISTER
            <form class="form-horizontal" style="margin-top:5px;" action="userRegist" method="post">
                <div class="form-group">
                    <label for="username" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-6">
                        <input type="text" name="username" onkeyup="ajaxCheck()" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <span id="span01"></span>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-6">
                        <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-6">
                        <input type="email" name="email" class="form-control" id="inputEmail3" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">昵称</label>
                    <div class="col-sm-6">
                        <input type="text" name="nickname" class="form-control" id="usercaption" placeholder="请输入姓名">
                    </div>
                </div>



                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <input type="submit"  width="100" value="注册" name="submit" border="0"
                               style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                       height:35px;width:100px;color:white;">
                    </div>
                </div>
            </form>
        </div>

        <div class="col-md-2"></div>

    </div>
</div>




</body></html>




