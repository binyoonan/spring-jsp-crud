<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>회원가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/insert.css" />
</head>
<body>
    <div class="signup-container">
        <h1>Sign Up</h1>
        <p class="welcome-message">
            Please fill out the form below to create your account.
        </p>
        <form action="join_action" method="post">
            <input type="text" name="name" placeholder="Name" />
            <input type="text" name="id" placeholder="ID" />
            <input type="password" name="pwd" placeholder="Password" />
            <input type="password" name="pwd2" placeholder="Confirm Password" />
            <input type="text" name="phone" placeholder="Phone Number" />
            <input type="text" name="address" placeholder="Address" />
            <button type="submit">Submit</button>
			<br>
			<a href="/" class="btn-home">Back to Home</a>
        </form>
    </div>
</body>
</html>