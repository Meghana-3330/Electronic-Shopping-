package com.ellison.dao;

import com.ellison.model.User;
import com.ellison.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    
    public boolean registerUser(User user) {
        boolean status = false;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(
                "INSERT INTO user (email, name, mobile, address, pincode, password) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getName());
            ps.setString(3, user.getMobile());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPincode());
            ps.setString(6, user.getPassword());
            
            int rs = ps.executeUpdate();
            if(rs > 0) status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public User loginUser(String email, String password) {
        User user = null;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM user WHERE email=? AND password=?")) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("email"), rs.getString("name"), rs.getString("mobile"), 
                                rs.getString("address"), rs.getString("pincode"), rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
