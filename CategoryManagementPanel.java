// src/com/GearRentPro/view/CategoryManagementPanel.java
package com.GearRentPro.view;

import com.GearRentPro.controller.CategoryController;
import com.GearRentPro.dto.CategoryDTO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CategoryManagementPanel extends JPanel {
    private JTable tblCategories;
    private DefaultTableModel tableModel;
    private JTextField txtCategoryId, txtName, txtDescription;
    private JCheckBox chkStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnRefresh;
    private CategoryController categoryController;
    
    public CategoryManagementPanel() {
        categoryController = new CategoryController();
        initializeUI();
        loadAllCategories();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Category Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Category ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Category ID:"), gbc);
        
        gbc.gridx = 1;
        txtCategoryId = new JTextField(20);
        txtCategoryId.setEditable(false);
        formPanel.add(txtCategoryId, gbc);
        
        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Category Name:"), gbc);
        
        gbc.gridx = 1;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);
        
        // Description
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Description:"), gbc);
        
        gbc.gridx = 1;
        txtDescription = new JTextField(20);
        formPanel.add(txtDescription, gbc);
        
        // Status
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Status:"), gbc);
        
        gbc.gridx = 1;
        chkStatus = new JCheckBox("Active");
        chkStatus.setSelected(true);
        formPanel.add(chkStatus, gbc);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnAdd = new JButton("Add Category");
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
        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(formPanel, BorderLayout.NORTH);
        
        // Center Panel - Table
        String[] columns = {"Category ID", "Name", "Description", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblCategories = new JTable(tableModel);
        tblCategories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblCategories.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                loadSelectedCategory();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tblCategories);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Categories List"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Action Listeners
        btnAdd.addActionListener(e -> addCategory());
        btnUpdate.addActionListener(e -> updateCategory());
        btnDelete.addActionListener(e -> deleteCategory());
        btnClear.addActionListener(e -> clearForm());
        btnRefresh.addActionListener(e -> loadAllCategories());
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }
    
    private void loadAllCategories() {
        try {
            tableModel.setRowCount(0);
            List<CategoryDTO> categories = categoryController.getAllCategories();
            
            for (CategoryDTO category : categories) {
                tableModel.addRow(new Object[]{
                    category.getCategoryId(),
                    category.getName(),
                    category.getDescription(),
                    category.isStatus() ? "Active" : "Inactive"
                });
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading categories: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadSelectedCategory() {
        int selectedRow = tblCategories.getSelectedRow();
        if (selectedRow >= 0) {
            txtCategoryId.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtName.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtDescription.setText(tableModel.getValueAt(selectedRow, 2).toString());
            chkStatus.setSelected(tableModel.getValueAt(selectedRow, 3).equals("Active"));
        }
    }
    
    private void addCategory() {
        try {
            String categoryId = "CAT" + String.format("%03d", (tableModel.getRowCount() + 1));
            String name = txtName.getText().trim();
            String description = txtDescription.getText().trim();
            boolean status = chkStatus.isSelected();
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter category name!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Check if category name already exists
            // In real app, you would check database
            
            CategoryDTO categoryDTO = new CategoryDTO(categoryId, name, description, status);
            boolean saved = categoryController.saveCategory(categoryDTO);
            
            if (saved) {
                JOptionPane.showMessageDialog(this, 
                    "Category added successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllCategories();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error adding category: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateCategory() {
        try {
            String categoryId = txtCategoryId.getText().trim();
            
            if (categoryId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a category to update!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String name = txtName.getText().trim();
            String description = txtDescription.getText().trim();
            boolean status = chkStatus.isSelected();
            
            CategoryDTO categoryDTO = new CategoryDTO(categoryId, name, description, status);
            boolean updated = categoryController.updateCategory(categoryDTO);
            
            if (updated) {
                JOptionPane.showMessageDialog(this, 
                    "Category updated successfully!", 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
                loadAllCategories();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error updating category: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteCategory() {
        try {
            String categoryId = txtCategoryId.getText().trim();
            
            if (categoryId.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please select a category to delete!", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Check if category has equipment
            boolean inUse = categoryController.isCategoryInUse(categoryId);
            if (inUse) {
                JOptionPane.showMessageDialog(this, 
                    "Cannot delete category! It is being used by equipment.", 
                    "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this category?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = categoryController.deleteCategory(categoryId);
                
                if (deleted) {
                    JOptionPane.showMessageDialog(this, 
                        "Category deleted successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearForm();
                    loadAllCategories();
                }
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error deleting category: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        txtCategoryId.setText("");
        txtName.setText("");
        txtDescription.setText("");
        chkStatus.setSelected(true);
        tblCategories.clearSelection();
    }
}\\\\''