<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CHESS ONLINE - LOG IN</title>
</head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/CSS/styleSignup.css'/>" />
<body>
    <div id="content">
        <h1 id="contentLogin">CHESS ONLINE</h1>
        <div id="boxLogin">
            <form id="formBoxLogin" action="${pageContext.request.contextPath}/signup" method="post">
                <input  class="inputInfo" type="text" placeholder="User name" name="username" id="">
                <input type="email" class="inputInfo" placeholder="Email" name="email">
                <input class="inputInfo" type="password" placeholder="Password" name="password" id="">
                <input type="password" class="inputInfo" placeholder="Confirm Password">
                <p style="color: red; font-size: 15px; margin-left: 140px;">${error}</p>
                <button id="login">Sign up</button>
                <hr>
                <a style="margin-top: 10px; color: #3cbbea;" href="<c:url value='/login'/>">Login - and star playing chess!</a>
            </form>
        </div>
    </div>
</body>
</html>