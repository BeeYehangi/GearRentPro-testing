/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GearRentPro.entity;

import com.GearRentPro.enums.UserRole;
/**
 *
 * @author MCS
 */
public class User implements SuperEntity {
    private String userId;
    private String username;
    private String password;
    private UserRole role;
    private String branchId;
    private boolean status;
    
    //Constructors
    
    public User() {}
    
    public User(String userId, String username, String password, 
                UserRole role, String branchId, boolean status) {
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
        return "User{" + "userId=" + userId + ", username=" + username + 
               ", role=" + role + ", branchId=" + branchId + '}';
    } 
}

