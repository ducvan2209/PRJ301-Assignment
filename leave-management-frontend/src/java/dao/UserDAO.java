package dao;

import dal.DBContext;
import model.User;
import model.Role;
import model.Feature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Department;

public class UserDAO extends DBContext {

    /**
     * Chức năng 1: Xác thực đăng nhập
     *
     * @param employeeCode Mã nhân viên
     * @param passwordHash Mật khẩu hash
     * @return User nếu thành công, null nếu thất bại
     */
    public User authenticateUser(String employeeCode, String passwordHash) {
        String sql = "{call AuthenticateUser(?, ?)}";
        try (Connection conn = getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, employeeCode);
            stmt.setString(2, passwordHash);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setUserID(rs.getInt("UserID"));
                    u.setFirstName(rs.getString("FirstName"));
                    u.setLastName(rs.getString("LastName"));
                    u.setEmail(rs.getString("Email"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lấy user đầy đủ thông tin theo UserID
     */
    public User getUserByID(int userID) { // get all 
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("UserID"),
                            rs.getString("EmployeeCode"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getInt("DepartmentID"),
                            rs.getObject("ManagerID") != null ? rs.getInt("ManagerID") : null,
                            rs.getString("Email"),
                            rs.getString("PasswordHash")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Chức năng 2: Lấy danh sách Role của user
     */
    public List<Role> getRolesByUserID(int userID) {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT r.RoleID, r.RoleName "
                + "FROM UserRoles ur JOIN Roles r ON ur.RoleID = r.RoleID "
                + "WHERE ur.UserID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Role role = new Role(
                            rs.getInt("RoleID"),
                            rs.getString("RoleName")
                    );
                    roles.add(role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    /**
     * Chức năng 2: Lấy tất cả Feature mà user được cấp quyền (thông qua vai
     * trò)
     */
    public List<Feature> getFeaturesByUserID(int userID) {
        List<Feature> features = new ArrayList<>();
        String sql = "SELECT DISTINCT f.FeatureID, f.FeatureName "
                + "FROM UserRoles ur "
                + "JOIN RoleFeatures rf ON ur.RoleID = rf.RoleID "
                + "JOIN Features f ON rf.FeatureID = f.FeatureID "
                + "WHERE ur.UserID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Feature f = new Feature(
                            rs.getInt("FeatureID"),
                            rs.getString("FeatureName")
                    );
                    features.add(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return features;
    }

    public Department getDepartmentByID(int departmentID) {
        String sql = "SELECT * FROM Departments WHERE DepartmentID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, departmentID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Department(
                            rs.getInt("DepartmentID"),
                            rs.getString("DepartmentName")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lấy tất cả Features mà user có quyền
     */
    public List<String> getFeatureNamesByUserID(int userID) {
        List<String> features = new ArrayList<>();
        String sql = "SELECT DISTINCT f.FeatureName "
                + "FROM UserRoles ur "
                + "JOIN RoleFeatures rf ON ur.RoleID = rf.RoleID "
                + "JOIN Features f ON rf.FeatureID = f.FeatureID "
                + "WHERE ur.UserID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    features.add(rs.getString("FeatureName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return features;
    }

}

  
       

//        public int insert(User user) {
//            Connection conn = null;
//            PreparedStatement stm = null;
//            int result = -1;
//
//            String sql = "INSERT INTO [dbo].[Users] "
//                    + "([EmployeeCode], [FirstName], [LastName], [DepartmentID], [ManagerID], [Email], [PasswordHash]) "
//                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
//
//            try {
//                conn = getConnection(); // Initialize connection
//                if (conn == null) {
//                    throw new SQLException("Failed to establish database connection");
//                }
//
//                stm = conn.prepareStatement(sql); // Initialize PreparedStatement
//                stm.setString(1, user.getEmployeeCode());
//                stm.setString(2, user.getFirstName());
//                stm.setString(3, user.getLastName());
//                stm.setInt(4, user.getDepartmentID());
//                stm.setInt(5, user.getManagerID());
//                stm.setString(6, user.getEmail());
//                stm.setString(7, user.getPasswordHash());
//
//                result = stm.executeUpdate();
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                // Close resources
//                try {
//                    if (stm != null) {
//                        stm.close();
//                    }
//                    if (conn != null) {
//                        conn.close();
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            return result;
//        }
//        public int update(User user) {
//        Connection conn = null;
//        PreparedStatement stm = null;
//        int result = -1;
//        String sql = "UPDATE [dbo].[Users] " +
//                     "SET [EmployeeCode] = ?, " +
//                     "[FirstName] = ?, " +
//                     "[LastName] = ?, " +
//                     "[DepartmentID] = ?, " +
//                     "[ManagerID] = ?, " +
//                     "[Email] = ?, " +
//                     "[PasswordHash] = ? " +
//                     "WHERE UerID = ?";
//
//        try {
//            conn = getConnection(); // Initialize connection
//            if (conn == null) {
//                throw new SQLException("Failed to establish database connection");
//            }
//
//            stm = conn.prepareStatement(sql); // Initialize PreparedStatement
//            stm.setString(1, user.getEmployeeCode());
//            stm.setString(2, user.getFirstName());
//            stm.setString(3, user.getLastName());
//            stm.setInt(4, user.getDepartmentID());
//            stm.setInt(5, user.getManagerID());
//            stm.setString(6, user.getEmail());
//            stm.setString(7, user.getPasswordHash()); // Fixed typo: getPassworkHash -> getPasswordHash
//            stm.setInt(8, user.getId()); // Set the ID parameter for WHERE clause
//
//            result = stm.executeUpdate();
//        } catch (SQLException ex) {
//            System.err.println("SQL Error: " + ex.getMessage() + ", Error Code: " + ex.getErrorCode());
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            // Close resources
//            try {
//                if (stm != null) stm.close();
//                if (conn != null) conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return result;
//    }
//
//    public static void main(String[] args) { // UPDATE VALUES IN TABLE
//        UserDAO userDAO = new UserDAO();
//
//        User user = new User(8, "ABC02", "duc", "van", 2, 0, 0, "duc", "duc");
//        int result = userDAO.update(user);
//
//        if (result == -1 || result == 0) {
//            System.out.println("UPDATE failed");
//        } else {
//            System.out.println("UPDATE successful");
//        }
//    }
//}
//        

//        public static void main(String[] args) { // INSERT NEW VALUES INTO TABLE
//            UserDAO userDAO = new UserDAO();
//
//            User user = new User(2, "ABC03", "Sai", "Linh", 2, 3, "Linh2908", "123");
//            int result = userDAO.insert(user);
//
//            if (result == -1 || result == 0) {
//                System.out.println("Insert failed");
//            } else {
//                System.out.println("Insert successful");
//            }
//        }
//        
    


