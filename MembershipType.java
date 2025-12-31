// src/com/GearRentPro/enums/MembershipType.java
package com.GearRentPro.enums;

public enum MembershipType {
    STANDARD("Standard", 100.00, 0.05, "Basic membership with 5% discount"),
    PREMIUM("Premium", 200.00, 0.10, "Premium membership with 10% discount"),
    GOLD("Gold", 350.00, 0.15, "Gold membership with 15% discount"),
    CORPORATE("Corporate", 500.00, 0.20, "Corporate membership with 20% discount");
    
    private final String displayName;
    private final double annualFee;
    private final double discountRate;
    private final String description;
    
    MembershipType(String displayName, double annualFee, double discountRate, String description) {
        this.displayName = displayName;
        this.annualFee = annualFee;
        this.discountRate = discountRate;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public double getAnnualFee() {
        return annualFee;
    }
    
    public double getDiscountRate() {
        return discountRate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public double calculateDiscount(double amount) {
        return amount * discountRate;
    }
    
    public static MembershipType fromString(String type) {
        for (MembershipType membershipType : MembershipType.values()) {
            if (membershipType.name().equalsIgnoreCase(type) || 
                membershipType.getDisplayName().equalsIgnoreCase(type)) {
                return membershipType;
            }
        }
        throw new IllegalArgumentException("Invalid membership type: " + type);
    }
}