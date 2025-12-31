package com.GearRentPro.test;

import com.GearRentPro.db.DBConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            System.out.println("✅ Database connected successfully!");
            
            var stmt = conn.createStatement();
            var rs = stmt.executeQuery("SELECT 1");
            System.out.println("✅ Test query executed successfully!");
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}