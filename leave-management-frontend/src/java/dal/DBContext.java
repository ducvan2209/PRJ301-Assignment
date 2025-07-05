package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {

    private final String serverName = "localhost";
    private final String dbName = "LeaveRequestSystem"; // Thay thế bằng tên DB của bạn
    private final String portNumber = "1433";
    private final String userID = "sa"; // Thay thế bằng user của bạn
    private final String password = "12345"; // Thay thế bằng pass của bạn

    // Phương thức này sẽ trả về một kết nối mới mỗi khi được gọi
    public Connection getConnection() throws SQLException {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(url, userID, password);
            System.out.println("Kết nối cơ sở dữ liệu thành công!"); // Có thể bỏ sau khi debug
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Lỗi: Không tìm thấy Driver JDBC. " + ex.getMessage());
            throw new SQLException("Lỗi cấu hình Driver JDBC.", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage());
            throw ex; // Ném lại lỗi để phương thức gọi có thể xử lý
        }
    }
    
        // Main method để kiểm tra kết nối (có thể giữ lại hoặc bỏ)
    public static void main(String[] args) {
        DBContext db = new DBContext();
        try (Connection con = db.getConnection()) {
            if (con != null) {
                System.out.println("Kiểm tra: Kết nối được tạo thành công.");
            } else {
                System.out.println("Kiểm tra: Kết nối không được tạo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
