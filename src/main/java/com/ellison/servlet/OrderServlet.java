package com.ellison.servlet;

import com.ellison.dao.CartDAO;
import com.ellison.dao.OrderDAO;
import com.ellison.model.Cart;
import com.ellison.model.Order;
import com.ellison.model.Transaction;
import com.ellison.model.User;
import com.ellison.util.EmailUtil;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO = new OrderDAO();
    private CartDAO cartDAO = new CartDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("checkout".equals(action)) {
            String address = request.getParameter("address"); // Or use user's address
            List<Cart> cartItems = cartDAO.getCartItems(user.getEmail());
            
            if (cartItems.isEmpty()) {
                response.sendRedirect("CartServlet?action=view");
                return;
            }

            String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            String txnId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            double totalAmount = 0;

            for (Cart item : cartItems) {
                Order order = new Order();
                order.setOrderId(orderId);
                order.setProdId(item.getProdid());
                order.setUsername(user.getEmail());
                order.setQuantity(item.getQuantity());
                double amount = item.getQuantity() * item.getPrice();
                order.setAmount(amount);
                order.setShipped(false);
                orderDAO.addOrder(order);
                totalAmount += amount;
            }

            Transaction txn = new Transaction();
            txn.setTxnId(txnId);
            txn.setUsername(user.getEmail());
            txn.setTime(new Timestamp(System.currentTimeMillis()));
            txn.setAmount(totalAmount);
            orderDAO.addTransaction(txn);

            cartDAO.clearCart(user.getEmail());

            EmailUtil.sendEmail(user.getEmail(), "Order Placed Successfully", 
                "Hi " + user.getName() + ",\n\nYour order " + orderId + " has been placed successfully. Total amount: ₹" + totalAmount);

            response.sendRedirect("OrderServlet?action=history&msg=success");
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
        if ("history".equals(action)) {
            List<Order> orders = orderDAO.getOrdersByUser(user.getEmail());
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("orders.jsp").forward(request, response);
        } else if ("checkout_page".equals(action)) {
            List<Cart> cartItems = cartDAO.getCartItems(user.getEmail());
            double total = 0;
            for(Cart c : cartItems) total += (c.getPrice() * c.getQuantity());
            request.setAttribute("total", total);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }
    }
}
