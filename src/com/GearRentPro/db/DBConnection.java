package com.GearRentPro.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Singleton instance
    private static DBConnection dbConnection;
    private Connection connection;
    
    // Database configuration - UPDATE THESE IF NEEDED
    private static final String URL = "jdbc:mysql://localhost:3306/gearrent_pro";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "bimaya@123";
    
    // Private constructor (Singleton pattern)
    private DBConnection() {
        initializeConnection();
    }
    
    // Initialize database connection
    private void initializeConnection() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            // Optional: Test connection
            if (connection != null && !connection.isClosed()) {
                System.out.println("✅ Database connection established successfully!");
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL JDBC Driver not found!");
            System.err.println("Please add mysql-connector-java.jar to your lib folder!");
            e.printStackTrace();
            
        } catch (SQLException e) {
            System.err.println("❌ Failed to connect to database!");
            System.err.println("URL: " + URL);
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Get singleton instance
    public static DBConnection getInstance() {
        if (dbConnection == null) {
            synchronized (DBConnection.class) {
                if (dbConnection == null) {
                    dbConnection = new DBConnection();
                }
            }
        }
        return dbConnection;
    }
    
    // Get the database connection
    public Connection getConnection() {
        try {
            // Check if connection is closed or null, reconnect if needed
            if (connection == null || connection.isClosed()) {
                System.out.println("Reconnecting to database...");
                initializeConnection();
            }
        } catch (SQLException e) {
            System.err.println("Error checking connection status: " + e.getMessage());
        }
        return connection;
    }
    
    // Close connection (call when application exits)
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
    
    // Test method to verify connection
    public boolean testConnection() {
        try {
            Connection conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                return conn.createStatement().execute("SELECT 1");
            }
        } catch (SQLException e) {
            System.err.println("Connection test failed: " + e.getMessage());
        }
        return false;
    }
}