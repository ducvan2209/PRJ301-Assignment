<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="model.User" %>
<%@ page import="model.Role" %>
<%@ page import="model.Department" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Role> roles = (List<Role>) request.getAttribute("roles");
    Department dep = (Department) request.getAttribute("department");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Đơn xin nghỉ phép</title>
    <style>
        body { font-family: Arial; }
        .form-box {
            background: #fde4d1;
            padding: 20px;
            width: 300px;
            margin: 50px auto;
            border: 1px solid #aaa;
            border-radius: 5px;
        }
        input, textarea { width: 100%; margin-top: 5px; }
        button {
            margin-top: 10px;
            padding: 6px 12px;
            background: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
        }
        button:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
<div class="form-box">
    <h3>Đơn xin nghỉ phép</h3>
    <p>
        User: <b><%= user.getFirstName() %> <%= user.getLastName() %></b><br>
        Role:
        <%
            for (int i=0; i<roles.size(); i++) {
                out.print(roles.get(i).getRoleName());
                if (i < roles.size()-1) out.print(", ");
            }
        %><br>
        Dep: phòng <%= dep != null ? dep.getDepartmentName() : "" %>
    </p>
    <form action="create-request" method="post">
        <label>Từ ngày:</label>
        <input type="date" name="startDate" required><br>
        <label>Tới ngày:</label>
        <input type="date" name="endDate" required><br>
        <label>Lý do:</label>
        <textarea name="reason" rows="3"></textarea><br>
        <button type="submit">Gửi</button>
    </form>
</div>
</body>
</html>
