// src/com/GearRentPro/view/ReservationManagementPanel.java
package com.GearRentPro.view;

import com.GearRentPro.controller.ReservationController;
import com.GearRentPro.controller.CustomerController;
import com.GearRentPro.controller.EquipmentController;
import com.GearRentPro.controller.BranchController;
import com.GearRentPro.dto.ReservationDTO;
import com.GearRentPro.dto.CustomerDTO;
import com.GearRentPro.dto.EquipmentDTO;
import com.GearRentPro.dto.BranchDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservationManagementPanel extends JPanel {
    private JTable tblReservations;
    private DefaultTableModel tableModel;
    private JTextField txtReservationId, txtStartDate, txtEndDate;
    private JComboBox<String> cmbCustomer, cmbEquipment, cmbBranch, cmbStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh, btnProcessRental;
    private ReservationController reservationController;
    private CustomerController customerController;
    private EquipmentController equipmentController;
    private BranchController branchController;
    
    public ReservationManagementPanel() {
        reservationController = new ReservationController();
        customerController = new CustomerController();
        equipmentController = new EquipmentController();
        branchController = new BranchController();
        initializeUI();
        loadCustomers();
        loadEquipment();
        loadBranches();
        loadAllReservations();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Reservation Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Reservation ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Reservation ID:"), gbc);
        
        gbc.gridx = 1;
        txtReservationId = new JTextField(20);
        txtReservationId.setEditable(false);
        formPanel.add(txtReservationId, gbc);
        
        // Customer
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Customer:"), gbc);
        
        gbc.gridx = 1;
        cmbCustomer = new JComboBox<>();
        formPanel.add(cmbCustomer, gbc);
        
        // Equipment
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Equipment:"), gbc);
        
        gbc.gridx = 1;
        cmbEquipment = new JComboBox<>();
        formPanel.add(cmbEquipment, gbc);
        
        // Branch
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Branch:"), gbc);
        
        gbc.gridx = 1;
        cmbBranch = new JComboBox<>();
        formPanel.add(cmbBranch, gbc);
        
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
        txtEndDate.setText(LocalDate.now().plusDays(3).toString());
        formPanel.add(txtEndDate, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1;
        String[] statuses = {"PENDING", "CONFIRMED", "CANCELLED", "COMPLETED"};
        cmbStatus = new JComboBox<>(statuses);
        formPanel.add(cmbStatus, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnRefresh = new JButton("Refresh");
        btnProcessRental = new JButton("Process Rental");
        
        styleButton(btnAdd, Color.GREEN);
        styleButton(btnUpdate, Color.BLUE);
        styleButton(btnDelete, Color.RED);
        styleButton(btnClear, Color.ORANGE);
        styleButton(btnRefresh, Color.GRAY);
        styleButton(btnProcessRental, new Color(153, 0, 153)); // Purple
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnProcessRental);
        
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        
        // Center Panel - Table
        String[] columns = {"Reservation ID", "Customer", "Equipment", "Branch", "Start Date", "End Date", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblReservations = new JTable(tableModel);
        tblReservations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblReservations.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedReservation();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tblReservations);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Reservations List"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Action Listeners
        btnAdd.addActionListener(e -> addReservation());
        btnUpdate.addActionListener(e -> updateReservation());
        btnDelete.addActionListener(e -> deleteReservation());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadAllReservations());
        btnProcessRental.addActionListener(e -> processRental());
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
                if (customer.isStatus()) { // Only active customers
                    cmbCustomer.addItem(customer.getCustomerId() + " - " + customer.getName());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading customers: " + e.getMessage());
        }
    }
    
    private void loadEquipment() {
        try {
            cmbEquipment.removeAllItems();
            List<EquipmentDTO> equipmentList = equipmentController.getAvailableEquipment();
            for (EquipmentDTO equipment : equipmentList) {
                cmbEquipment.addItem(equipment.getEquipmentId() + " - " + 
                                   equipment.getBrand() + " " + equipment.getModel());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading equipment: " + e.getMessage());
        }
    }
    
    private void loadBranches() {
        try {
            cmbBranch.removeAllItems();
            List<BranchDTO> branches = branchController.getAllBranches();
            for (BranchDTO branch : branches) {
                cmbBranch.addItem(branch.getBranchId() + " - " + branch.getLocation());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading branches: " + e.getMessage());
        }
    }
    
    private void loadAllReservations() {
        try {
            tableModel.setRowCount(0);
            List<ReservationDTO> reservations = reservationController.getAllReservations();
            
            for (ReservationDTO reservation : reservations) {
                tableModel.addRow(new Object[]{
                    reservation.getReservationId(),
                    reservation.getCustomerId(),
                    reservation.getEquipmentId(),
                    reservation.getBranchId(),
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getStatus()
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading reservations: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSelectedReservation() {
        int selectedRow = tblReservations.getSelectedRow();
        if (selectedRow >= 0) {
            txtReservationId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            
            // Set customer combobox
            String customerId = tableModel.getValueAt(selectedRow, 1).toString();
            for (int i = 0; i < cmbCustomer.getItemCount(); i++) {
                if (cmbCustomer.getItemAt(i).startsWith(customerId)) {
                    cmbCustomer.setSelectedIndex(i);
                    break;
                }
            }
            
            // Set equipment combobox
            String equipmentId = tableModel.getValueAt(selectedRow, 2).toString();
            for (int i = 0; i < cmbEquipment.getItemCount(); i++) {
                if (cmbEquipment.getItemAt(i).startsWith(equipmentId)) {
                    cmbEquipment.setSelectedIndex(i);
                    break;
                }
            }
            
            // Set branch combobox
            String branchId = tableModel.getValueAt(selectedRow, 3).toString();
            for (int i = 0; i < cmbBranch.getItemCount(); i++) {
                if (cmbBranch.getItemAt(i).startsWith(branchId)) {
                    cmbBranch.setSelectedIndex(i);
                    break;
                }
            }
            
            txtStartDate.setText(tableModel.getValueAt(selectedRow, 4).toString());
            txtEndDate.setText(tableModel.getValueAt(selectedRow, 5).toString());
            cmbStatus.setSelectedItem(tableModel.getValueAt(selectedRow, 6).toString());
        }
    }
    
    private void addReservation() {
        try {
            String reservationId = "RES" + System.currentTimeMillis();
            
            // Get IDs from comboboxes
            String customerFull = cmbCustomer.getSelectedItem().toString();
            String equipmentFull = cmbEquipment.getSelectedItem().toString();
            String branchFull = cmbBranch.getSelectedItem().toString();
            
            String customerId = customerFull.split(" - ")[0];
            String equipmentId = equipmentFull.split(" - ")[0];
            String branchId = branchFull.split(" - ")[0];
            
            Date startDate = Date.valueOf(txtStartDate.getText().trim());
            Date endDate = Date.valueOf(txtEndDate.getText().trim());
            String status = cmbStatus.getSelectedItem().toString();
            
            // Validate dates
            if (endDate.before(startDate)) {
                JOptionPane.showMessageDialog(this, 
                    "End date must be after start date!", 
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Create DTO
            ReservationDTO reservationDTO = new ReservationDTO(
                reservationId, equipmentId, customerId, branchId,
                startDate, endDate, status, new Date(System.currentTimeMillis())
            );
            
            boolean saved = reservationController.saveReservation(reservationDTO);
            
            if (saved) {
                JOptionPane.showMessageDialog(this, 
                    "Reservation added successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllReservations();
                loadEquipment(); // Refresh available equipment
            }
            
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid dates (YYYY-MM-DD)!", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error adding reservation: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateReservation() {
        try {
            String reservationId = txtReservationId.getText().trim();
            
            if (reservationId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a reservation to update!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Get IDs from comboboxes
            String customerFull = cmbCustomer.getSelectedItem().toString();
            String equipmentFull = cmbEquipment.getSelectedItem().toString();
            String branchFull = cmbBranch.getSelectedItem().toString();
            
            String customerId = customerFull.split(" - ")[0];
            String equipmentId = equipmentFull.split(" - ")[0];
            String branchId = branchFull.split(" - ")[0];
            
            Date startDate = Date.valueOf(txtStartDate.getText().trim());
            Date endDate = Date.valueOf(txtEndDate.getText().trim());
            String status = cmbStatus.getSelectedItem().toString();
            
            // Create DTO
            ReservationDTO reservationDTO = new ReservationDTO(
                reservationId, equipmentId, customerId, branchId,
                startDate, endDate, status, new Date(System.currentTimeMillis())
            );
            
            boolean updated = reservationController.updateReservation(reservationDTO);
            
            if (updated) {
                JOptionPane.showMessageDialog(this, 
                    "Reservation updated successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllReservations();
                loadEquipment();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error updating reservation: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteReservation() {
        try {
            String reservationId = txtReservationId.getText().trim();
            
            if (reservationId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a reservation to delete!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this reservation?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = reservationController.deleteReservation(reservationId);
                
                if (deleted) {
                    JOptionPane.showMessageDialog(this, 
                        "Reservation deleted successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    loadAllReservations();
                    loadEquipment();
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error deleting reservation: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void processRental() {
        String reservationId = txtReservationId.getText().trim();
        
        if (reservationId.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please select a reservation to process!", 
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Open rental processing dialog
        JOptionPane.showMessageDialog(this, 
            "Rental processing feature will be implemented here!", 
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void clearForm() {
        txtReservationId.setText("");
        cmbCustomer.setSelectedIndex(0);
        cmbEquipment.setSelectedIndex(0);
        cmbBranch.setSelectedIndex(0);
        txtStartDate.setText(LocalDate.now().toString());
        txtEndDate.setText(LocalDate.now().plusDays(3).toString());
        cmbStatus.setSelectedIndex(0);
        tblReservations.clearSelection();
    }
}