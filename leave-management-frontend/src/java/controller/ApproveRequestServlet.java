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
import java.util.List;
import model.Role;
import model.User;

/**
 *
 * @author Acer
 */
@WebServlet(name="ApproveRequestServlet", urlPatterns={"/approve-request"})
public class ApproveRequestServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ApproveRequestServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApproveRequestServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("requestID");
        request.setAttribute("requestID", id);
        request.getRequestDispatcher("approveRequest.jsp").forward(request, response);
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
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền duyệt đơn.");
            return;
        }

        int requestID = Integer.parseInt(request.getParameter("requestID"));
        String status = request.getParameter("status");

        LeaveRequestDAO dao = new LeaveRequestDAO();
        dao.updateLeaveRequestStatus(requestID, status, user.getUserID());

//        // Mô phỏng gửi email cho nhân viên
//        System.out.println("========= Thông báo Email =========");
//        System.out.println("Gửi email cho nhân viên:");
//        System.out.println("Chủ đề: Kết quả xét duyệt đơn nghỉ phép");
//        System.out.println("Nội dung:");
//        System.out.println("Đơn nghỉ phép #" + requestID + " của bạn đã được " + status + ".");
//        System.out.println("=====================================");
//
        response.sendRedirect("subordinate-requests");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
