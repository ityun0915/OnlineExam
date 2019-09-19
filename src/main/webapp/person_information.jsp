<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人信息</title>
</head>
<%@include file="header.jsp"%>

<style type="text/css">
    table.hovertable {
        font-family: verdana,arial,sans-serif;
        font-size:11px;
        color:#333333;
        border-width: 1px;
        border-color: #999999;
        border-collapse: collapse;
    }
    table.hovertable th {
        background-color:#c3dde0;
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
    table.hovertable tr {
        background-color:#d4e3e5;
    }
    table.hovertable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #a9c6c9;
    }
</style>
<body>
<div style="width: 100%;height: 100%">
        <!-- Table goes in the document BODY -->
        <strong>
            <table class="hovertable" style="width: 550px ;height: 300px ;margin: 50px auto;">
            <tr>
                <td colspan="2"><div style="text-align: center">考生信息</div></td>
            </tr>
            <tr οnmοuseοver="this.style.backgroundColor='#ffff66';" οnmοuseοut="this.style.backgroundColor='#d4e3e5';">
                <td>考生号</td>
                <td>${sessionScope.loginUser.uid}</td>
            </tr>
            <tr οnmοuseοver="this.style.backgroundColor='#ffff66';" οnmοuseοut="this.style.backgroundColor='#d4e3e5';">
                <td>用户名</td>
                <td>${sessionScope.loginUser.username}</td>
            </tr>
            <tr οnmοuseοver="this.style.backgroundColor='#ffff66';" οnmοuseοut="this.style.backgroundColor='#d4e3e5';">
                <td>姓名</td>
                <td>${sessionScope.loginUser.nickname}</td>
            </tr>
            <tr οnmοuseοver="this.style.backgroundColor='#ffff66';" οnmοuseοut="this.style.backgroundColor='#d4e3e5';">
                <td>email</td>
                <td>${sessionScope.loginUser.email}</td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><a href="topic_list.jsp">查看题库</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="paper_create.jsp">手动生成考试</a></td>

            </tr>
            </table>
        </strong>
</div>
</body>
</html>
