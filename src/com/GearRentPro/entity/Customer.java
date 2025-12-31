/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GearRentPro.entity;

/**
 *
 * @author MCS
 */
public class Customer implements SuperEntity {
    private String customerId;
    private String name;
    private String nicPassport;
    private String contactNo;
    private String email;
    private String address;
    private String membershipLevel;
    private boolean status;
    
    public Customer() {}
    
    public Customer(String customerId, String name, String nicPassport, 
                   String contactNo, String email, String address, 
                   String membershipLevel, boolean status) {
        this.customerId = customerId;
        this.name = name;
        this.nicPassport = nicPassport;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.membershipLevel = membershipLevel;
        this.status = status;
    }
    
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getNicPassport() { return nicPassport; }
    public void setNicPassport(String nicPassport) { this.nicPassport = nicPassport; }
    
    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getMembershipLevel() { return membershipLevel; }
    public void setMembershipLevel(String membershipLevel) { this.membershipLevel = membershipLevel; }
    
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    
    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", name=" + name + 
               ", nicPassport=" + nicPassport + '}';
    }
}
