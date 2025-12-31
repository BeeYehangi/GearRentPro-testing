/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GearRentPro.entity;
import java.sql.Date;
/**
 *
 * @author MCS
 */
public class Rental implements SuperEntity {
    private String rentalId;
    private String equipmentId;
    private String customerId;
    private String branchId;
    private Date startDate;
    private Date endDate;
    private Date actualReturnDate;
    private double rentalAmount;
    private double securityDeposit;
    private double membershipDiscount;
    private double longRentalDiscount;
    private double finalPayableAmount;
    private double lateFee;
    private double damageCharge;
    private String paymentStatus;
    private String rentalStatus;
    private Date createdDate;
    
    public Rental() {}
    
    public Rental(String rentalId, String equipmentId, String customerId, 
                 String branchId, Date startDate, Date endDate, 
                 Date actualReturnDate, double rentalAmount, 
                 double securityDeposit, double membershipDiscount, 
                 double longRentalDiscount, double finalPayableAmount, 
                 double lateFee, double damageCharge, String paymentStatus, 
                 String rentalStatus, Date createdDate) {
        this.rentalId = rentalId;
        this.equipmentId = equipmentId;
        this.customerId = customerId;
        this.branchId = branchId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.actualReturnDate = actualReturnDate;
        this.rentalAmount = rentalAmount;
        this.securityDeposit = securityDeposit;
        this.membershipDiscount = membershipDiscount;
        this.longRentalDiscount = longRentalDiscount;
        this.finalPayableAmount = finalPayableAmount;
        this.lateFee = lateFee;
        this.damageCharge = damageCharge;
        this.paymentStatus = paymentStatus;
        this.rentalStatus = rentalStatus;
        this.createdDate = createdDate;
    }
    
    public String getRentalId() { return rentalId; }
    public void setRentalId(String rentalId) { this.rentalId = rentalId; }
    
    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }
    
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    
    public String getBranchId() { return branchId; }
    public void setBranchId(String branchId) { this.branchId = branchId; }
    
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    
    public Date getActualReturnDate() { return actualReturnDate; }
    public void setActualReturnDate(Date actualReturnDate) { this.actualReturnDate = actualReturnDate; }
    
    public double getRentalAmount() { return rentalAmount; }
    public void setRentalAmount(double rentalAmount) { this.rentalAmount = rentalAmount; }
    
    public double getSecurityDeposit() { return securityDeposit; }
    public void setSecurityDeposit(double securityDeposit) { this.securityDeposit = securityDeposit; }
    
    public double getMembershipDiscount() { return membershipDiscount; }
    public void setMembershipDiscount(double membershipDiscount) { this.membershipDiscount = membershipDiscount; }
    
    public double getLongRentalDiscount() { return longRentalDiscount; }
    public void setLongRentalDiscount(double longRentalDiscount) { this.longRentalDiscount = longRentalDiscount; }
    
    public double getFinalPayableAmount() { return finalPayableAmount; }
    public void setFinalPayableAmount(double finalPayableAmount) { this.finalPayableAmount = finalPayableAmount; }
    
    public double getLateFee() { return lateFee; }
    public void setLateFee(double lateFee) { this.lateFee = lateFee; }
    
    public double getDamageCharge() { return damageCharge; }
    public void setDamageCharge(double damageCharge) { this.damageCharge = damageCharge; }
    
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    
    public String getRentalStatus() { return rentalStatus; }
    public void setRentalStatus(String rentalStatus) { this.rentalStatus = rentalStatus; }
    
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    
    @Override
    public String toString() {
        return "Rental{" + "rentalId=" + rentalId + ", equipmentId=" + equipmentId + 
               ", customerId=" + customerId + ", rentalAmount=" + rentalAmount + '}';
    }
}

