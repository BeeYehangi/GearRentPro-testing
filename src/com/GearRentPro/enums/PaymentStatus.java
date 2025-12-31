// src/com/GearRentPro/enums/PaymentStatus.java
package com.GearRentPro.enums;

public enum PaymentStatus {
    PENDING("Pending", "#f39c12", "Payment initiated but not completed"),
    PAID("Paid", "#27ae60", "Full payment received"),
    PARTIAL("Partial", "#3498db", "Partial payment received"),
    FAILED("Failed", "#e74c3c", "Payment failed/declined"),
    REFUNDED("Refunded", "#95a5a6", "Payment refunded to customer"),
    VOIDED("Voided", "#34495e", "Payment voided/cancelled");
    
    private final String displayName;
    private final String colorCode;
    private final String description;
    
    PaymentStatus(String displayName, String colorCode, String description) {
        this.displayName = displayName;
        this.colorCode = colorCode;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getColorCode() {
        return colorCode;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean isCompleted() {
        return this == PAID || this == REFUNDED;
    }
    
    public static PaymentStatus fromString(String status) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.name().equalsIgnoreCase(status) || 
                paymentStatus.getDisplayName().equalsIgnoreCase(status)) {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException("Invalid payment status: " + status);
    }
}