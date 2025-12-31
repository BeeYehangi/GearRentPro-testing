// src/com/GearRentPro/view/LoginForm.java
package com.GearRentPro.view;

import com.GearRentPro.controller.UserController;
import com.GearRentPro.dto.UserDTO;
import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnExit;
    private UserController userController;
    
    public LoginForm() {
        userController = new UserController();
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("GearRent Pro - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Header
        JLabel lblTitle = new JLabel("GearRent Pro", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 204));
        add(lblTitle, BorderLayout.NORTH);
        
        // Center Panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(new JLabel("Username:"), gbc);
        
        gbc.gridx = 1;
        txtUsername = new JTextField(20);
        centerPanel.add(txtUsername, gbc);
        
        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1;
        txtPassword = new JPasswordField(20);
        centerPanel.add(txtPassword, gbc);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnLogin = new JButton("Login");
        btnExit = new JButton("Exit");
        
        btnLogin.setBackground(new Color(0, 153, 76));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        
        btnExit.setBackground(new Color(204, 0, 0));
        btnExit.setForeground(Color.WHITE);
        btnExit.setFocusPainted(false);
        
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnExit);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Action Listeners
        btnLogin.addActionListener(e -> login());
        btnExit.addActionListener(e -> System.exit(0));
        
        // Enter key support
        txtPassword.addActionListener(e -> login());
    }
    
    private void login() {
        try {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            UserDTO user = userController.authenticate(username, password);
            
            if (user != null) {
                JOptionPane.showMessageDialog(this, "Login successful!\nWelcome " + user.getRole(), 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Open main dashboard based on user role
                openDashboard(user);
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void openDashboard(UserDTO user) {
        dispose(); // Close login form
        
        switch (user.getRole().toUpperCase()) {
            case "ADMIN":
                new AdminDashboard().setVisible(true);
                break;
            case "MANAGER":
                new ManagerDashboard().setVisible(true);
                break;
            case "STAFF":
                new StaffDashboard().setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Unknown role: " + user.getRole());
                new LoginForm().setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}