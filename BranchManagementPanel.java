/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GearRentPro.view;

/**
 *
 * @author MCS
 */
public class BranchManagementPanel {
    
}
// src/com/GearRentPro/view/BranchManagementPanel.java
package com.GearRentPro.view;

import com.GearRentPro.controller.BranchController;
import com.GearRentPro.dto.BranchDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BranchManagementPanel extends JPanel {
    private JTable tblBranches;
    private DefaultTableModel tableModel;
    private JTextField txtBranchId, txtLocation, txtContact, txtEmail;
    private JCheckBox chkStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh;
    private BranchController branchController;
    
    public BranchManagementPanel() {
        branchController = new BranchController();
        initializeUI();
        loadAllBranches();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Branch Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Branch ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Branch ID:"), gbc);
        
        gbc.gridx = 1;
        txtBranchId = new JTextField(20);
        txtBranchId.setEditable(false);
        formPanel.add(txtBranchId, gbc);
        
        // Location
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Location:"), gbc);
        
        gbc.gridx = 1;
        txtLocation = new JTextField(20);
        formPanel.add(txtLocation, gbc);
        
        // Contact
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Contact:"), gbc);
        
        gbc.gridx = 1;
        txtContact = new JTextField(20);
        formPanel.add(txtContact, gbc);
        
        // Email
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1;
        chkStatus = new JCheckBox("Active");
        chkStatus.setSelected(true);
        formPanel.add(chkStatus, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnAdd = new JButton("Add Branch");
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
        
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        
        // Center Panel - Table
        String[] columns = {"Branch ID", "Location", "Contact", "Email", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblBranches = new JTable(tableModel);
        tblBranches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblBranches.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedBranch();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tblBranches);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Branches List"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Action Listeners
        btnAdd.addActionListener(e -> addBranch());
        btnUpdate.addActionListener(e -> updateBranch());
        btnDelete.addActionListener(e -> deleteBranch());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadAllBranches());
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }
    
    private void loadAllBranches() {
        try {
            tableModel.setRowCount(0);
            List<BranchDTO> branches = branchController.getAllBranches();
            
            for (BranchDTO branch : branches) {
                tableModel.addRow(new Object[]{
                    branch.getBranchId(),
                    branch.getLocation(),
                    branch.getContact(),
                    branch.getEmail(),
                    branch.isStatus() ? "Active" : "Inactive"
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading branches: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSelectedBranch() {
        int selectedRow = tblBranches.getSelectedRow();
        if (selectedRow >= 0) {
            txtBranchId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtLocation.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtContact.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtEmail.setText(tableModel.getValueAt(selectedRow, 3).toString());
            chkStatus.setSelected(tableModel.getValueAt(selectedRow, 4).equals("Active"));
        }
    }
    
    private void addBranch() {
        try {
            String branchId = "BR" + String.format("%03d", (tableModel.getRowCount() + 1));
            String location = txtLocation.getText().trim();
            String contact = txtContact.getText().trim();
            String email = txtEmail.getText().trim();
            boolean status = chkStatus.isSelected();
            
            if (location.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill Location and Contact fields!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validate email format
            if (!email.isEmpty() && !email.contains("@")) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter a valid email address!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            BranchDTO branchDTO = new BranchDTO(branchId, location, contact, email, status);
            boolean saved = branchController.saveBranch(branchDTO);
            
            if (saved) {
                JOptionPane.showMessageDialog(this, 
                    "Branch added successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllBranches();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error adding branch: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateBranch() {
        try {
            String branchId = txtBranchId.getText().trim();
            
            if (branchId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a branch to update!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String location = txtLocation.getText().trim();
            String contact = txtContact.getText().trim();
            String email = txtEmail.getText().trim();
            boolean status = chkStatus.isSelected();
            
            BranchDTO branchDTO = new BranchDTO(branchId, location, contact, email, status);
            boolean updated = branchController.updateBranch(branchDTO);
            
            if (updated) {
                JOptionPane.showMessageDialog(this, 
                    "Branch updated successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllBranches();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error updating branch: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteBranch() {
        try {
            String branchId = txtBranchId.getText().trim();
            
            if (branchId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a branch to delete!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Check if branch has equipment
            // In real app, you would check if branch is in use
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this branch?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = branchController.deleteBranch(branchId);
                
                if (deleted) {
                    JOptionPane.showMessageDialog(this, 
                        "Branch deleted successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    loadAllBranches();
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error deleting branch: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        txtBranchId.setText("");
        txtLocation.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        chkStatus.setSelected(true);
        tblBranches.clearSelection();
    }
}