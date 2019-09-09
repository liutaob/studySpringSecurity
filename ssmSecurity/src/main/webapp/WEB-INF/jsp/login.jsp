<%--
  Created by IntelliJ IDEA.
  User: 大春
  Date: 2019/8/4
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<body>
<h1>登录页面</h1>

<b color="red" id="errorShow" style="display: none">用户名或密码错误</b>

<form id="loginForm" method="post">
    username:<input type="text" name="username"><br>
    password:<input type="password" name="password"><br>
    <span style="display: inline">
        验证码：
    <input type="text" name="imageCode" placeholder="验证码" />
    <img src="${pageContext.request.contextPath}/code/image" onclick="this.src='${pageContext.request.contextPath}/code/image?'+Math.random()"/>

</span><br>
    记住我:<input type="checkbox" name="remember-me"><br>
    <input type="button" value="login" id="loginBtn">
</form>
<script>
    $(function () {
        $("#loginBtn").click(function () {
            $.post("${pageContext.request.contextPath}/userLogin", $("#loginForm").serialize(), function (data) {
                debugger;
                if (data.success) {
                    <%--window.location.href = "${pageContext.request.contextPath}"//首页--%>
                    window.location.href = "/"//首页
                } else {
                    // 登录不成功将错误块显示
                    $("#errorShow").css("display","block");
                }
            }, "json");
        });

    });
</script>
</body>
</html>
