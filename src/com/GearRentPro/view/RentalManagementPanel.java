// src/com/GearRentPro/view/RentalManagementPanel.java
package com.GearRentPro.view;

import com.GearRentPro.controller.RentalController;
import com.GearRentPro.controller.ReservationController;
import com.GearRentPro.controller.EquipmentController;
import com.GearRentPro.controller.CustomerController;
import com.GearRentPro.dto.RentalDTO;
import com.GearRentPro.dto.ReservationDTO;
import com.GearRentPro.dto.EquipmentDTO;
import com.GearRentPro.dto.CustomerDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class RentalManagementPanel extends JPanel {
    private JTable tblRentals;
    private DefaultTableModel tableModel;
    private JTextField txtRentalId, txtStartDate, txtEndDate, txtActualReturnDate;
    private JTextField txtRentalAmount, txtSecurityDeposit, txtMembershipDiscount;
    private JTextField txtLongRentalDiscount, txtFinalPayableAmount, txtLateFee, txtDamageCharge;
    private JComboBox<String> cmbReservation, cmbEquipment, cmbCustomer, cmbBranch;
    private JComboBox<String> cmbPaymentStatus, cmbRentalStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh, btnCalculate, btnReturn;
    private RentalController rentalController;
    private ReservationController reservationController;
    private EquipmentController equipmentController;
    private CustomerController customerController;
    
    public RentalManagementPanel() {
        rentalController = new RentalController();
        reservationController = new ReservationController();
        equipmentController = new EquipmentController();
        customerController = new CustomerController();
        initializeUI();
        loadReservations();
        loadEquipment();
        loadCustomers();
        loadAllRentals();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Form (using JTabbedPane for better organization)
        JTabbedPane formTabbedPane = new JTabbedPane();
        
        // Basic Information Tab
        JPanel basicInfoPanel = createBasicInfoPanel();
        formTabbedPane.addTab("Basic Information", basicInfoPanel);
        
        // Financial Details Tab
        JPanel financialPanel = createFinancialPanel();
        formTabbedPane.addTab("Financial Details", financialPanel);
        
        // Status Tab
        JPanel statusPanel = createStatusPanel();
        formTabbedPane.addTab("Status", statusPanel);
        
        add(formTabbedPane, BorderLayout.NORTH);
        
        // Center Panel - Table
        String[] columns = {"Rental ID", "Equipment", "Customer", "Start Date", "End Date", 
                          "Rental Amount", "Payment Status", "Rental Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblRentals = new JTable(tableModel);
        tblRentals.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblRentals.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedRental();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tblRentals);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Rentals List"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Button Panel at bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAdd = new JButton("Add Rental");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnRefresh = new JButton("Refresh");
        btnCalculate = new JButton("Calculate Charges");
        btnReturn = new JButton("Mark Returned");
        
        styleButton(btnAdd, Color.GREEN);
        styleButton(btnUpdate, Color.BLUE);
        styleButton(btnDelete, Color.RED);
        styleButton(btnClear, Color.ORANGE);
        styleButton(btnRefresh, Color.GRAY);
        styleButton(btnCalculate, new Color(0, 102, 204));
        styleButton(btnReturn, new Color(153, 0, 153));
        
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnReturn);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Action Listeners
        btnAdd.addActionListener(e -> addRental());
        btnUpdate.addActionListener(e -> updateRental());
        btnDelete.addActionListener(e -> deleteRental());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadAllRentals());
        btnCalculate.addActionListener(e -> calculateCharges());
        btnReturn.addActionListener(e -> markAsReturned());
    }
    
    private JPanel createBasicInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Rental ID
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Rental ID:"), gbc);
        
        gbc.gridx = 1;
        txtRentalId = new JTextField(20);
        txtRentalId.setEditable(false);
        panel.add(txtRentalId, gbc);
        
        // Reservation (optional)
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Reservation:"), gbc);
        
        gbc.gridx = 1;
        cmbReservation = new JComboBox<>();
        panel.add(cmbReservation, gbc);
        
        // Equipment
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Equipment:"), gbc);
        
        gbc.gridx = 1;
        cmbEquipment = new JComboBox<>();
        panel.add(cmbEquipment, gbc);
        
        // Customer
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Customer:"), gbc);
        
        gbc.gridx = 1;
        cmbCustomer = new JComboBox<>();
        panel.add(cmbCustomer, gbc);
        
        // Start Date
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Start Date:"), gbc);
        
        gbc.gridx = 1;
        txtStartDate = new JTextField(20);
        txtStartDate.setText(LocalDate.now().toString());
        panel.add(txtStartDate, gbc);
        
        // End Date
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("End Date:"), gbc);
        
        gbc.gridx = 1;
        txtEndDate = new JTextField(20);
        txtEndDate.setText(LocalDate.now().plusDays(3).toString());
        panel.add(txtEndDate, gbc);
        
        // Actual Return Date
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Actual Return:"), gbc);
        
        gbc.gridx = 1;
        txtActualReturnDate = new JTextField(20);
        panel.add(txtActualReturnDate, gbc);
        
        return panel;
    }
    
    private JPanel createFinancialPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Rental Amount
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Rental Amount:"), gbc);
        
        gbc.gridx = 1;
        txtRentalAmount = new JTextField(20);
        panel.add(txtRentalAmount, gbc);
        
        // Security Deposit
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Security Deposit:"), gbc);
        
        gbc.gridx = 1;
        txtSecurityDeposit = new JTextField(20);
        panel.add(txtSecurityDeposit, gbc);
        
        // Membership Discount
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Membership Discount:"), gbc);
        
        gbc.gridx = 1;
        txtMembershipDiscount = new JTextField(20);
        txtMembershipDiscount.setText("0.00");
        panel.add(txtMembershipDiscount, gbc);
        
        // Long Rental Discount
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Long Rental Discount:"), gbc);
        
        gbc.gridx = 1;
        txtLongRentalDiscount = new JTextField(20);
        txtLongRentalDiscount.setText("0.00");
        panel.add(txtLongRentalDiscount, gbc);
        
        // Final Payable Amount
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Final Amount:"), gbc);
        
        gbc.gridx = 1;
        txtFinalPayableAmount = new JTextField(20);
        panel.add(txtFinalPayableAmount, gbc);
        
        // Late Fee
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Late Fee:"), gbc);
        
        gbc.gridx = 1;
        txtLateFee = new JTextField(20);
        txtLateFee.setText("0.00");
        panel.add(txtLateFee, gbc);
        
        // Damage Charge
        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Damage Charge:"), gbc);
        
        gbc.gridx = 1;
        txtDamageCharge = new JTextField(20);
        txtDamageCharge.setText("0.00");
        panel.add(txtDamageCharge, gbc);
        
        return panel;
    }
    
    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Payment Status
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Payment Status:"), gbc);
        
        gbc.gridx = 1;
        String[] paymentStatuses = {"PENDING", "PARTIAL", "PAID", "REFUNDED"};
        cmbPaymentStatus = new JComboBox<>(paymentStatuses);
        panel.add(cmbPaymentStatus, gbc);
        
        // Rental Status
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Rental Status:"), gbc);
        
        gbc.gridx = 1;
        String[] rentalStatuses = {"ACTIVE", "COMPLETED", "OVERDUE", "CANCELLED"};
        cmbRentalStatus = new JComboBox<>(rentalStatuses);
        panel.add(cmbRentalStatus, gbc);
        
        // Created Date (auto)
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Created Date:"), gbc);
        
        gbc.gridx = 1;
        JLabel lblCreatedDate = new JLabel(LocalDate.now().toString());
        panel.add(lblCreatedDate, gbc);
        
        return panel;
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }
    
    private void loadReservations() {
        try {
            cmbReservation.removeAllItems();
            cmbReservation.addItem("NONE - Walk-in Rental");
            
            List<ReservationDTO> reservations = reservationController.getAllReservations();
            for (ReservationDTO reservation : reservations) {
                if ("CONFIRMED".equals(reservation.getStatus())) {
                    cmbReservation.addItem(reservation.getReservationId() + " - " + 
                                          reservation.getEquipmentId());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading reservations: " + e.getMessage());
        }
    }
    
    private void loadEquipment() {
        try {
            cmbEquipment.removeAllItems();
            List<EquipmentDTO> equipmentList = equipmentController.getAllEquipment();
            for (EquipmentDTO equipment : equipmentList) {
                if ("AVAILABLE".equals(equipment.getStatus())) {
                    cmbEquipment.addItem(equipment.getEquipmentId() + " - " + 
                                       equipment.getBrand() + " " + equipment.getModel());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading equipment: " + e.getMessage());
        }
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
    
    private void loadAllRentals() {
        try {
            tableModel.setRowCount(0);
            List<RentalDTO> rentals = rentalController.getAllRentals();
            
            for (RentalDTO rental : rentals) {
                tableModel.addRow(new Object[]{
                    rental.getRentalId(),
                    rental.getEquipmentId(),
                    rental.getCustomerId(),
                    rental.getStartDate(),
                    rental.getEndDate(),
                    String.format("$%.2f", rental.getRentalAmount()),
                    rental.getPaymentStatus(),
                    rental.getRentalStatus()
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading rentals: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSelectedRental() {
        int selectedRow = tblRentals.getSelectedRow();
        if (selectedRow >= 0) {
            txtRentalId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            
            // Note: You'll need to fetch the full rental details from controller
            // For now, just populate basic info
            // Equipment, Customer, etc. would need to be loaded from the rental object
        }
    }
    
    private void addRental() {
        try {
            String rentalId = "RENT" + System.currentTimeMillis();
            
            // Get IDs from comboboxes
            String reservationFull = cmbReservation.getSelectedItem().toString();
            String equipmentFull = cmbEquipment.getSelectedItem().toString();
            String customerFull = cmbCustomer.getSelectedItem().toString();
            
            String reservationId = reservationFull.startsWith("NONE") ? null : 
                                  reservationFull.split(" - ")[0];
            String equipmentId = equipmentFull.split(" - ")[0];
            String customerId = customerFull.split(" - ")[0];
            
            // For now, using a placeholder branch ID
            String branchId = "BR001";
            
            Date startDate = Date.valueOf(txtStartDate.getText().trim());
            Date endDate = Date.valueOf(txtEndDate.getText().trim());
            Date actualReturnDate = txtActualReturnDate.getText().isEmpty() ? null :
                                   Date.valueOf(txtActualReturnDate.getText().trim());
            
            double rentalAmount = Double.parseDouble(txtRentalAmount.getText().trim());
            double securityDeposit = Double.parseDouble(txtSecurityDeposit.getText().trim());
            double membershipDiscount = Double.parseDouble(txtMembershipDiscount.getText().trim());
            double longRentalDiscount = Double.parseDouble(txtLongRentalDiscount.getText().trim());
            double finalPayableAmount = Double.parseDouble(txtFinalPayableAmount.getText().trim());
            double lateFee = Double.parseDouble(txtLateFee.getText().trim());
            double damageCharge = Double.parseDouble(txtDamageCharge.getText().trim());
            
            String paymentStatus = cmbPaymentStatus.getSelectedItem().toString();
            String rentalStatus = cmbRentalStatus.getSelectedItem().toString();
            
            // Create DTO
            RentalDTO rentalDTO = new RentalDTO(
                rentalId, reservationId, equipmentId, customerId, branchId,
                startDate, endDate, actualReturnDate, rentalAmount,
                securityDeposit, membershipDiscount, longRentalDiscount,
                finalPayableAmount, lateFee, damageCharge,
                paymentStatus, rentalStatus, new Date(System.currentTimeMillis())
            );
            
            boolean saved = rentalController.saveRental(rentalDTO);
            
            if (saved) {
                JOptionPane.showMessageDialog(this, 
                    "Rental added successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllRentals();
                loadEquipment(); // Refresh available equipment
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error adding rental: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateRental() {
        // Similar to addRental but with existing rentalId
        JOptionPane.showMessageDialog(this, 
            "Update functionality to be implemented!", 
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void deleteRental() {
        try {
            String rentalId = txtRentalId.getText().trim();
            
            if (rentalId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a rental to delete!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this rental?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = rentalController.deleteRental(rentalId);
                
                if (deleted) {
                    JOptionPane.showMessageDialog(this, 
                        "Rental deleted successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    loadAllRentals();
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error deleting rental: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void calculateCharges() {
        try {
            // Calculate based on equipment daily rate and duration
            String equipmentFull = cmbEquipment.getSelectedItem().toString();
            String equipmentId = equipmentFull.split(" - ")[0];
            
            // Get equipment details (you'll need to fetch from controller)
            EquipmentDTO equipment = equipmentController.findEquipment(equipmentId);
            
            if (equipment != null) {
                double dailyRate = equipment.getBaseDailyPrice();
                Date startDate = Date.valueOf(txtStartDate.getText().trim());
                Date endDate = Date.valueOf(txtEndDate.getText().trim());
                
                long days = ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
                if (days <= 0) days = 1;
                
                double baseAmount = dailyRate * days;
                double securityDeposit = equipment.getSecurityDeposit();
                
                // Apply discounts
                double membershipDiscount = 0.10 * baseAmount; // 10% example
                double longRentalDiscount = days > 7 ? 0.15 * baseAmount : 0; // 15% for >7 days
                
                double total = baseAmount - membershipDiscount - longRentalDiscount;
                
                // Update fields
                txtRentalAmount.setText(String.format("%.2f", baseAmount));
                txtSecurityDeposit.setText(String.format("%.2f", securityDeposit));
                txtMembershipDiscount.setText(String.format("%.2f", membershipDiscount));
                txtLongRentalDiscount.setText(String.format("%.2f", longRentalDiscount));
                txtFinalPayableAmount.setText(String.format("%.2f", total));
                
                JOptionPane.showMessageDialog(this, 
                    "Charges calculated!\n" +
                    "Daily Rate: $" + dailyRate + "\n" +
                    "Duration: " + days + " days\n" +
                    "Base Amount: $" + baseAmount + "\n" +
                    "Total Payable: $" + total, 
                    "Calculation Results", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error calculating charges: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void markAsReturned() {
        String rentalId = txtRentalId.getText().trim();
        
        if (rentalId.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please select a rental to mark as returned!", 
                "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            txtActualReturnDate.setText(LocalDate.now().toString());
            cmbRentalStatus.setSelectedItem("COMPLETED");
            
            // Calculate late fees if any
            Date endDate = Date.valueOf(txtEndDate.getText().trim());
            LocalDate today = LocalDate.now();
            
            if (today.isAfter(endDate.toLocalDate())) {
                long lateDays = ChronoUnit.DAYS.between(endDate.toLocalDate(), today);
                double dailyRate = Double.parseDouble(txtRentalAmount.getText()) / 
                                 ChronoUnit.DAYS.between(
                                     Date.valueOf(txtStartDate.getText()).toLocalDate(),
                                     endDate.toLocalDate()
                                 );
                double lateFeeAmount = lateDays * dailyRate * 0.5; // 50% of daily rate as late fee
                txtLateFee.setText(String.format("%.2f", lateFeeAmount));
            }
            
            JOptionPane.showMessageDialog(this, 
                "Rental marked as returned!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error processing return: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        txtRentalId.setText("");
        cmbReservation.setSelectedIndex(0);
        cmbEquipment.setSelectedIndex(0);
        cmbCustomer.setSelectedIndex(0);
        txtStartDate.setText(LocalDate.now().toString());
        txtEndDate.setText(LocalDate.now().plusDays(3).toString());
        txtActualReturnDate.setText("");
        txtRentalAmount.setText("");
        txtSecurityDeposit.setText("");
        txtMembershipDiscount.setText("0.00");
        txtLongRentalDiscount.setText("0.00");
        txtFinalPayableAmount.setText("");
        txtLateFee.setText("0.00");
        txtDamageCharge.setText("0.00");
        cmbPaymentStatus.setSelectedIndex(0);
        cmbRentalStatus.setSelectedIndex(0);
        tblRentals.clearSelection();
    }
}