// src/com/GearRentPro/view/EquipmentManagementPanel.java
package com.GearRentPro.view;

import com.GearRentPro.controller.EquipmentController;
import com.GearRentPro.dto.EquipmentDTO;
import com.GearRentPro.controller.CategoryController;
import com.GearRentPro.controller.BranchController;
import com.GearRentPro.dto.CategoryDTO;
import com.GearRentPro.dto.BranchDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EquipmentManagementPanel extends JPanel {
    private JTable tblEquipment;
    private DefaultTableModel tableModel;
    private JTextField txtEquipmentId, txtBrand, txtModel, txtPurchaseYear;
    private JTextField txtBaseDailyPrice, txtSecurityDeposit;
    private JComboBox<String> cmbCategory, cmbBranch, cmbStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh;
    private EquipmentController equipmentController;
    private CategoryController categoryController;
    private BranchController branchController;
    
    public EquipmentManagementPanel() {
        equipmentController = new EquipmentController();
        categoryController = new CategoryController();
        branchController = new BranchController();
        initializeUI();
        loadCategories();
        loadBranches();
        loadAllEquipment();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Equipment Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Equipment ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Equipment ID:"), gbc);
        
        gbc.gridx = 1;
        txtEquipmentId = new JTextField(20);
        txtEquipmentId.setEditable(false);
        formPanel.add(txtEquipmentId, gbc);
        
        // Brand
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Brand:"), gbc);
        
        gbc.gridx = 1;
        txtBrand = new JTextField(20);
        formPanel.add(txtBrand, gbc);
        
        // Model
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Model:"), gbc);
        
        gbc.gridx = 1;
        txtModel = new JTextField(20);
        formPanel.add(txtModel, gbc);
        
        // Purchase Year
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Purchase Year:"), gbc);
        
        gbc.gridx = 1;
        txtPurchaseYear = new JTextField(20);
        formPanel.add(txtPurchaseYear, gbc);
        
        // Category
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Category:"), gbc);
        
        gbc.gridx = 1;
        cmbCategory = new JComboBox<>();
        formPanel.add(cmbCategory, gbc);
        
        // Branch
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Branch:"), gbc);
        
        gbc.gridx = 1;
        cmbBranch = new JComboBox<>();
        formPanel.add(cmbBranch, gbc);
        
        // Base Daily Price
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Daily Price:"), gbc);
        
        gbc.gridx = 1;
        txtBaseDailyPrice = new JTextField(20);
        formPanel.add(txtBaseDailyPrice, gbc);
        
        // Security Deposit
        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(new JLabel("Security Deposit:"), gbc);
        
        gbc.gridx = 1;
        txtSecurityDeposit = new JTextField(20);
        formPanel.add(txtSecurityDeposit, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 8;
        formPanel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1;
        String[] statuses = {"AVAILABLE", "RENTED", "MAINTENANCE", "UNAVAILABLE"};
        cmbStatus = new JComboBox<>(statuses);
        formPanel.add(cmbStatus, gbc);
        
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
        
        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        
        // Center Panel - Table
        String[] columns = {"ID", "Brand", "Model", "Category", "Branch", "Price", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblEquipment = new JTable(tableModel);
        tblEquipment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblEquipment.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedEquipment();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tblEquipment);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Equipment List"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Action Listeners
        btnAdd.addActionListener(e -> addEquipment());
        btnUpdate.addActionListener(e -> updateEquipment());
        btnDelete.addActionListener(e -> deleteEquipment());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadAllEquipment());
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }
    
    private void loadCategories() {
        try {
            cmbCategory.removeAllItems();
            List<CategoryDTO> categories = categoryController.getAllCategories();
            for (CategoryDTO category : categories) {
                cmbCategory.addItem(category.getCategoryId() + " - " + category.getName());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading categories: " + e.getMessage());
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
    
    private void loadAllEquipment() {
        try {
            tableModel.setRowCount(0);
            List<EquipmentDTO> equipmentList = equipmentController.getAllEquipment();
            
            for (EquipmentDTO equipment : equipmentList) {
                tableModel.addRow(new Object[]{
                    equipment.getEquipmentId(),
                    equipment.getBrand(),
                    equipment.getModel(),
                    equipment.getCategoryId(),
                    equipment.getBranchId(),
                    String.format("$%.2f", equipment.getBaseDailyPrice()),
                    equipment.getStatus()
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading equipment: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSelectedEquipment() {
        int selectedRow = tblEquipment.getSelectedRow();
        if (selectedRow >= 0) {
            txtEquipmentId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtBrand.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtModel.setText(tableModel.getValueAt(selectedRow, 2).toString());
            
            // Set category combobox
            String categoryId = tableModel.getValueAt(selectedRow, 3).toString();
            for (int i = 0; i < cmbCategory.getItemCount(); i++) {
                if (cmbCategory.getItemAt(i).startsWith(categoryId)) {
                    cmbCategory.setSelectedIndex(i);
                    break;
                }
            }
            
            // Set branch combobox
            String branchId = tableModel.getValueAt(selectedRow, 4).toString();
            for (int i = 0; i < cmbBranch.getItemCount(); i++) {
                if (cmbBranch.getItemAt(i).startsWith(branchId)) {
                    cmbBranch.setSelectedIndex(i);
                    break;
                }
            }
            
            // Parse price (remove $ sign)
            String priceStr = tableModel.getValueAt(selectedRow, 5).toString();
            priceStr = priceStr.replace("$", "");
            txtBaseDailyPrice.setText(priceStr);
            
            cmbStatus.setSelectedItem(tableModel.getValueAt(selectedRow, 6).toString());
        }
    }
    
    private void addEquipment() {
        try {
            // Generate equipment ID
            String equipmentId = "EQ" + System.currentTimeMillis();
            String brand = txtBrand.getText().trim();
            String model = txtModel.getText().trim();
            int purchaseYear = Integer.parseInt(txtPurchaseYear.getText().trim());
            double dailyPrice = Double.parseDouble(txtBaseDailyPrice.getText().trim());
            double securityDeposit = Double.parseDouble(txtSecurityDeposit.getText().trim());
            String status = cmbStatus.getSelectedItem().toString();
            
            // Extract IDs from combobox selections
            String categoryFull = cmbCategory.getSelectedItem().toString();
            String branchFull = cmbBranch.getSelectedItem().toString();
            String categoryId = categoryFull.split(" - ")[0];
            String branchId = branchFull.split(" - ")[0];
            
            // Validate
            if (brand.isEmpty() || model.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!");
                return;
            }
            
            // Create DTO
            EquipmentDTO equipmentDTO = new EquipmentDTO(
                equipmentId, categoryId, brand, model, purchaseYear, 
                dailyPrice, securityDeposit, status, branchId,
                LocalDate.now(), LocalDate.now() // Default dates
            );
            
            boolean saved = equipmentController.saveEquipment(equipmentDTO);
            
            if (saved) {
                JOptionPane.showMessageDialog(this, "Equipment added successfully!");
                clearForm();
                loadAllEquipment();
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for price fields!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding equipment: " + e.getMessage());
        }
    }
    
    private void updateEquipment() {
        try {
            String equipmentId = txtEquipmentId.getText().trim();
            
            if (equipmentId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select equipment to update!");
                return;
            }
            
            String brand = txtBrand.getText().trim();
            String model = txtModel.getText().trim();
            int purchaseYear = Integer.parseInt(txtPurchaseYear.getText().trim());
            double dailyPrice = Double.parseDouble(txtBaseDailyPrice.getText().trim());
            double securityDeposit = Double.parseDouble(txtSecurityDeposit.getText().trim());
            String status = cmbStatus.getSelectedItem().toString();
            
            String categoryFull = cmbCategory.getSelectedItem().toString();
            String branchFull = cmbBranch.getSelectedItem().toString();
            String categoryId = categoryFull.split(" - ")[0];
            String branchId = branchFull.split(" - ")[0];
            
            // Create DTO
            EquipmentDTO equipmentDTO = new EquipmentDTO(
                equipmentId, categoryId, brand, model, purchaseYear, 
                dailyPrice, securityDeposit, status, branchId,
                LocalDate.now(), LocalDate.now() // Using current dates for now
            );
            
            boolean updated = equipmentController.updateEquipment(equipmentDTO);
            
            if (updated) {
                JOptionPane.showMessageDialog(this, "Equipment updated successfully!");
                clearForm();
                loadAllEquipment();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating equipment: " + e.getMessage());
        }
    }
    
    private void deleteEquipment() {
        try {
            String equipmentId = txtEquipmentId.getText().trim();
            
            if (equipmentId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select equipment to delete!");
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this equipment?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = equipmentController.deleteEquipment(equipmentId);
                
                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Equipment deleted successfully!");
                    clearForm();
                    loadAllEquipment();
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error deleting equipment: " + e.getMessage());
        }
    }
    
    private void clearForm() {
        txtEquipmentId.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtPurchaseYear.setText("");
        txtBaseDailyPrice.setText("");
        txtSecurityDeposit.setText("");
        cmbCategory.setSelectedIndex(0);
        cmbBranch.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
        tblEquipment.clearSelection();
    }
}