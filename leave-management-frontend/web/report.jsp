<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.LeaveRequest" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>
<%@ page import="model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<LeaveRequest> requests = (List<LeaveRequest>) request.getAttribute("requests");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Báo cáo nghỉ phép</title>
    <style>
        table{
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
        .filter-form {
            margin-bottom: 20px;
        }
        .filter-form label {
            margin-right: 10px;
        }
        .filter-form input[type="date"] {
            margin-right: 10px;
        }
        .filter-form button {
            padding: 10px 15px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .filter-form button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<h2>Báo cáo nghỉ phép</h2>
<form method="post" action="report">
    Từ ngày:
    <input type="date" name="fromDate" value="<%= request.getAttribute("fromDate") != null ? request.getAttribute("fromDate") : "" %>" required>
    Đến ngày:
    <input type="date" name="toDate" value="<%= request.getAttribute("toDate") != null ? request.getAttribute("toDate") : "" %>" required>
    <button type="submit">Xem báo cáo</button>
</form>

<%
    if (requests != null && !requests.isEmpty()) {
%>
<table>
    <tr>
        <th>Mã đơn</th>
        <th>UserID</th>
        <th>Từ ngày</th>
        <th>Đến ngày</th>
        <th>Lý do</th>
        <th>Trạng thái</th>
    </tr>
    <%
        for (LeaveRequest lr : requests) {
    %>
    <tr>
        <td><%= lr.getRequestID() %></td>
        <td><%= lr.getUserID() %></td>
        <td><%= lr.getStartDate() %></td>
        <td><%= lr.getEndDate() %></td>
        <td><%= lr.getReason() %></td>
        <td class="<%= lr.getStatus().equals("Approved") ? "approved" : lr.getStatus().equals("Rejected") ? "rejected" : "inprogress" %>">
            <%= lr.getStatus() %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<% } else if(request.getAttribute("fromDate") != null) { %>
<p>Không có dữ liệu.</p>
<% } %>

<p><a href="home.jsp">Về trang chính</a></p>
</body>
</html>
