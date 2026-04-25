package com.ellison.servlet;

import com.ellison.dao.CartDAO;
import com.ellison.model.Cart;
import com.ellison.model.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private CartDAO cartDAO = new CartDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        String username = user.getEmail();

        if ("add".equals(action)) {
            int prodid = Integer.parseInt(request.getParameter("prodid"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Cart cart = new Cart();
            cart.setUsername(username);
            cart.setProdid(prodid);
            cart.setQuantity(quantity);
            cartDAO.addToCart(cart);
            response.sendRedirect("CartServlet?action=view");
        } else if ("update".equals(action)) {
            int prodid = Integer.parseInt(request.getParameter("prodid"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cartDAO.updateQuantity(username, prodid, quantity);
            response.sendRedirect("CartServlet?action=view");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        String username = user.getEmail();

        if ("view".equals(action) || action == null) {
            List<Cart> cartItems = cartDAO.getCartItems(username);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else if ("remove".equals(action)) {
            int prodid = Integer.parseInt(request.getParameter("prodid"));
            cartDAO.removeProduct(username, prodid);
            response.sendRedirect("CartServlet?action=view");
        }
    }
}
