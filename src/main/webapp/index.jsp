<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
    <script src="js/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        alert("aaaa");
        function test() {
            alert("aaaa");
        };

        var clik = function () {
            alert("dada");
        };

        function readJson() {
            alert("readJson");
            $.ajax({
                url: "data.json",//json文件位置，文件名
                type: "GET",//请求方式为get
                dataType: "json", //返回数据格式为json
                success: function(data) {//请求成功完成后要执行的方法
                    //给info赋值给定义好的变量
                    var pageData=data;
                    alert(data);
                }
            });
        };
    </script>
</head>
<body>
--------=============-----------------=================---------</br>
<a href="#" onclick="test()">我要弹筐筐</a>
<button onclick="clik()">点我</button>
<button onclick="readJson()">readJson</button>
</body>
</html>