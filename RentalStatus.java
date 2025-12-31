// src/com/GearRentPro/enums/RentalStatus.java
package com.GearRentPro.enums;

public enum RentalStatus {
    PENDING("Pending", "#f39c12", "Rental created but not active"),
    ACTIVE("Active", "#3498db", "Equipment currently rented"),
    OVERDUE("Overdue", "#e74c3c", "Past due return date"),
    COMPLETED("Completed", "#27ae60", "Successfully returned"),
    CANCELLED("Cancelled", "#95a5a6", "Cancelled by customer/staff"),
    NO_SHOW("No Show", "#e67e22", "Reserved but never picked up"),
    EXTENDED("Extended", "#9b59b6", "Rental period extended");
    
    private final String displayName;
    private final String colorCode;
    private final String description;
    
    RentalStatus(String displayName, String colorCode, String description) {
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
    
    public boolean isActive() {
        return this == ACTIVE || this == OVERDUE || this == EXTENDED;
    }
    
    public boolean isCompleted() {
        return this == COMPLETED || this == CANCELLED;
    }
    
    public static RentalStatus fromString(String status) {
        for (RentalStatus rentalStatus : RentalStatus.values()) {
            if (rentalStatus.name().equalsIgnoreCase(status) || 
                rentalStatus.getDisplayName().equalsIgnoreCase(status)) {
                return rentalStatus;
            }
        }
        throw new IllegalArgumentException("Invalid rental status: " + status);
    }
}