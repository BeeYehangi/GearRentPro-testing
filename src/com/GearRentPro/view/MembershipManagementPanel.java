// src/com/GearRentPro/view/MembershipManagementPanel.java
package com.GearRentPro.view;

import com.GearRentPro.controller.MembershipController;
import com.GearRentPro.controller.CustomerController;
import com.GearRentPro.dto.MembershipDTO;
import com.GearRentPro.dto.CustomerDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MembershipManagementPanel extends JPanel {
    private JTable tblMemberships;
    private DefaultTableModel tableModel;
    private JTextField txtMembershipId, txtFee, txtStartDate, txtEndDate;
    private JComboBox<String> cmbCustomer, cmbType;
    private JCheckBox chkStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh, btnRenew;
    private MembershipController membershipController;
    private CustomerController customerController;
    
    public MembershipManagementPanel() {
        membershipController = new MembershipController();
        customerController = new CustomerController();
        initializeUI();
        loadCustomers();
        loadAllMemberships();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Membership Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Membership ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Membership ID:"), gbc);
        
        gbc.gridx = 1;
        txtMembershipId = new JTextField(20);
        txtMembershipId.setEditable(false);
        formPanel.add(txtMembershipId, gbc);
        
        // Customer
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Customer:"), gbc);
        
        gbc.gridx = 1;
        cmbCustomer = new JComboBox<>();
        formPanel.add(cmbCustomer, gbc);
        
        // Membership Type
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Type:"), gbc);
        
        gbc.gridx = 1;
        String[] types = {"STANDARD", "PREMIUM", "GOLD"};
        cmbType = new JComboBox<>(types);
        formPanel.add(cmbType, gbc);
        
        // Fee
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Annual Fee ($):"), gbc);
        
        gbc.gridx = 1;
        txtFee = new JTextField(20);
        txtFee.setText("100.00");
        formPanel.add(txtFee, gbc);
        
        // Start Date
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Start Date:"), gbc);
        
        gbc.gridx = 1;
        txtStartDate = new JTextField(20);
        txtStartDate.setText(LocalDate.now().toString());
        formPanel.add(txtStartDate, gbc);
        
        // End Date
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("End Date:"), gbc);
        
        gbc.gridx = 1;
        txtEndDate = new JTextField(20);
        txtEndDate.setText(LocalDate.now().plusYears(1).toString());
        formPanel.add(txtEndDate, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1;
        chkStatus = new JCheckBox("Active");
        chkStatus.setSelected(true);
        formPanel.add(chkStatus, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnAdd = new JButton("Add Membership");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnRefresh = new JButton("Refresh");
        btnRenew = new JButton("Renew Membership");
        
        styleButton(btnAdd, Color.GREEN);
        styleButton(btnUpdate, Color.BLUE);
        styleButton(btnDelete, Color.RED);
        styleButton(btnClear, Color.ORANGE);
        styleButton(btnRefresh, Color.GRAY);
        styleButton(btnRenew, new Color(153, 0, 153));
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnRenew);
        
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        
        // Center Panel - Table
        String[] columns = {"Membership ID", "Customer", "Type", "Fee", "Start Date", "End Date", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblMemberships = new JTable(tableModel);
        tblMemberships.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblMemberships.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedMembership();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tblMemberships);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Memberships List"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Action Listeners
        btnAdd.addActionListener(e -> addMembership());
        btnUpdate.addActionListener(e -> updateMembership());
        btnDelete.addActionListener(e -> deleteMembership());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadAllMemberships());
        btnRenew.addActionListener(e -> renewMembership());
        
        // Add fee calculation based on type
        cmbType.addActionListener(e -> updateFeeBasedOnType());
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }
    
    private void loadCustomers() {
        try {
            cmbCustomer.removeAllItems();
            List<CustomerDTO> customers = customerController.getAllCustomers();
            for (CustomerDTO customer : customers) {
                if (customer.isStatus()) {
                    cmbCustomer.addItem(customer.getCustomerId() + " - " + customer.getName());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage());
        }
    }
    
    private void updateFeeBasedOnType() {
        String type = cmbType.getSelectedItem().toString();
        switch (type) {
            case "STANDARD":
                txtFee.setText("100.00");
                break;
            case "PREMIUM":
                txtFee.setText("200.00");
                break;
            case "GOLD":
                txtFee.setText("350.00");
                break;
        }
    }
    
    private void loadAllMemberships() {
        try {
            tableModel.setRowCount(0);
            List<MembershipDTO> memberships = membershipController.getAllMemberships();
            
            for (MembershipDTO membership : memberships) {
                String status = membership.isStatus() ? 
                    (LocalDate.parse(membership.getEndDate()).isBefore(LocalDate.now()) ? 
                     "EXPIRED" : "ACTIVE") : "INACTIVE";
                
                tableModel.addRow(new Object[]{
                    membership.getMembershipId(),
                    membership.getCustomerId(),
                    membership.getType(),
                    String.format("$%.2f", membership.getFee()),
                    membership.getStartDate(),
                    membership.getEndDate(),
                    status
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading memberships: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSelectedMembership() {
        int selectedRow = tblMemberships.getSelectedRow();
        if (selectedRow >= 0) {
            txtMembershipId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            
            // Set customer combobox
            String customerId = tableModel.getValueAt(selectedRow, 1).toString();
            for (int i = 0; i < cmbCustomer.getItemCount(); i++) {
                if (cmbCustomer.getItemAt(i).startsWith(customerId)) {
                    cmbCustomer.setSelectedIndex(i);
                    break;
                }
            }
            
            cmbType.setSelectedItem(tableModel.getValueAt(selectedRow, 2).toString());
            
            // Parse fee
            String feeStr = tableModel.getValueAt(selectedRow, 3).toString();
            feeStr = feeStr.replace("$", "");
            txtFee.setText(feeStr);
            
            txtStartDate.setText(tableModel.getValueAt(selectedRow, 4).toString());
            txtEndDate.setText(tableModel.getValueAt(selectedRow, 5).toString());
            
            String status = tableModel.getValueAt(selectedRow, 6).toString();
            chkStatus.setSelected("ACTIVE".equals(status));
        }
    }
    
    private void addMembership() {
        try {
            String membershipId = "MEM" + System.currentTimeMillis();
            
            // Get customer ID from combobox
            String customerFull = cmbCustomer.getSelectedItem().toString();
            String customerId = customerFull.split(" - ")[0];
            
            String type = cmbType.getSelectedItem().toString();
            double fee = Double.parseDouble(txtFee.getText().trim());
            String startDate = txtStartDate.getText().trim();
            String endDate = txtEndDate.getText().trim();
            boolean status = chkStatus.isSelected();
            
            // Validate dates
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            
            if (end.isBefore(start)) {
                JOptionPane.showMessageDialog(this, 
                    "End date must be after start date!", 
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Check if customer already has active membership
            MembershipDTO existing = membershipController.findMembershipByCustomer(customerId);
            if (existing != null && existing.isStatus() && 
                LocalDate.parse(existing.getEndDate()).isAfter(LocalDate.now())) {
                int option = JOptionPane.showConfirmDialog(this,
                    "Customer already has an active membership until " + 
                    existing.getEndDate() + ".\nDo you want to create a new membership anyway?",
                    "Existing Membership", JOptionPane.YES_NO_OPTION);
                
                if (option != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            
            MembershipDTO membershipDTO = new MembershipDTO(
                membershipId, customerId, type, fee, startDate, endDate, status
            );
            
            boolean saved = membershipController.saveMembership(membershipDTO);
            
            if (saved) {
                JOptionPane.showMessageDialog(this, 
                    "Membership added successfully!\n" +
                    "Customer will receive " + getDiscountPercentage(type) + "% discount on rentals.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllMemberships();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error adding membership: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String getDiscountPercentage(String type) {
        switch (type) {
            case "STANDARD": return "5";
            case "PREMIUM": return "10";
            case "GOLD": return "15";
            default: return "0";
        }
    }
    
    private void updateMembership() {
        try {
            String membershipId = txtMembershipId.getText().trim();
            
            if (membershipId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a membership to update!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String customerFull = cmbCustomer.getSelectedItem().toString();
            String customerId = customerFull.split(" - ")[0];
            String type = cmbType.getSelectedItem().toString();
            double fee = Double.parseDouble(txtFee.getText().trim());
            String startDate = txtStartDate.getText().trim();
            String endDate = txtEndDate.getText().trim();
            boolean status = chkStatus.isSelected();
            
            MembershipDTO membershipDTO = new MembershipDTO(
                membershipId, customerId, type, fee, startDate, endDate, status
            );
            
            boolean updated = membershipController.updateMembership(membershipDTO);
            
            if (updated) {
                JOptionPane.showMessageDialog(this, 
                    "Membership updated successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllMemberships();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error updating membership: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteMembership() {
        try {
            String membershipId = txtMembershipId.getText().trim();
            
            if (membershipId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a membership to delete!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this membership?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = membershipController.deleteMembership(membershipId);
                
                if (deleted) {
                    JOptionPane.showMessageDialog(this, 
                        "Membership deleted successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    loadAllMemberships();
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error deleting membership: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void renewMembership() {
        try {
            String membershipId = txtMembershipId.getText().trim();
            
            if (membershipId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a membership to renew!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Extend membership by 1 year
            LocalDate currentEndDate = LocalDate.parse(txtEndDate.getText().trim());
            LocalDate newEndDate = currentEndDate.plusYears(1);
            
            int confirm = JOptionPane.showConfirmDialog(this,
                "Renew membership for 1 year?\nNew end date: " + newEndDate,
                "Renew Membership", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean renewed = membershipController.renewMembership(
                    membershipId, newEndDate.toString());
                
                if (renewed) {
                    txtEndDate.setText(newEndDate.toString());
                    chkStatus.setSelected(true);
                    
                    JOptionPane.showMessageDialog(this, 
                        "Membership renewed until " + newEndDate + "!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    loadAllMemberships();
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error renewing membership: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        txtMembershipId.setText("");
        cmbCustomer.setSelectedIndex(0);
        cmbType.setSelectedIndex(0);
        txtFee.setText("100.00");
        txtStartDate.setText(LocalDate.now().toString());
        txtEndDate.setText(LocalDate.now().plusYears(1).toString());
        chkStatus.setSelected(true);
        tblMemberships.clearSelection();
    }
}