// src/com/GearRentPro/view/QuickActionPanel.java
package com.GearRentPro.view;

import javax.swing.*;
import java.awt.*;

public class QuickActionPanel extends JPanel {
    
    public QuickActionPanel() {
        setLayout(new GridLayout(2, 4, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Create quick action buttons
        add(createQuickActionButton("New Rental", "🛒", Color.GREEN, 
            e -> JOptionPane.showMessageDialog(this, "Open New Rental Form")));
        
        add(createQuickActionButton("New Reservation", "📅", Color.BLUE, 
            e -> JOptionPane.showMessageDialog(this, "Open New Reservation Form")));
        
        add(createQuickActionButton("Return Equipment", "↪️", Color.ORANGE, 
            e -> JOptionPane.showMessageDialog(this, "Open Return Form")));
        
        add(createQuickActionButton("New Customer", "👤", Color.MAGENTA, 
            e -> JOptionPane.showMessageDialog(this, "Open New Customer Form")));
        
        add(createQuickActionButton("Add Equipment", "📷", Color.CYAN, 
            e -> JOptionPane.showMessageDialog(this, "Open New Equipment Form")));
        
        add(createQuickActionButton("Today's Report", "📊", new Color(75, 0, 130), 
            e -> JOptionPane.showMessageDialog(this, "Generate Today's Report")));
        
        add(createQuickActionButton("Check Availability", "✅", new Color(0, 100, 0), 
            e -> JOptionPane.showMessageDialog(this, "Check Equipment Availability")));
        
        add(createQuickActionButton("Quick Search", "🔍", Color.DARK_GRAY, 
            e -> JOptionPane.showMessageDialog(this, "Open Quick Search")));
    }
    
    private JButton createQuickActionButton(String text, String icon, Color color, java.awt.event.ActionListener action) {
        JButton button = new JButton("<html><center><font size='5'>" + icon + 
                                   "</font><br>" + text + "</center></html>");
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 5));
        button.addActionListener(action);
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
}