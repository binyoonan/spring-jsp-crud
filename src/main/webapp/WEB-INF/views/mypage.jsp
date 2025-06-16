<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/mypage.css" />
</head>
<body>
    <div class="profile-container">
        <h1>My Page</h1>
        <table class="profile-info">
            <tr>
                <th>Field</th>
                <th>Information</th>
            </tr>
            <tr>
                <td>ID</td>
                <td>${user.id}</td>
            </tr>
            <tr>
                <td>Name</td>
                <td>${user.name}</td>
            </tr>
            <tr>
                <td>Phone</td>
                <td>${user.phone}</td>
            </tr>
            <tr>
                <td>Address</td>
                <td>${user.address}</td>
            </tr>
            <tr>
                <td>User Type</td>
                <td>${user.userType}</td>
            </tr>
            <tr>
                <td>Join Date</td>
                <td>${user.created}</td>
            </tr>
            <tr>
                <td>Last Updated</td>
                <td>${user.lastUpdated}</td>
            </tr>
        </table>
        <div class="profile-actions">
            <a href="/user_update?id=${user.id}" class="btn-home">Update Profile</a>
            <a href="/" class="btn-home">Back to Home</a>
        </div>
    </div>
</body>
</html>
