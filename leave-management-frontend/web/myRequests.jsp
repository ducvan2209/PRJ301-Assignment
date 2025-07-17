<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.LeaveRequest" %>
<%@ page session="true" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách nghỉ phép của tôi</title>
    
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
        .buttons {
            margin-bottom: 20px;
        }
        .buttons a {
            margin-right: 10px;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .buttons a:hover {
            background-color: #0056b3;
        }
    </style>
  
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        List<LeaveRequest> requests = (List<LeaveRequest>) request.getAttribute("requests");
    %>

    <h1>Danh sách nghỉ phép của tôi</h1>
    
    <div class="buttons">
        <a href="home.jsp">Quay lại trang chính</a> <!-- Giả sử trang chính là home.jsp, thay đổi nếu cần -->
        <a href="my-requests">Tạo đơn mới</a> <!-- Giả sử trang tạo đơn là createLeave.jsp, thay đổi nếu cần -->
    </div>
    
    <% if (requests != null && !requests.isEmpty()) { %>
        <table>
            <thead>
                <tr>
                    <th>Ngày bắt đầu</th>
                    <th>Ngày kết thúc</th>
                    <th>Lý do</th>
                    <th>Trạng thái</th>
                </tr>
            </thead>
            <tbody>
                <% for (LeaveRequest req : requests) { %>
                    <tr>
                        <td><%= req.getStartDate() %></td> <!-- Giả sử getStartDate() trả về Date hoặc String -->
                        <td><%= req.getEndDate() %></td> <!-- Tương tự -->
                        <td><%= req.getReason() %></td>
                        <td><%= req.getStatus() %></td>
                    </tr>
             <% } %>
            </tbody>
        </table>
  <% } else { %>
        <p>Không có yêu cầu nghỉ phép nào.</p>
    <% } %>
</body>
</html>