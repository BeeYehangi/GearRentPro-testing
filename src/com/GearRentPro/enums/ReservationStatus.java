// src/com/GearRentPro/enums/ReservationStatus.java
package com.GearRentPro.enums;

public enum ReservationStatus {
    PENDING("Pending", "#f39c12", "Awaiting confirmation"),
    CONFIRMED("Confirmed", "#3498db", "Reservation confirmed"),
    CANCELLED("Cancelled", "#95a5a6", "Cancelled by customer"),
    NO_SHOW("No Show", "#e67e22", "Customer didn't show up"),
    COMPLETED("Completed", "#27ae60", "Converted to rental"),
    EXPIRED("Expired", "#7f8c8d", "Reservation expired");
    
    private final String displayName;
    private final String colorCode;
    private final String description;
    
    ReservationStatus(String displayName, String colorCode, String description) {
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
        return this == PENDING || this == CONFIRMED;
    }
    
    public static ReservationStatus fromString(String status) {
        for (ReservationStatus reservationStatus : ReservationStatus.values()) {
            if (reservationStatus.name().equalsIgnoreCase(status) || 
                reservationStatus.getDisplayName().equalsIgnoreCase(status)) {
                return reservationStatus;
            }
        }
        throw new IllegalArgumentException("Invalid reservation status: " + status);
    }
}