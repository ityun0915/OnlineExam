<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<div class="container-fluid" style="background-color:skyblue">
    <div class="col-md-4" style="padding-top:20px"></div>
    <div class="col-md-4" style="padding-top:15px ;text-align: center"><h4><strong>线上考试</strong></h4></div>
    <div class="col-md-4" style="padding-top:20px">
        <ol class="list-inline">
            <c:if test="${empty loginUser}">
                <li><a href="login.jsp">登录</a></li>
                <li><a href="regist.jsp">注册</a></li>
            </c:if>
            <c:if test="${not empty loginUser}">
                <li>欢迎您:<a href="person_information.jsp">${loginUser.nickname}</a></li>
                <li><a href="${pageContext.request.contextPath}/userLogout">退出</a></li>
                <li><a href="${pageContext.request.contextPath}/CartServlet?method=cartUI">错题集</a></li>
                <li><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrdersWithPage&num=1">我的试卷</a></li>
            </c:if>
        </ol>
    </div>
</div>
</body>
</html>
