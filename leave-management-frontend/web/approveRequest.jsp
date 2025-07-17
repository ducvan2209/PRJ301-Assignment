<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String requestID = (String) request.getAttribute("requestID");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Xét duyệt đơn nghỉ phép</title>
</head>
<body>
<h2>Xét duyệt đơn nghỉ phép</h2>
<form action="approve-request" method="post">
    <input type="hidden" name="requestID" value="<%= requestID %>">
    <label>Trạng thái:</label><br>
    <select name="status">
        <option value="Approved">Chấp thuận</option>
        <option value="Rejected">Từ chối</option>
    </select><br><br>
    <button type="submit">Xác nhận</button>
</form>
<p><a href="subordinate-requests">Quay lại danh sách</a></p>
</body>
</html>
