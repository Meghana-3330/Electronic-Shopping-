package com.ellison.dao;

import com.ellison.model.Order;
import com.ellison.model.Transaction;
import com.ellison.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public boolean addOrder(Order order) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO orders (orderid, prodid, username, quantity, amount, shipped, paymentMethod) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, order.getOrderId());
            ps.setInt(2, order.getProdId());
            ps.setString(3, order.getUsername());
            ps.setInt(4, order.getQuantity());
            ps.setDouble(5, order.getAmount());
            ps.setBoolean(6, order.isShipped());
            ps.setString(7, order.getPaymentMethod());
            if(ps.executeUpdate() > 0) status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean addTransaction(Transaction txn) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO transactions (txnid, username, time, amount, paymentMethod) VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, txn.getTxnId());
            ps.setString(2, txn.getUsername());
            ps.setTimestamp(3, txn.getTime());
            ps.setDouble(4, txn.getAmount());
            ps.setString(5, txn.getPaymentMethod());
            if(ps.executeUpdate() > 0) status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<Order> getOrdersByUser(String username) {
        List<Order> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT o.orderid, o.prodid, o.username, o.quantity, o.amount, o.shipped, o.paymentMethod, p.pname, p.image " +
                "FROM orders o JOIN product p ON o.prodid = p.pid WHERE o.username=? ORDER BY o.orderid DESC")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getString("orderid"));
                o.setProdId(rs.getInt("prodid"));
                o.setUsername(rs.getString("username"));
                o.setQuantity(rs.getInt("quantity"));
                o.setAmount(rs.getDouble("amount"));
                o.setShipped(rs.getBoolean("shipped"));
                o.setPaymentMethod(rs.getString("paymentMethod"));
                o.setPname(rs.getString("pname"));
                o.setImage(rs.getString("image"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT o.orderid, o.prodid, o.username, o.quantity, o.amount, o.shipped, o.paymentMethod, p.pname, p.image " +
                "FROM orders o JOIN product p ON o.prodid = p.pid ORDER BY o.orderid DESC")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getString("orderid"));
                o.setProdId(rs.getInt("prodid"));
                o.setUsername(rs.getString("username"));
                o.setQuantity(rs.getInt("quantity"));
                o.setAmount(rs.getDouble("amount"));
                o.setShipped(rs.getBoolean("shipped"));
                o.setPaymentMethod(rs.getString("paymentMethod"));
                o.setPname(rs.getString("pname"));
                o.setImage(rs.getString("image"));
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateShippedStatus(String orderId, int prodId) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE orders SET shipped=true WHERE orderid=? AND prodid=?")) {
            ps.setString(1, orderId);
            ps.setInt(2, prodId);
            if(ps.executeUpdate() > 0) status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
