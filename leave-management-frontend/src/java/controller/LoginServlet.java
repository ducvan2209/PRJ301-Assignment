package controller;

import dao.UserDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.Role;

@WebServlet(name="LoginServlet", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mặc định load trang login
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String empCode = request.getParameter("employeeCode");
        String password = request.getParameter("password");

        // Nếu có hash password thì hash tại đây
        String passwordHash = password;

        UserDAO dao = new UserDAO();
        User user = dao.authenticateUser(empCode, passwordHash);

        if (user != null) {
            // Đăng nhập thành công
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // LẤY DANH SÁCH ROLE CỦA USER
            List<Role> roles = dao.getRolesByUserID(user.getUserID());
            session.setAttribute("roles", roles);

            // Chuyển đến trang chính
            response.sendRedirect("home.jsp");
        } else {
            // Đăng nhập thất bại
            request.setAttribute("error", "Sai thông tin đăng nhập!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
