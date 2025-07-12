<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
    <style>
        body { font-family: Arial; background: #f0f0f0; }
        .login-container {
            width: 300px;
            margin: 80px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 6px rgba(0,0,0,0.2);
        }
        input { width: 100%; margin: 8px 0; padding: 8px; }
        button { width: 100%; padding: 8px; }
        .error { color: red; }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Đăng nhập hệ thống</h2>
    <form action="login" method="post">
        <label for="employeeCode">Mã nhân viên:</label>
        <input type="text" name="employeeCode" id="employeeCode" required />

        <label for="password">Mật khẩu:</label>
        <input type="password" name="password" id="password" required />

        <button type="submit">Đăng nhập</button>
    </form>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="error"><%= error %></div>
    <% } %>
</div>
</body>
</html>
