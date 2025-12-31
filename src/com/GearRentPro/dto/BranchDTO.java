// BranchDTO.java
package com.GearRentPro.dto;

public class BranchDTO extends SuperDTO {
    private String branchId;
    private String name;
    private String address;
    private String contact;
    private String managerId;
    private boolean status;
    
    public BranchDTO() {}
    
    public BranchDTO(String branchId, String name, String address, String contact, 
                     String managerId, boolean status) {
        this.branchId = branchId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.managerId = managerId;
        this.status = status;
    }
    
    // Getters and Setters (same as Branch entity)
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
    
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    
    @Override
    public String toString() {
        return "BranchDTO{" + "branchId=" + branchId + ", name=" + name + 
               ", address=" + address + ", contact=" + contact + 
               ", managerId=" + managerId + '}';
    }
}