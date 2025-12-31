// src/com/GearRentPro/enums/UserRole.java
package com.GearRentPro.enums;

public enum UserRole {
    ADMIN("Administrator", "Full system access"),
    MANAGER("Manager", "Manage rentals, customers, and equipment"),
    STAFF("Staff", "Process rentals and returns"),
    CASHIER("Cashier", "Handle payments only");
    
    private final String displayName;
    private final String description;
    
    UserRole(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static UserRole fromString(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.name().equalsIgnoreCase(role) || 
                userRole.getDisplayName().equalsIgnoreCase(role)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Invalid user role: " + role);
    }
    
    public static String[] getAllRoles() {
        UserRole[] roles = UserRole.values();
        String[] roleNames = new String[roles.length];
        for (int i = 0; i < roles.length; i++) {
            roleNames[i] = roles[i].getDisplayName();
        }
        return roleNames;
    }
}