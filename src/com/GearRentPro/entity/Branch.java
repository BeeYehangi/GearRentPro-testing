package com.GearRentPro.entity;

public class Branch implements SuperEntity {
    private String branchId;
    private String name;
    private String address;
    private String contact;
    private String managerId;
    private boolean status;
    
    public Branch() {}
    
    // Updated constructor with status parameter
    public Branch(String branchId, String name, String address, String contact, 
                  String managerId, boolean status) {
        this.branchId = branchId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.managerId = managerId;
        this.status = status;
    }
    
    // Getters and Setters
    public String getBranchId() { return branchId; }
    public void setBranchId(String branchId) { this.branchId = branchId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    
    public String getManagerId() { return managerId; }
    public void setManagerId(String managerId) { this.managerId = managerId; }
    
    // Add these missing methods:
    public boolean isStatus() { 
        return status; 
    }
    
    public void setStatus(boolean status) { 
        this.status = status; 
    }
    
    @Override
    public String toString() {
        return "Branch{" + "branchId=" + branchId + ", name=" + name + 
               ", address=" + address + ", contact=" + contact + 
               ", managerId=" + managerId + ", status=" + status + '}';
    }
}