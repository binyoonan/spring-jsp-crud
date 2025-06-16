<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>프로젝트</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/index.css" />
</head>
<body>
    <%
        String userType = (String) session.getAttribute("user_type");
        boolean isLogin = session.getAttribute("is_login") != null
            && (Boolean) session.getAttribute("is_login");
        boolean isAdmin = "admin".equals(userType);
    %>
    <div class="welcome-container">
        <h1 class="welcome-title">Welcome to the Project</h1>
        <p class="welcome-message">
            Sign up or log in to explore our services.<br>
            Enjoy access to your personal dashboard and, if you are an admin, manage user lists.
        </p>
        <div class="nav-container">
            <% if (!isLogin) { %>
                <a href="join">Sign Up</a>
                <a href="login">Log In</a>
            <% } else { %>
                <a href="mypage">My Page</a>
                <a href="logout">Log Out</a>
                <% if (isAdmin) { %>
                    <a href="user_list">User List</a>
                <% } %>
            <% } %>
        </div>
    </div>
</body>
</html>
