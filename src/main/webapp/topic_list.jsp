<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>在线题库</title>
</head>
<body>
<%@include file="header.jsp"%>

    <div style="padding-left:60px "><h3>题型分类:</h3><br></div>
    <span id="span01" ></span>

</body>
</html>
<script>
    function selCates() {
        $.ajax({
            type : "post",//数据请求方式
            url : "${pageContext.request.contextPath}/selCates",
            contentType : "application/json;charset=utf-8",
            data : "",
            datatype : "json",//数据返回类型
            success : function (data) {
                /*var list = eval('(' + data + ')');//json解析成 对象
                alert(list);*/

                $.each(data,function (i,obj) {
                    // alert(obj.cname);
                    var li = "<div style='padding-left:70px '><h4><li><a href='${pageContext.request.contextPath}/selTopicByCid?cid="+obj.cid+"'>"+obj.cname+"</a></li></h4></div>";
                    $("#span01").append(li);
                })
            }

        })
    }
    selCates();
</script>
