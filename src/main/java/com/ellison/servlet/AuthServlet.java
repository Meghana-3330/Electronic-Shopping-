package com.ellison.servlet;

import com.ellison.dao.UserDAO;
import com.ellison.model.User;
import com.ellison.util.EmailUtil;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("register".equals(action)) {
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setMobile(request.getParameter("mobile"));
            user.setAddress(request.getParameter("address"));
            user.setPincode(request.getParameter("pincode"));
            user.setPassword(request.getParameter("password")); // In real app, hash password!
            
            if (userDAO.registerUser(user)) {
                EmailUtil.sendEmail(user.getEmail(), "Welcome to Ellison Electronics", "Hi " + user.getName() + ",\n\nYour registration is successful!");
                response.sendRedirect("login.jsp?msg=registered");
            } else {
                response.sendRedirect("register.jsp?msg=error");
            }
        } else if ("login".equals(action)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            User user = userDAO.loginUser(email, password);
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                if(email.equals("admin@ellison.com")) {
                    response.sendRedirect("AdminServlet?action=dashboard");
                } else {
                    response.sendRedirect("ProductServlet");
                }
            } else {
                response.sendRedirect("login.jsp?msg=invalid");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("login.jsp?msg=loggedout");
        }
    }
}
