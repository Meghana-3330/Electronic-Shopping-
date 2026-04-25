package com.ellison.servlet;

import com.ellison.dao.OrderDAO;
import com.ellison.dao.ProductDAO;
import com.ellison.model.Order;
import com.ellison.model.Product;
import com.ellison.model.User;
import com.ellison.util.EmailUtil;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !"admin@ellison.com".equals(user.getEmail())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        
        if ("addProduct".equals(action)) {
            Product p = new Product();
            p.setPname(request.getParameter("pname"));
            p.setType(request.getParameter("type"));
            p.setPrice(Double.parseDouble(request.getParameter("price")));
            p.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            p.setImage(request.getParameter("image")); // Normally you'd upload, but keeping simple for this scope
            productDAO.addProduct(p);
            response.sendRedirect("AdminServlet?action=dashboard");
        } else if ("updateProduct".equals(action)) {
            Product p = new Product();
            p.setPid(Integer.parseInt(request.getParameter("pid")));
            p.setPname(request.getParameter("pname"));
            p.setType(request.getParameter("type"));
            p.setPrice(Double.parseDouble(request.getParameter("price")));
            p.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            productDAO.updateProduct(p);
            response.sendRedirect("AdminServlet?action=dashboard");
        } else if ("markShipped".equals(action)) {
            String orderId = request.getParameter("orderId");
            int prodId = Integer.parseInt(request.getParameter("prodId"));
            String username = request.getParameter("username"); // to notify user
            
            orderDAO.updateShippedStatus(orderId, prodId);
            EmailUtil.sendEmail(username, "Order Shipped", "Your order " + orderId + " has been shipped.");
            response.sendRedirect("AdminServlet?action=orders");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !"admin@ellison.com".equals(user.getEmail())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("dashboard".equals(action)) {
            List<Product> products = productDAO.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else if ("orders".equals(action)) {
            List<Order> orders = orderDAO.getAllOrders();
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("admin_orders.jsp").forward(request, response);
        } else if ("deleteProduct".equals(action)) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            productDAO.deleteProduct(pid);
            response.sendRedirect("AdminServlet?action=dashboard");
        }
    }
}
