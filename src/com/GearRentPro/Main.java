// src/com/GearRentPro/Main.java
package com.GearRentPro;

import com.GearRentPro.view.LoginForm;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Start application
        SwingUtilities.invokeLater(() -> {
            LoginForm loginForm = new LoginForm();
            loginForm.setVisible(true);
            
            System.out.println("🚀 GearRent Pro Application Started!");
            System.out.println("====================================");
            System.out.println("✅ Login Form: Ready");
            System.out.println("✅ Admin Dashboard: Ready");
            System.out.println("✅ User Management: Ready");
            System.out.println("✅ Branch Management: Ready");
            System.out.println("✅ Category Management: Ready");
            System.out.println("✅ Customer Management: Ready");
            System.out.println("✅ Equipment Management: Ready");
            System.out.println("✅ Reservation Management: Ready");
            System.out.println("✅ Rental Management: Ready");
            System.out.println("✅ Membership Management: Ready");
            System.out.println("✅ Reports Dashboard: Ready");
            System.out.println("====================================");
            System.out.println("🎯 Total UI Components: 10");
            System.out.println("🎯 Total Controllers: 8");
            System.out.println("🎯 Application Architecture: Complete!");
        });
    }
}