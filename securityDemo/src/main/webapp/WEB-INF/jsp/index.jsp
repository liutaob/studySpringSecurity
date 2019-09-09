<%--
  Created by IntelliJ IDEA.
  User: 大春
  Date: 2019/7/30
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>首页</title>
</head>
<body>
<h1 align="center">欢迎商品管理系统</h1>
<c:choose>
    <c:when test="${pageContext.request.userPrincipal.name != null}">
        <label>
            Hi ${pageContext.request.userPrincipal.name} ! 你好，您的身份:${pageContext.request.userPrincipal.authorities}
        </label>
        <form action="/logout" method="post">
            <input type="submit" value="注销">
        </form>
    </c:when>
    <c:otherwise>
        <h2 align="center">游客您好，如果想管理商品 <a href="/login">请登录</a></h2>
    </c:otherwise>
</c:choose>
    <a href="${pageContext.request.contextPath}/product/add">商品添加</a>
    <a href="${pageContext.request.contextPath}/product/update">商品修改</a>
    <a href="${pageContext.request.contextPath}/product/list">商品列表</a>
    <a href="${pageContext.request.contextPath}/product/delete">商品删除</a>
</body>
</html>
