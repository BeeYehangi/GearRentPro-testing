// src/com/GearRentPro/enums/DiscountType.java
package com.GearRentPro.enums;

public enum DiscountType {
    MEMBERSHIP("Membership Discount", "Discount for membership holders"),
    LONG_TERM("Long Term Rental", "Discount for extended rentals"),
    FIRST_TIME("First Time Customer", "Discount for new customers"),
    PROMOTIONAL("Promotional", "Special promotion discount"),
    SEASONAL("Seasonal", "Seasonal discount"),
    VOLUME("Volume Discount", "Discount for bulk rentals"),
    LOYALTY("Loyalty", "Loyalty reward discount");
    
    private final String displayName;
    private final String description;
    
    DiscountType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static DiscountType fromString(String type) {
        for (DiscountType discountType : DiscountType.values()) {
            if (discountType.name().equalsIgnoreCase(type) || 
                discountType.getDisplayName().equalsIgnoreCase(type)) {
                return discountType;
            }
        }
        throw new IllegalArgumentException("Invalid discount type: " + type);
    }
}