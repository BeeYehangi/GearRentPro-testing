/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GearRentPro.view;

/**
 *
 * @author MCS
 */
public class CustomerManagementPanel {
    
}// src/com/GearRentPro/view/CustomerManagementPanel.java
package com.GearRentPro.view;

import com.GearRentPro.controller.CustomerController;
import com.GearRentPro.dto.CustomerDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerManagementPanel extends JPanel {
    private JTable tblCustomers;
    private DefaultTableModel tableModel;
    private JTextField txtCustomerId, txtName, txtNIC, txtAddress;
    private JTextField txtEmail, txtContact;
    private JCheckBox chkStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh;
    private CustomerController customerController;
    
    public CustomerManagementPanel() {
        customerController = new CustomerController();
        initializeUI();
        loadAllCustomers();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Customer Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Customer ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Customer ID:"), gbc);
        
        gbc.gridx = 1;
        txtCustomerId = new JTextField(20);
        txtCustomerId.setEditable(false);
        formPanel.add(txtCustomerId, gbc);
        
        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Full Name:"), gbc);
        
        gbc.gridx = 1;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);
        
        // NIC
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("NIC Number:"), gbc);
        
        gbc.gridx = 1;
        txtNIC = new JTextField(20);
        formPanel.add(txtNIC, gbc);
        
        // Address
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Address:"), gbc);
        
        gbc.gridx = 1;
        txtAddress = new JTextField(20);
        formPanel.add(txtAddress, gbc);
        
        // Email
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail, gbc);
        
        // Contact
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Contact:"), gbc);
        
        gbc.gridx = 1;
        txtContact = new JTextField(20);
        formPanel.add(txtContact, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 6;
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
        
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        
        // Center Panel - Table
        String[] columns = {"Customer ID", "Name", "NIC", "Email", "Contact", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblCustomers = new JTable(tableModel);
        tblCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCustomers.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedCustomer();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tblCustomers);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Customers List"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Action Listeners
        btnAdd.addActionListener(e -> addCustomer());
        btnUpdate.addActionListener(e -> updateCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadAllCustomers());
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }
    
    private void loadAllCustomers() {
        try {
            tableModel.setRowCount(0);
            List<CustomerDTO> customers = customerController.getAllCustomers();
            
            for (CustomerDTO customer : customers) {
                tableModel.addRow(new Object[]{
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getNic(),
                    customer.getEmail(),
                    customer.getContact(),
                    customer.isStatus() ? "Active" : "Inactive"
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading customers: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSelectedCustomer() {
        int selectedRow = tblCustomers.getSelectedRow();
        if (selectedRow >= 0) {
            txtCustomerId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtNIC.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtEmail.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtContact.setText(tableModel.getValueAt(selectedRow, 4).toString());
            chkStatus.setSelected(tableModel.getValueAt(selectedRow, 5).equals("Active"));
        }
    }
    
    private void addCustomer() {
        try {
            String customerId = "CUST" + System.currentTimeMillis();
            String name = txtName.getText().trim();
            String nic = txtNIC.getText().trim();
            String address = txtAddress.getText().trim();
            String email = txtEmail.getText().trim();
            String contact = txtContact.getText().trim();
            boolean status = chkStatus.isSelected();
            
            if (name.isEmpty() || nic.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please fill all required fields (Name, NIC, Contact)!", 
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
            
            // Check if NIC already exists
            CustomerDTO existingCustomer = customerController.findCustomerByNIC(nic);
            if (existingCustomer != null) {
                JOptionPane.showMessageDialog(this, 
                    "Customer with NIC " + nic + " already exists!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            CustomerDTO customerDTO = new CustomerDTO(
                customerId, name, nic, address, email, contact, status
            );
            
            boolean saved = customerController.saveCustomer(customerDTO);
            
            if (saved) {
                JOptionPane.showMessageDialog(this, 
                    "Customer added successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllCustomers();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error adding customer: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateCustomer() {
        try {
            String customerId = txtCustomerId.getText().trim();
            
            if (customerId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a customer to update!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String name = txtName.getText().trim();
            String nic = txtNIC.getText().trim();
            String address = txtAddress.getText().trim();
            String email = txtEmail.getText().trim();
            String contact = txtContact.getText().trim();
            boolean status = chkStatus.isSelected();
            
            CustomerDTO customerDTO = new CustomerDTO(
                customerId, name, nic, address, email, contact, status
            );
            
            boolean updated = customerController.updateCustomer(customerDTO);
            
            if (updated) {
                JOptionPane.showMessageDialog(this, 
                    "Customer updated successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllCustomers();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error updating customer: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteCustomer() {
        try {
            String customerId = txtCustomerId.getText().trim();
            
            if (customerId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a customer to delete!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this customer?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = customerController.deleteCustomer(customerId);
                
                if (deleted) {
                    JOptionPane.showMessageDialog(this, 
                        "Customer deleted successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    loadAllCustomers();
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error deleting customer: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        txtCustomerId.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtContact.setText("");
        chkStatus.setSelected(true);
        tblCustomers.clearSelection();
    }
}
