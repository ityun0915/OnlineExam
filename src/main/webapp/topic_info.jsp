<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>试题详情</title>
</head>
<body>
<%@include file="header.jsp"%>


<c:forEach items="${list}" var="topic" varStatus="status">
    <div style="margin-left:140px ">
        <h3><td>${ status.index + 1}</td>.
                ${topic.topic}</h3><br>
    </div>
    <div style="margin-left:150px ">
        <div ><input type="radio" name="option">${topic.option1}</div>&nbsp;
        <div><input type="radio" name="option">${topic.option2}</div><br>
        <div><input type="radio" name="option">${topic.option3}</div>&nbsp;
        <div><input type="radio" name="option">${topic.option4}</div>&nbsp;
    </div>
</c:forEach>


</body>
</html>
