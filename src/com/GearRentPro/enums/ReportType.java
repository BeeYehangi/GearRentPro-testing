// src/com/GearRentPro/enums/ReportType.java
package com.GearRentPro.enums;

public enum ReportType {
    DAILY_RENTALS("Daily Rentals", "Rental activities for a specific day"),
    MONTHLY_REVENUE("Monthly Revenue", "Revenue summary for a month"),
    EQUIPMENT_UTILIZATION("Equipment Utilization", "Equipment usage statistics"),
    CUSTOMER_ACTIVITY("Customer Activity", "Customer rental history"),
    OVERDUE_RENTALS("Overdue Rentals", "List of overdue rentals"),
    POPULAR_EQUIPMENT("Popular Equipment", "Most rented equipment"),
    BRANCH_PERFORMANCE("Branch Performance", "Performance by branch"),
    PAYMENT_SUMMARY("Payment Summary", "Payment collection summary"),
    MEMBERSHIP_REPORT("Membership Report", "Membership statistics"),
    MAINTENANCE_SCHEDULE("Maintenance Schedule", "Upcoming maintenance");
    
    private final String displayName;
    private final String description;
    
    ReportType(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static ReportType fromString(String type) {
        for (ReportType reportType : ReportType.values()) {
            if (reportType.name().equalsIgnoreCase(type) || 
                reportType.getDisplayName().equalsIgnoreCase(type)) {
                return reportType;
            }
        }
        throw new IllegalArgumentException("Invalid report type: " + type);
    }
}