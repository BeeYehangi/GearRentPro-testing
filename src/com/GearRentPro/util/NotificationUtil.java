// src/com/GearRentPro/util/NotificationUtil.java
package com.GearRentPro.util;

import javax.swing.*;
import java.awt.*;

public class NotificationUtil {
    
    // Success message
    public static void showSuccess(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Success", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Error message
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
    
    // Warning message
    public static void showWarning(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Warning", 
            JOptionPane.WARNING_MESSAGE);
    }
    
    // Confirmation dialog
    public static boolean showConfirmation(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, 
            "Confirmation", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
    
    // Input dialog
    public static String showInputDialog(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message);
    }
    
    // Custom notification with icon
    public static void showCustomNotification(Component parent, String title, 
                                            String message, int messageType) {
        JOptionPane.showMessageDialog(parent, message, title, messageType);
    }
    
    // Show about dialog
    public static void showAboutDialog(Component parent) {
        String aboutText = 
            "GearRent Pro v1.0\n" +
            "================\n" +
            "Equipment Rental Management System\n" +
            "Developed for efficient gear rental operations\n" +
            "\n" +
            "Features:\n" +
            "• User Management\n" +
            "• Equipment Tracking\n" +
            "• Reservation System\n" +
            "• Rental Processing\n" +
            "• Membership Management\n" +
            "• Reporting & Analytics\n" +
            "\n" +
            "© 2024 GearRent Pro. All rights reserved.";
        
        JOptionPane.showMessageDialog(parent, aboutText, "About GearRent Pro", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Show system information
    public static void showSystemInfo(Component parent) {
        String systemInfo = 
            "System Information\n" +
            "==================\n" +
            "Java Version: " + System.getProperty("java.version") + "\n" +
            "Java Home: " + System.getProperty("java.home") + "\n" +
            "OS: " + System.getProperty("os.name") + " " + 
                 System.getProperty("os.version") + "\n" +
            "User: " + System.getProperty("user.name") + "\n" +
            "Working Directory: " + System.getProperty("user.dir");
        
        JTextArea textArea = new JTextArea(systemInfo);
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        
        JOptionPane.showMessageDialog(parent, scrollPane, "System Information", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}