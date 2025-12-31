// src/com/GearRentPro/enums/EquipmentStatus.java
package com.GearRentPro.enums;

public enum EquipmentStatus {
    AVAILABLE("Available", "#27ae60", "Equipment is available for rent"),
    RENTED("Rented", "#3498db", "Currently rented out"),
    RESERVED("Reserved", "#f39c12", "Reserved for future rental"),
    MAINTENANCE("Maintenance", "#e67e22", "Under maintenance/repair"),
    UNAVAILABLE("Unavailable", "#95a5a6", "Temporarily unavailable"),
    DAMAGED("Damaged", "#e74c3c", "Damaged, needs repair"),
    RETIRED("Retired", "#7f8c8d", "Permanently removed from service");
    
    private final String displayName;
    private final String colorCode;
    private final String description;
    
    EquipmentStatus(String displayName, String colorCode, String description) {
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
    
    public boolean isAvailableForRent() {
        return this == AVAILABLE || this == RESERVED;
    }
    
    public boolean isOutOfService() {
        return this == MAINTENANCE || this == DAMAGED || this == RETIRED;
    }
    
    public static EquipmentStatus fromString(String status) {
        for (EquipmentStatus equipmentStatus : EquipmentStatus.values()) {
            if (equipmentStatus.name().equalsIgnoreCase(status) || 
                equipmentStatus.getDisplayName().equalsIgnoreCase(status)) {
                return equipmentStatus;
            }
        }
        throw new IllegalArgumentException("Invalid equipment status: " + status);
    }
}