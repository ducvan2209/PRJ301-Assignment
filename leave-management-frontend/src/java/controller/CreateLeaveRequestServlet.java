/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.LeaveRequestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.LeaveRequest;
import model.User;

/**
 *
 * @author Acer
 */
@WebServlet(name = "CreateLeaveRequestServlet", urlPatterns = {"/create-request"})
public class CreateLeaveRequestServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateLeaveRequestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateLeaveRequestServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        dao.UserDAO userDAO = new dao.UserDAO();
        // Lấy danh sách vai trò
        java.util.List<model.Role> roles = userDAO.getRolesByUserID(user.getUserID());

        // Lấy thông tin phòng ban
        model.Department department = userDAO.getDepartmentByID(user.getDepartmentID());

        request.setAttribute("roles", roles);
        request.setAttribute("department", department);

        request.getRequestDispatcher("createRequest.jsp").forward(request, response);
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

        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String reason = request.getParameter("reason");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlStartDate = null;
        java.sql.Date sqlEndDate = null;
        try {
            java.util.Date utilStart = sdf.parse(startDateStr);
            java.util.Date utilEnd = sdf.parse(endDateStr);
            sqlStartDate = new java.sql.Date(utilStart.getTime());
            sqlEndDate = new java.sql.Date(utilEnd.getTime());
        } catch (ParseException e) {
            throw new ServletException("Ngày không hợp lệ", e);
        }

        LeaveRequest lr = new LeaveRequest();
        lr.setUserID(user.getUserID());
        lr.setStartDate(sqlStartDate);
        lr.setEndDate(sqlEndDate);
        lr.setReason(reason);

        LeaveRequestDAO dao = new LeaveRequestDAO();
        dao.createLeaveRequest(lr);

        // Mô phỏng gửi email cho quản lý
//        System.out.println("========= Thông báo Email =========");
//        System.out.println("Gửi email cho quản lý:");
//        System.out.println("Chủ đề: Đơn nghỉ phép mới");
//        System.out.println("Nội dung:");
//        System.out.println("Nhân viên " + user.getFirstName() + " " + user.getLastName() +
//                " vừa tạo đơn nghỉ phép từ " + startDateStr + " đến " + endDateStr +
//                ". Lý do: " + reason);
//        System.out.println("=====================================");

        response.sendRedirect("my-requests");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
