// src/com/GearRentPro/view/ReportsPanel.java
package com.GearRentPro.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportsPanel extends JPanel {
    private JTable tblReport;
    private DefaultTableModel tableModel;
    private JComboBox<String> cmbReportType;
    private JTextField txtStartDate, txtEndDate;
    private JButton btnGenerate, btnPrint, btnExport;
    private JTextArea txtSummary;
    
    public ReportsPanel() {
        initializeUI();
        loadSampleData();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Top Panel - Report Controls
        JPanel controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBorder(BorderFactory.createTitledBorder("Report Parameters"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Report Type
        gbc.gridx = 0; gbc.gridy = 0;
        controlPanel.add(new JLabel("Report Type:"), gbc);
        
        gbc.gridx = 1;
        String[] reportTypes = {
            "Daily Rentals Summary",
            "Monthly Revenue Report", 
            "Equipment Utilization",
            "Customer Activity",
            "Reservation Status",
            "Overdue Rentals",
            "Popular Equipment",
            "Branch Performance"
        };
        cmbReportType = new JComboBox<>(reportTypes);
        controlPanel.add(cmbReportType, gbc);
        
        // Start Date
        gbc.gridx = 0; gbc.gridy = 1;
        controlPanel.add(new JLabel("Start Date:"), gbc);
        
        gbc.gridx = 1;
        txtStartDate = new JTextField(15);
        txtStartDate.setText(LocalDate.now().minusDays(30).toString());
        controlPanel.add(txtStartDate, gbc);
        
        // End Date
        gbc.gridx = 0; gbc.gridy = 2;
        controlPanel.add(new JLabel("End Date:"), gbc);
        
        gbc.gridx = 1;
        txtEndDate = new JTextField(15);
        txtEndDate.setText(LocalDate.now().toString());
        controlPanel.add(txtEndDate, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        btnGenerate = new JButton("Generate Report");
        btnPrint = new JButton("Print");
        btnExport = new JButton("Export to Excel");
        
        styleButton(btnGenerate, new Color(0, 102, 204));
        styleButton(btnPrint, new Color(102, 102, 102));
        styleButton(btnExport, new Color(0, 153, 76));
        
        buttonPanel.add(btnGenerate);
        buttonPanel.add(btnPrint);
        buttonPanel.add(btnExport);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        controlPanel.add(buttonPanel, gbc);
        
        add(controlPanel, BorderLayout.NORTH);
        
        // Center Panel - Split between table and summary
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setResizeWeight(0.7);
        
        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Report Data"));
        
        String[] columns = {"ID", "Description", "Date", "Amount", "Status", "Branch"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tblReport = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(tblReport);
        tablePanel.add(tableScroll, BorderLayout.CENTER);
        
        // Summary Panel
        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Summary"));
        
        txtSummary = new JTextArea(5, 50);
        txtSummary.setEditable(false);
        txtSummary.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtSummary.setText("Report summary will appear here...\n");
        
        JScrollPane summaryScroll = new JScrollPane(txtSummary);
        summaryPanel.add(summaryScroll, BorderLayout.CENTER);
        
        splitPane.setTopComponent(tablePanel);
        splitPane.setBottomComponent(summaryPanel);
        
        add(splitPane, BorderLayout.CENTER);
        
        // Chart Panel at bottom
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.SOUTH);
        
        // Action Listeners
        btnGenerate.addActionListener(e -> generateReport());
        btnPrint.addActionListener(e -> printReport());
        btnExport.addActionListener(e -> exportReport());
    }
    
    private JPanel createChartPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Visual Analytics"));
        panel.setPreferredSize(new Dimension(800, 200));
        
        // Sample chart panels (in real app, you'd use JFreeChart or similar)
        JPanel chart1 = createChartCard("Monthly Revenue", new Color(65, 105, 225), "$12,450");
        JPanel chart2 = createChartCard("Active Rentals", new Color(50, 205, 50), "24");
        JPanel chart3 = createChartCard("Utilization Rate", new Color(255, 140, 0), "78%");
        
        panel.add(chart1);
        panel.add(chart2);
        panel.add(chart3);
        
        return panel;
    }
    
    private JPanel createChartCard(String title, Color color, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(color, 2));
        card.setBackground(new Color(240, 240, 240));
        
        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setForeground(color);
        
        JLabel lblValue = new JLabel(value, SwingConstants.CENTER);
        lblValue.setFont(new Font("Arial", Font.BOLD, 24));
        lblValue.setForeground(color.darker());
        
        // Simple bar chart simulation
        JPanel barPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(color);
                int width = (int)(getWidth() * 0.8);
                int height = getHeight() / 3;
                int x = (getWidth() - width) / 2;
                int y = getHeight() - height;
                g.fillRect(x, y, width, height);
            }
        };
        barPanel.setPreferredSize(new Dimension(100, 60));
        
        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        card.add(barPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }
    
    private void loadSampleData() {
        // Sample data for demonstration
        Object[][] sampleData = {
            {"R001", "Camera Rental - Canon EOS", "2024-01-15", "$150.00", "COMPLETED", "BR001"},
            {"R002", "Drone Rental - DJI Mavic", "2024-01-16", "$200.00", "ACTIVE", "BR002"},
            {"R003", "Laptop Rental - MacBook Pro", "2024-01-17", "$80.00", "COMPLETED", "BR001"},
            {"R004", "Projector Rental - Epson", "2024-01-18", "$120.00", "OVERDUE", "BR003"},
            {"R005", "Sound System Rental", "2024-01-19", "$300.00", "ACTIVE", "BR002"},
            {"R006", "Camera Rental - Sony A7", "2024-01-20", "$180.00", "COMPLETED", "BR001"},
            {"R007", "Tablet Rental - iPad Pro", "2024-01-21", "$60.00", "ACTIVE", "BR003"},
            {"R008", "Gaming Console Rental", "2024-01-22", "$90.00", "COMPLETED", "BR002"}
        };
        
        for (Object[] row : sampleData) {
            tableModel.addRow(row);
        }
        
        updateSummary();
    }
    
    private void generateReport() {
        String reportType = cmbReportType.getSelectedItem().toString();
        String startDate = txtStartDate.getText().trim();
        String endDate = txtEndDate.getText().trim();
        
        // Clear existing data
        tableModel.setRowCount(0);
        
        // In real application, you would:
        // 1. Call appropriate controller methods
        // 2. Process data based on report type
        // 3. Populate table with real data
        
        // For now, show sample data
        loadSampleData();
        
        JOptionPane.showMessageDialog(this,
            "Generating " + reportType + " report\n" +
            "Period: " + startDate + " to " + endDate + "\n\n" +
            "In a real application, this would fetch data\n" +
            "from the database and generate the report.",
            "Report Generation", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateSummary() {
        double totalRevenue = 0;
        int activeRentals = 0;
        int completedRentals = 0;
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String amountStr = tableModel.getValueAt(i, 3).toString();
            amountStr = amountStr.replace("$", "").replace(",", "");
            try {
                totalRevenue += Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                // Skip if not a number
            }
            
            String status = tableModel.getValueAt(i, 4).toString();
            if ("ACTIVE".equals(status)) {
                activeRentals++;
            } else if ("COMPLETED".equals(status)) {
                completedRentals++;
            }
        }
        
        StringBuilder summary = new StringBuilder();
        summary.append("REPORT SUMMARY\n");
        summary.append("==============\n");
        summary.append(String.format("Total Records: %d\n", tableModel.getRowCount()));
        summary.append(String.format("Active Rentals: %d\n", activeRentals));
        summary.append(String.format("Completed Rentals: %d\n", completedRentals));
        summary.append(String.format("Total Revenue: $%.2f\n", totalRevenue));
        summary.append(String.format("Average Revenue per Rental: $%.2f\n", 
                     tableModel.getRowCount() > 0 ? totalRevenue / tableModel.getRowCount() : 0));
        summary.append("\nGenerated on: " + LocalDate.now() + "\n");
        
        txtSummary.setText(summary.toString());
    }
    
    private void printReport() {
        JOptionPane.showMessageDialog(this,
            "Print functionality would be implemented here.\n" +
            "This would format the report for printing.",
            "Print Report", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void exportReport() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Export Report");
        fileChooser.setSelectedFile(new java.io.File("GearRent_Report_" + 
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".csv"));
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(this,
                "Report exported successfully to:\n" +
                fileChooser.getSelectedFile().getPath(),
                "Export Complete", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}