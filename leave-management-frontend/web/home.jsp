<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ page import="model.Role" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Role> roles = (List<Role>) session.getAttribute("roles");

    boolean isManager = false;
    boolean isAdmin = false;

    if (roles != null) {
        for (Role r : roles) {
            if ("Team Lead".equalsIgnoreCase(r.getRoleName()) ||
                "Division Leader".equalsIgnoreCase(r.getRoleName())) {
                isManager = true;
            }
            if ("Division Leader".equalsIgnoreCase(r.getRoleName())) {
                isAdmin = true;
            }
        }
    }
%>
<!DOCTYPE html>
<html>
   <head>
    <title>Trang Chủ</title>
    <style>
       body {
                font-family: Arial;
            }
            .menu {
                margin-top: 20px;
            }
            .menu a {
                display: inline-block;
                margin-right: 15px;
                padding: 8px 12px;
                background: #007BFF;
                color: white;
                text-decoration: none;
                border-radius: 4px;
            }
            .menu a:hover {
                background: #0056b3;
            }
    </style>
</head>
    <body>
        <h2>Xin chào, <%= user.getFirstName() %> <%= user.getLastName() %></h2>
        <p>Email: <%= user.getEmail() %></p>

        <div class="menu">
            <!-- Mọi user đều có quyền -->
            <a href="create-request">Tạo đơn nghỉ phép</a>
            <a href="my-requests">Xem đơn của tôi</a>

            <% if (isManager) { %>
            <a href="subordinate-requests">Xem & Duyệt đơn cấp dưới</a>
            <a href="report">Báo cáo nghỉ phép</a>
            <% } %>



            <a href="logout.jsp">Đăng xuất</a>
        </div>
    </body>
</html>
