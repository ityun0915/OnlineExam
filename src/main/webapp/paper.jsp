<%@ page import="com.yizhidu.pojo.TestPaper" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>在线考试</title>
</head>

<body>
<%@include file="header.jsp"%>



<form action="checkTopic" method="post" id="form1">
    <div>
        <h3>在线考试试题如下(共${testPaper.count}题): &nbsp;&nbsp;
            <input id="input1" type="submit" value="提交试卷"/> &nbsp;&nbsp;
            <span style="color: red" id="showTimes"></span>
        </h3>
        <font color="blue"> (注意:答题时间根据题目数计算,1题1分钟)</font>


    </div>

    <%--遍历map的list--%>

    <c:forEach items="${testPaper.map}" var="map">
        <%--cname :--%>
        <div style="margin-left:240px "><h3>(${map.key}题)</h3> </div>
<%--
        ${map.value}
--%>
            <%--遍历list的topics--%>
            <c:forEach items="${map.value}" var="topic" varStatus="status">
                <div style="margin-left:140px ">
                    <h3><td>${ status.index + 1}</td>.
                            ${topic.topic}</h3><br>
                </div>
                <div style="margin-left:150px ">
                    <div><input type="radio" name="${map.key}#${ status.index + 1}" value="${topic.option1}">${topic.option1}</div>&nbsp;
                    <div><input type="radio" name="${map.key}#${ status.index + 1}" value="${topic.option2}">${topic.option2}</div><br>
                    <div><input type="radio" name="${map.key}#${ status.index + 1}" value="${topic.option3}">${topic.option3}</div>&nbsp;
                    <div><input type="radio" name="${map.key}#${ status.index + 1}" value="${topic.option4}">${topic.option4}</div>&nbsp;
                    <%--隐藏域表单传输答案--%>
                    <div ><input type="hidden" name="question" value="${topic.question}"></div>
                    <%--<div ><input type="hidden" name="tid" value="${topic.tid}"></div>--%>

                </div>
            </c:forEach>
    </c:forEach>

</form>
<%
    //获取总题目数
    TestPaper testPaper = (TestPaper) request.getAttribute("testPaper");
    int count = testPaper.getCount();

    long time=count*60*1000l;
%>
</body>
</html>
<script>
    var second = <%= time / 1000%>; // 剩余秒数
    // 写一个方法，将秒数专为天数
    var toDays = function(){
        var s = second % 60; // 秒
        var mi = (second - s) / 60 % 60; // 分钟
        /*var h = ((second - s) / 60 - mi ) / 60 % 24; // 小时
        var d = (((second - s) / 60 - mi ) / 60 - h ) / 24 // 天*/
        return "剩余答题时间："/* + d + "天" + h + "小时"*/ + mi + "分钟" + s + "秒";
    }
    //然后写一个定时器
    window.setInterval(function(){
        second --;
        document.getElementById("showTimes").innerHTML = toDays ();
        if (second == 0){
            alert("作答时间到,正在为您改卷...");
            $("#form1").submit();
        }
    }, 1000);
</script>