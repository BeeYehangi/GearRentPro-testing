// src/com/GearRentPro/entity/Membership.java
package com.GearRentPro.entity;

public class Membership implements SuperEntity {
    private String membershipId;
    private String customerId;
    private String type;
    private double fee;
    private String startDate;
    private String endDate;
    private boolean status;
    
    public Membership() {}
    
    public Membership(String membershipId, String customerId, String type, double fee, 
                     String startDate, String endDate, boolean status) {
        this.membershipId = membershipId;
        this.customerId = customerId;
        this.type = type;
        this.fee = fee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    
    // Getters and Setters
    public String getMembershipId() { return membershipId; }
    public void setMembershipId(String membershipId) { this.membershipId = membershipId; }
    
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }
    
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    
    @Override
    public String toString() {
        return "Membership{" + "membershipId='" + membershipId + '\'' + ", customerId='" + customerId + '\'' + 
               ", type='" + type + '\'' + ", fee=" + fee + ", startDate='" + startDate + '\'' + 
               ", endDate='" + endDate + '\'' + ", status=" + status + '}';
    }
}