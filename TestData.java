package com.GearRentPro.test;

import com.GearRentPro.db.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestData {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            
            System.out.println("📊 Checking database tables...\n");
            
            // Check tables
            String[] tables = {"users", "branches", "customers", "equipment", "rentals"};
            
            for (String table : tables) {
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + table);
                if (rs.next()) {
                    System.out.println(table + ": " + rs.getInt(1) + " records");
                }
                rs.close();
            }
            
            System.out.println("\n✅ Database is ready!");
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}