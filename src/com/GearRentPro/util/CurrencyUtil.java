// src/com/GearRentPro/util/CurrencyUtil.java
package com.GearRentPro.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {
    
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00");
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
    
    // Format amount with 2 decimal places
    public static String formatAmount(double amount) {
        return DECIMAL_FORMAT.format(amount);
    }
    
    // Format as currency ($)
    public static String formatCurrency(double amount) {
        return CURRENCY_FORMAT.format(amount);
    }
    
    // Format as currency without symbol
    public static String formatCurrencyNoSymbol(double amount) {
        return DECIMAL_FORMAT.format(amount);
    }
    
    // Parse string to double
    public static double parseAmount(String amountStr) {
        try {
            // Remove any non-numeric characters except decimal point
            String cleaned = amountStr.replaceAll("[^\\d.-]", "");
            return Double.parseDouble(cleaned);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    // Calculate discount amount
    public static double calculateDiscount(double originalPrice, double discountPercentage) {
        return originalPrice * (discountPercentage / 100);
    }
    
    // Calculate final amount after discount
    public static double calculateFinalAmount(double originalPrice, double discountAmount) {
        return Math.max(0, originalPrice - discountAmount);
    }
    
    // Calculate tax amount (assuming 8% tax)
    public static double calculateTax(double amount) {
        return amount * 0.08;
    }
    
    // Calculate total with tax
    public static double calculateTotalWithTax(double amount) {
        return amount + calculateTax(amount);
    }
}