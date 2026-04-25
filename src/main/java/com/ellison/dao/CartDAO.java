package com.ellison.dao;

import com.ellison.model.Cart;
import com.ellison.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    public boolean addToCart(Cart cart) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection()) {
            // Check if already in cart
            PreparedStatement check = con.prepareStatement("SELECT * FROM usercart WHERE username=? AND prodid=?");
            check.setString(1, cart.getUsername());
            check.setInt(2, cart.getProdid());
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                // Update quantity
                int existingQty = rs.getInt("quantity");
                PreparedStatement update = con.prepareStatement("UPDATE usercart SET quantity=? WHERE username=? AND prodid=?");
                update.setInt(1, existingQty + cart.getQuantity());
                update.setString(2, cart.getUsername());
                update.setInt(3, cart.getProdid());
                if(update.executeUpdate() > 0) status = true;
            } else {
                // Insert new
                PreparedStatement insert = con.prepareStatement("INSERT INTO usercart (username, prodid, quantity) VALUES (?, ?, ?)");
                insert.setString(1, cart.getUsername());
                insert.setInt(2, cart.getProdid());
                insert.setInt(3, cart.getQuantity());
                if(insert.executeUpdate() > 0) status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public List<Cart> getCartItems(String username) {
        List<Cart> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "SELECT c.username, c.prodid, c.quantity, p.pname, p.price, p.image " +
                "FROM usercart c JOIN product p ON c.prodid = p.pid WHERE c.username=?")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cart c = new Cart();
                c.setUsername(rs.getString("username"));
                c.setProdid(rs.getInt("prodid"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPname(rs.getString("pname"));
                c.setPrice(rs.getDouble("price"));
                c.setImage(rs.getString("image"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateQuantity(String username, int prodid, int quantity) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE usercart SET quantity=? WHERE username=? AND prodid=?")) {
            ps.setInt(1, quantity);
            ps.setString(2, username);
            ps.setInt(3, prodid);
            if(ps.executeUpdate() > 0) status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean removeProduct(String username, int prodid) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM usercart WHERE username=? AND prodid=?")) {
            ps.setString(1, username);
            ps.setInt(2, prodid);
            if(ps.executeUpdate() > 0) status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public boolean clearCart(String username) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM usercart WHERE username=?")) {
            ps.setString(1, username);
            if(ps.executeUpdate() > 0) status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
