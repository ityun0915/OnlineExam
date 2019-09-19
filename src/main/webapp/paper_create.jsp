<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>手动生成试卷</title>
</head>


<body>

<%@include file="header.jsp"%>

    <form action="${pageContext.request.contextPath}/createPaper" method="get" style="font-size: 20px">

        <div id="span01"></div>
        <div id="div1">
            <input type="submit" value="开始组卷">
        </div>

    </form>

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
                // $("#div1").append($("#span01"));
                $.each(data,function (i,obj) {
                    // alert(obj.cname);
                    var li =obj.cname+" : <input class='input1' type='number' name='"+obj.cid+"' placeholder='请输入该题型数量'style='font-size: small'/><br>";
                    $("#span01").append(li);

                })

            }

        })
    }
    selCates();
</script>