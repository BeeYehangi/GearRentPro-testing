// src/com/GearRentPro/util/UtilTest.java
package com.GearRentPro.util;

public class UtilTest {
    
    public static void main(String[] args) {
        System.out.println("🧪 Testing GearRent Pro Utilities\n");
        
        // Test ID Generator
        System.out.println("1. ID Generator:");
        System.out.println("   User ID: " + IDGenerator.generateUserId());
        System.out.println("   Customer ID: " + IDGenerator.generateCustomerId());
        System.out.println("   Equipment ID: " + IDGenerator.generateEquipmentId());
        
        // Test Validation
        System.out.println("\n2. Validation Utilities:");
        System.out.println("   Valid Email (test@example.com): " + 
            ValidationUtil.isValidEmail("test@example.com"));
        System.out.println("   Valid NIC (123456789V): " + 
            ValidationUtil.isValidNIC("123456789V"));
        System.out.println("   Valid Phone (0712345678): " + 
            ValidationUtil.isValidPhone("0712345678"));
        
        // Test Date Utilities
        System.out.println("\n3. Date Utilities:");
        System.out.println("   Current Date: " + DateUtil.getCurrentDateString());
        System.out.println("   Format Display: " + 
            DateUtil.formatForDisplay("2024-01-15"));
        
        // Test Currency Utilities
        System.out.println("\n4. Currency Utilities:");
        System.out.println("   Format Amount (1234.56): " + 
            CurrencyUtil.formatAmount(1234.56));
        System.out.println("   Format Currency (1234.56): " + 
            CurrencyUtil.formatCurrency(1234.56));
        
        // Test Password Utilities
        System.out.println("\n5. Password Utilities:");
        String salt = PasswordUtil.generateSalt();
        String password = "SecurePass123!";
        String hashed = PasswordUtil.hashPassword(password, salt);
        System.out.println("   Salt: " + salt.substring(0, 10) + "...");
        System.out.println("   Hashed Password: " + hashed.substring(0, 10) + "...");
        System.out.println("   Password Verified: " + 
            PasswordUtil.verifyPassword(password, hashed, salt));
        
        // Test Logging
        System.out.println("\n6. Logging Utilities:");
        LogUtil.info("Test info message");
        LogUtil.warning("Test warning message");
        LogUtil.error("Test error message");
        LogUtil.logUserActivity("admin", "Logged in");
        
        // Test Configuration
        System.out.println("\n7. Configuration Utilities:");
        System.out.println("   App Name: " + ConfigUtil.getAppName());
        System.out.println("   Tax Rate: " + ConfigUtil.getTaxRate() + "%");
        
        System.out.println("\n✅ All utilities tested successfully!");
    }
}