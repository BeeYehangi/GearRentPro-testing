package com.GearRentPro.test;

import com.GearRentPro.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestLogin {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            
            // Test admin login
            String sql = "SELECT username, role FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "admin");
            pstmt.setString(2, "1234");
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("✅ Login successful!");
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Role: " + rs.getString("role"));
            } else {
                System.out.println("❌ Login failed - wrong credentials");
            }
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}