<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/updateForm.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <h1 style="text-align:center;">Update Profile</h1>
    <form id="updateForm" class="form-container">
        <input type="hidden" name="id" value="${user.id}">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${user.name}" required>
        </div>
        <div class="form-group">
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="${user.phone}" required>
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="${user.address}" required>
        </div>
        <div class="form-group">
            <label for="userType">User Type:</label>
            <select id="userType" name="userType">
                <option value="guest" ${user.userType eq 'guest' ? 'selected' : ''}>Guest</option>
                <option value="admin" ${user.userType eq 'admin' ? 'selected' : ''}>Admin</option>
            </select>
        </div>
        <div class="button-area">
            <button type="button" id="saveBtn">Save</button>
            <button type="button" onclick="window.history.back()">Cancel</button>
        </div>
        <div id="result-message"></div>
    </form>

    <script>
        $(document).ready(function() {
            $("#saveBtn").click(function() {
                var formData = {
                    id: $("input[name='id']").val(),
                    name: $("input[name='name']").val(),
                    phone: $("input[name='phone']").val(),
                    address: $("input[name='address']").val(),
                    userType: $("select[name='userType']").val()
                };
                $.ajax({
                    url: "/update_action",
                    type: "POST",
                    data: JSON.stringify(formData),
                    contentType: "application/json; charset=utf-8",
                    success: function(response) {
                        $("#result-message").text("Profile updated successfully.");
                        location.href = "/mypage";
                    },
                    error: function(xhr, status, error) {
                        $("#result-message").text("Error occurred while updating profile: " + error);
                    }
                });
            });
        });
    </script>
</body>
</html>
