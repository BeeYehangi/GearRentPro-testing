// src/com/GearRentPro/view/AdminDashboard.java
package com.GearRentPro.view;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private JTabbedPane tabbedPane;
    
    public AdminDashboard() {
        setTitle("GearRent Pro - Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        
        initializeUI();
    }
    
    private void initializeUI() {
        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(logoutItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        
        // Tabbed Pane
        tabbedPane = new JTabbedPane();
        
        // Create tabs
        tabbedPane.addTab("Dashboard", createDashboardPanel());
        tabbedPane.addTab("User Management", new UserManagementPanel());
        tabbedPane.addTab("Branch Management", new BranchManagementPanel());
        tabbedPane.addTab("Category Management", new CategoryManagementPanel());
        tabbedPane.addTab("Equipment Management", new EquipmentManagementPanel());
        tabbedPane.addTab("Customer Management", new CustomerManagementPanel());
        tabbedPane.addTab("Reservations", new ReservationManagementPanel());
        tabbedPane.addTab("Rentals", new RentalManagementPanel());
        tabbedPane.addTab("Memberships", new MembershipManagementPanel());
        tabbedPane.addTab("Reports", new ReportsPanel());
        
        add(tabbedPane);
        
        // Action Listeners
        logoutItem.addActionListener(e -> logout());
        exitItem.addActionListener(e -> System.exit(0));
        aboutItem.addActionListener(e -> showAbout());
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Header
        JLabel lblHeader = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
        lblHeader.setForeground(new Color(0, 102, 204));
        panel.add(lblHeader, BorderLayout.NORTH);
        
        // Statistics Panel
        JPanel statsPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create statistic cards
        statsPanel.add(createStatCard("Total Users", "25", Color.BLUE));
        statsPanel.add(createStatCard("Active Rentals", "12", Color.GREEN));
        statsPanel.add(createStatCard("Pending Reservations", "8", Color.ORANGE));
        statsPanel.add(createStatCard("Available Equipment", "45", Color.CYAN));
        statsPanel.add(createStatCard("Total Customers", "120", Color.MAGENTA));
        statsPanel.add(createStatCard("Active Memberships", "85", Color.PINK));
        statsPanel.add(createStatCard("Today's Revenue", "$1,250", Color.RED));
        statsPanel.add(createStatCard("Branches", "5", Color.GRAY));
        
        panel.add(statsPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel lblValue = new JLabel(value, SwingConstants.CENTER);
        lblValue.setFont(new Font("Arial", Font.BOLD, 24));
        lblValue.setForeground(color);
        
        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        
        return card;
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", "Logout", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginForm().setVisible(true);
        }
    }
    
    private void showAbout() {
        JOptionPane.showMessageDialog(this, 
            "GearRent Pro v1.0\n\n" +
            "Equipment Rental Management System\n" +
            "Developed for managing gear rentals\n" +
            "© 2024 All Rights Reserved", 
            "About", JOptionPane.INFORMATION_MESSAGE);
    }
}