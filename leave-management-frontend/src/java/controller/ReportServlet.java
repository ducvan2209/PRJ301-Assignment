package controller;

import dao.LeaveRequestDAO;
import model.LeaveRequest;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Role;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Kiểm tra vai trò
        List<Role> roles = (List<Role>) session.getAttribute("roles");
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
        if (!isManager) {
        
            return;
        }

        // Mặc định chưa có dữ liệu
        request.getRequestDispatcher("report.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Kiểm tra vai trò
        List<Role> roles = (List<Role>) session.getAttribute("roles");
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
        if (!isManager) {
            
            return;
        }

        String fromDateStr = request.getParameter("fromDate");
        String toDateStr = request.getParameter("toDate");

        java.sql.Date fromDate = null;
        java.sql.Date toDate = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilFrom = sdf.parse(fromDateStr);
            java.util.Date utilTo = sdf.parse(toDateStr);
            fromDate = new java.sql.Date(utilFrom.getTime());
            toDate = new java.sql.Date(utilTo.getTime());
        } catch (ParseException e) {
            request.setAttribute("error", "Định dạng ngày không hợp lệ.");
        }

        LeaveRequestDAO dao = new LeaveRequestDAO();
        List<LeaveRequest> list = dao.getLeaveRequestsByDateRange(fromDate, toDate);

        request.setAttribute("requests", list);
        request.setAttribute("fromDate", fromDateStr);
        request.setAttribute("toDate", toDateStr);
        request.getRequestDispatcher("report.jsp").forward(request, response);
    }
}
