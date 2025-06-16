<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>LOGIN</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/login.css" />
</head>
<body>
    <div class="login-container">
        <h1>Login</h1>
        <form action="login_action" method="post">
            <input type="text" name="id" placeholder="ID" />
            <input type="password" name="pwd" placeholder="Password" />
            <button type="submit">Sign In</button>
        </form>
    </div>
</body>
</html>
