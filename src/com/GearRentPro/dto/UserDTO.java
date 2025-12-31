// src/com/gearrent/dto/UserDTO.java
package com.GearRentPro.dto;

import com.GearRentPro.enums.UserRole;

public class UserDTO {
    private String userId;
    private String username;
    private String password;
    private UserRole role;
    private String branchId;
    private boolean status;
    
    // Constructors
    public UserDTO() {}
    
    public UserDTO(String userId, String username, UserRole role, String branchId) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.branchId = branchId;
    }
    
    public UserDTO(String userId, String username, String password, UserRole role, String branchId, boolean status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.branchId = branchId;
        this.status = status;
    }
    
    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
    
    public String getBranchId() { return branchId; }
    public void setBranchId(String branchId) { this.branchId = branchId; }
    
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    
    @Override
    public String toString() {
        return "UserDTO{" + "userId=" + userId + ", username=" + username + 
               ", role=" + role + ", branchId=" + branchId + '}';
    }
}