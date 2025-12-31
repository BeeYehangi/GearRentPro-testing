// src/com/GearRentPro/enums/PaymentMethod.java
package com.GearRentPro.enums;

public enum PaymentMethod {
    CASH("Cash", "Cash payment at counter"),
    CREDIT_CARD("Credit Card", "Credit/debit card payment"),
    DEBIT_CARD("Debit Card", "Debit card payment"),
    ONLINE("Online", "Online payment gateway"),
    BANK_TRANSFER("Bank Transfer", "Direct bank transfer"),
    MOBILE_WALLET("Mobile Wallet", "Mobile payment wallet"),
    CHEQUE("Cheque", "Payment by cheque");
    
    private final String displayName;
    private final String description;
    
    PaymentMethod(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static PaymentMethod fromString(String method) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.name().equalsIgnoreCase(method) || 
                paymentMethod.getDisplayName().equalsIgnoreCase(method)) {
                return paymentMethod;
            }
        }
        throw new IllegalArgumentException("Invalid payment method: " + method);
    }
}