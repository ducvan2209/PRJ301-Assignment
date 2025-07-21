<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.LeaveRequest" %>
<%@ page import="model.Role" %>
<%@ page session="true" %>
<%@ page import="model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<LeaveRequest> requests = (List<LeaveRequest>) request.getAttribute("requests");
    List<Role> roles = (List<Role>) request.getAttribute("roles");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách đơn nghỉ phép của cấp dưới</title>
    <style>
        body { font-family: Arial; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background: #eee; }
        a.button {
            display: inline-block;
            padding: 4px 8px;
            background: #007BFF;
            color: white;
            text-decoration: none;
            border-radius: 3px;
        }
        a.button:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
<h2>Danh sách đơn nghỉ phép của cấp dưới</h2>
<table>
    <tr>
        <th>Mã đơn</th>
        <th>Mã nhân viên</th>
        <th>Từ ngày</th>
        <th>Đến ngày</th>
        <th>Lý do</th>
        <th>Trạng thái</th>
        <th>Hành động</th>
    </tr>
    <%
        if (requests != null && !requests.isEmpty()) {
            for (LeaveRequest lr : requests) {
    %>
    <tr>
        <td><%= lr.getRequestID() %></td>
        <td><%= lr.getUserID() %></td>
        <td><%= lr.getStartDate() %></td>
        <td><%= lr.getEndDate() %></td>
        <td><%= lr.getReason() %></td>
        <td><%= lr.getStatus() %></td>
        <td>
            <%
                // Kiểm tra vai trò quản lý
                boolean isManager = false;
                if (roles != null) {
                    for (Role r : roles) {
                        if ("Team Lead".equalsIgnoreCase(r.getRoleName()) ||
                            "Division Leader".equalsIgnoreCase(r.getRoleName())) {
                            isManager = true;
                            break;
                        }
                    }
                }
                if ("InProgress".equals(lr.getStatus()) && isManager) {
            %>
                <a class="button" href="approve-request?requestID=<%= lr.getRequestID() %>">Xét duyệt</a>
            <%
                } else if (!"InProgress".equals(lr.getStatus())) {
            %>
                Đã duyệt
            <%
                } else {
            %>
                -
            <%
                }
            %>
        </td>
   </tr>

    <%
            }
        } else {
    %>
    <tr><td colspan="7">Không có đơn nào.</td></tr>
    <%
        }
    %>
</table>
<p><a href="home.jsp">Về trang chính</a></p>
</body>
</html>
