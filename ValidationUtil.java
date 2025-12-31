// src/com/GearRentPro/util/ValidationUtil.java
package com.GearRentPro.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ValidationUtil {
    
    // Email validation
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
    
    // Phone number validation (Sri Lanka format)
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) return false;
        // Accepts formats: 0712345678, 0112345678, +94712345678
        String phoneRegex = "^(?:0|94|\\+94)?(?:7(0|1|2|4|5|6|7|8)\\d)\\d{6}$";
        return Pattern.compile(phoneRegex).matcher(phone).matches();
    }
    
    // NIC validation (old and new formats)
    public static boolean isValidNIC(String nic) {
        if (nic == null || nic.trim().isEmpty()) return false;
        
        // Old NIC: 9 digits + V/X (e.g., 123456789V)
        if (nic.length() == 10) {
            String oldNicRegex = "^[0-9]{9}[VX]$";
            return Pattern.compile(oldNicRegex).matcher(nic.toUpperCase()).matches();
        }
        
        // New NIC: 12 digits (e.g., 199912345678)
        if (nic.length() == 12) {
            String newNicRegex = "^[0-9]{12}$";
            return Pattern.compile(newNicRegex).matcher(nic).matches();
        }
        
        return false;
    }
    
    // Date validation
    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    // Check if date is future date
    public static boolean isFutureDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return date.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    // Check if date is past date
    public static boolean isPastDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return date.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    // Password strength validation
    public static boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) return false;
        
        boolean hasUpper = Pattern.compile("[A-Z]").matcher(password).find();
        boolean hasLower = Pattern.compile("[a-z]").matcher(password).find();
        boolean hasDigit = Pattern.compile("[0-9]").matcher(password).find();
        boolean hasSpecial = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]").matcher(password).find();
        
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
    
    // Amount validation (positive number)
    public static boolean isValidAmount(String amountStr) {
        try {
            double amount = Double.parseDouble(amountStr);
            return amount > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Integer validation
    public static boolean isValidInteger(String intStr) {
        try {
            Integer.parseInt(intStr);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Name validation (letters and spaces only)
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) return false;
        String nameRegex = "^[A-Za-z\\s.'-]+$";
        return Pattern.compile(nameRegex).matcher(name).matches();
    }
}