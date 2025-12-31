// src/com/GearRentPro/view/UserManagementPanel.java
package com.GearRentPro.view;

import com.GearRentPro.controller.UserController;
import com.GearRentPro.dto.UserDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserManagementPanel extends JPanel {
    private JTable tblUsers;
    private DefaultTableModel tableModel;
    private JTextField txtUserId, txtUsername, txtPassword, txtRole, txtBranchId;
    private JCheckBox chkStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh;
    private UserController userController;
    
    public UserManagementPanel() {
        userController = new UserController();
        initializeUI();
        loadAllUsers();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("User Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // User ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("User ID:"), gbc);
        
        gbc.gridx = 1;
        txtUserId = new JTextField(20);
        txtUserId.setEditable(false);
        formPanel.add(txtUserId, gbc);
        
        // Username
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Username:"), gbc);
        
        gbc.gridx = 1;
        txtUsername = new JTextField(20);
        formPanel.add(txtUsername, gbc);
        
        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Password:"), gbc);
        
        gbc.gridx = 1;
        txtPassword = new JTextField(20);
        formPanel.add(txtPassword, gbc);
        
        // Role
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Role:"), gbc);
        
        gbc.gridx = 1;
        String[] roles = {"ADMIN", "MANAGER", "STAFF"};
        JComboBox<String> cmbRole = new JComboBox<>(roles);
        formPanel.add(cmbRole, gbc);
        txtRole = new JTextField();
        txtRole.setVisible(false);
        
        // Branch ID
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Branch ID:"), gbc);
        
        gbc.gridx = 1;
        txtBranchId = new JTextField(20);
        formPanel.add(txtBranchId, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1;
        chkStatus = new JCheckBox("Active");
        chkStatus.setSelected(true);
        formPanel.add(chkStatus, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnRefresh = new JButton("Refresh");
        
        styleButton(btnAdd, Color.GREEN);
        styleButton(btnUpdate, Color.BLUE);
        styleButton(btnDelete, Color.RED);
        styleButton(btnClear, Color.ORANGE);
        styleButton(btnRefresh, Color.GRAY);
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnRefresh);
        
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        
        // Center Panel - Table
        String[] columns = {"User ID", "Username", "Role", "Branch ID", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblUsers = new JTable(tableModel);
        tblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblUsers.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedUser();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tblUsers);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Users List"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Action Listeners
        btnAdd.addActionListener(e -> addUser());
        btnUpdate.addActionListener(e -> updateUser());
        btnDelete.addActionListener(e -> deleteUser());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadAllUsers());
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }
    
    private void loadAllUsers() {
        try {
            tableModel.setRowCount(0);
            List<UserDTO> users = userController.getAllUsers();
            
            for (UserDTO user : users) {
                tableModel.addRow(new Object[]{
                    user.getUserId(),
                    user.getUsername(),
                    user.getRole(),
                    user.getBranchId(),
                    user.isStatus() ? "Active" : "Inactive"
                });
            }
            
            JOptionPane.showMessageDialog(this, 
                "Loaded " + users.size() + " users", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading users: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSelectedUser() {
        int selectedRow = tblUsers.getSelectedRow();
        if (selectedRow >= 0) {
            txtUserId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtUsername.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtRole.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtBranchId.setText(tableModel.getValueAt(selectedRow, 3).toString());
            chkStatus.setSelected(tableModel.getValueAt(selectedRow, 4).equals("Active"));
            txtPassword.setText(""); // Don't show password
        }
    }
    
    private void addUser() {
        try {
            String userId = "U" + System.currentTimeMillis(); // Generate ID
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            String role = txtRole.getText().trim();
            String branchId = txtBranchId.getText().trim();
            boolean status = chkStatus.isSelected();
            
            if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill all required fields!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            boolean saved = userController.saveUser(userId, username, password, 
                                                   role, branchId, status);
            
            if (saved) {
                JOptionPane.showMessageDialog(this, 
                    "User added successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllUsers();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error adding user: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateUser() {
        try {
            String userId = txtUserId.getText().trim();
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();
            String role = txtRole.getText().trim();
            String branchId = txtBranchId.getText().trim();
            boolean status = chkStatus.isSelected();
            
            if (userId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a user to update!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            boolean updated = userController.updateUser(userId, username, password, 
                                                       role, branchId, status);
            
            if (updated) {
                JOptionPane.showMessageDialog(this, 
                    "User updated successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllUsers();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error updating user: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteUser() {
        try {
            String userId = txtUserId.getText().trim();
            
            if (userId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a user to delete!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this user?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = userController.deleteUser(userId);
                
                if (deleted) {
                    JOptionPane.showMessageDialog(this, 
                        "User deleted successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    loadAllUsers();
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error deleting user: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        txtUserId.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtRole.setText("");
        txtBranchId.setText("");
        chkStatus.setSelected(true);
        tblUsers.clearSelection();
    }
}