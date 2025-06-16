<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/list.css" />
</head>
<body>
    <div class="userlist-container">
        <h1>User List</h1>
        <div class="user-stats">
            <p>Total Users: <strong>${totalUserCnt}</strong></p>
            <p>New Users Today: <strong>${signUpToday}</strong></p>
        </div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td><a href="/user_update?id=${user.id}">${user.id}</a></td>
                        <td>${user.name}</td>
                        <td>${user.address}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="button-area">
            <a href="/" class="btn-home">Back to Home</a>
        </div>
    </div>
</body>
</html>
