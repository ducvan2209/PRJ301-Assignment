package dao;

import dal.DBContext;
import model.LeaveRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDAO extends DBContext {

    /**
     * Tạo mới đơn nghỉ phép
     */
    public void createLeaveRequest(LeaveRequest lr) {
        String sql = "{call CreateLeaveRequest(?, ?, ?, ?)}";
        try (Connection conn = getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, lr.getUserID());
            stmt.setDate(2, lr.getStartDate());
            stmt.setDate(3, lr.getEndDate());
            stmt.setString(4, lr.getReason());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lấy danh sách đơn nghỉ phép của một user
     */
    public List<LeaveRequest> getLeaveRequestsByUser(int userID) {
        List<LeaveRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM LeaveRequests WHERE UserID = ? ORDER BY StartDate DESC";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LeaveRequest lr = new LeaveRequest(
                            rs.getInt("RequestID"),
                            rs.getInt("UserID"),
                            rs.getDate("StartDate"),
                            rs.getDate("EndDate"),
                            rs.getString("Reason"),
                            rs.getString("Status"),
                            rs.getObject("ApproverID") != null ? rs.getInt("ApproverID") : null
                    );
                    list.add(lr);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Cập nhật trạng thái đơn nghỉ phép
     */
    public void updateLeaveRequestStatus(int requestID, String status, int approverID) {
        String sql = "UPDATE LeaveRequests SET Status = ?, ApproverID = ? WHERE RequestID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, approverID);
            stmt.setInt(3, requestID);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
 * Lấy tất cả đơn nghỉ phép của cấp dưới quản lý
 */
public List<LeaveRequest> getLeaveRequestsOfSubordinates(int managerID) {
    List<LeaveRequest> list = new ArrayList<>();
    String sql = "SELECT lr.* FROM LeaveRequests lr " +
                 "JOIN Users u ON lr.UserID = u.UserID " +
                 "WHERE u.ManagerID = ? ORDER BY lr.StartDate DESC";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, managerID);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LeaveRequest lr = new LeaveRequest(
                        rs.getInt("RequestID"),
                        rs.getInt("UserID"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getString("Reason"),
                        rs.getString("Status"),
                        rs.getObject("ApproverID") != null ? rs.getInt("ApproverID") : null
                );
                list.add(lr);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

public List<LeaveRequest> getLeaveRequestsByDateRange(Date from, Date to) {
    List<LeaveRequest> list = new ArrayList<>();
    String sql = "SELECT * FROM LeaveRequests " +
                 "WHERE StartDate <= ? AND EndDate >= ? " +
                 "ORDER BY StartDate DESC";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDate(1, to);
        stmt.setDate(2, from);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LeaveRequest lr = new LeaveRequest(
                        rs.getInt("RequestID"),
                        rs.getInt("UserID"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getString("Reason"),
                        rs.getString("Status"),
                        rs.getObject("ApproverID") != null ? rs.getInt("ApproverID") : null
                );
                list.add(lr);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}



}
