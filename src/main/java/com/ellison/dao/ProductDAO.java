package com.ellison.dao;

import com.ellison.model.Product;
import com.ellison.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM product")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("pid"), rs.getString("pname"), rs.getString("type"),
                        rs.getDouble("price"), rs.getInt("quantity"), rs.getString("image")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> products = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE type=?")) {
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("pid"), rs.getString("pname"), rs.getString("type"),
                        rs.getDouble("price"), rs.getInt("quantity"), rs.getString("image")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> searchProducts(String query) {
        List<Product> products = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE pname LIKE ? OR type LIKE ?")) {
            ps.setString(1, "%" + query + "%");
            ps.setString(2, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("pid"), rs.getString("pname"), rs.getString("type"),
                        rs.getDouble("price"), rs.getInt("quantity"), rs.getString("image")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    public boolean addProduct(Product p) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO product(pname, type, price, quantity, image) VALUES(?,?,?,?,?)")) {
            ps.setString(1, p.getPname());
            ps.setString(2, p.getType());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setString(5, p.getImage());
            int rs = ps.executeUpdate();
            if(rs > 0) status = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public boolean updateProduct(Product p) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE product SET pname=?, type=?, price=?, quantity=? WHERE pid=?")) {
            ps.setString(1, p.getPname());
            ps.setString(2, p.getType());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setInt(5, p.getPid());
            int rs = ps.executeUpdate();
            if(rs > 0) status = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public boolean deleteProduct(int pid) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM product WHERE pid=?")) {
            ps.setInt(1, pid);
            int rs = ps.executeUpdate();
            if(rs > 0) status = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public Product getProductById(int pid) {
        Product p = null;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE pid=?")) {
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                p = new Product(rs.getInt("pid"), rs.getString("pname"), rs.getString("type"),
                        rs.getDouble("price"), rs.getInt("quantity"), rs.getString("image"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
