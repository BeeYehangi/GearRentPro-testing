// src/com/GearRentPro/enums/NotificationType.java
package com.GearRentPro.enums;

public enum NotificationType {
    RENTAL_REMINDER("Rental Reminder", "Reminder about upcoming rental return"),
    OVERDUE_ALERT("Overdue Alert", "Alert for overdue rentals"),
    PAYMENT_REMINDER("Payment Reminder", "Reminder for pending payments"),
    RESERVATION_CONFIRMATION("Reservation Confirmation", "Confirmation of reservation"),
    MAINTENANCE_ALERT("Maintenance Alert", "Equipment maintenance reminder"),
    SECURITY_ALERT("Security Alert", "Security-related notification"),
    SYSTEM_ALERT("System Alert", "System maintenance or updates");
    
    private final String displayName;
    private final String description;
    
    NotificationType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static NotificationType fromString(String type) {
        for (NotificationType notificationType : NotificationType.values()) {
            if (notificationType.name().equalsIgnoreCase(type) || 
                notificationType.getDisplayName().equalsIgnoreCase(type)) {
                return notificationType;
            }
        }
        throw new IllegalArgumentException("Invalid notification type: " + type);
    }
}