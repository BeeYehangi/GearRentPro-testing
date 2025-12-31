// src/com/GearRentPro/dto/ReservationDTO.java
package com.GearRentPro.dto;

import java.sql.Date;

public class ReservationDTO extends SuperDTO {
    private String reservationId;
    private String equipmentId;
    private String customerId;
    private String branchId;
    private Date startDate;
    private Date endDate;
    private String status;
    private Date createdDate;
    
    public ReservationDTO() {}
    
    // Constructor with 8 parameters
    public ReservationDTO(String reservationId, String equipmentId, String customerId, 
                         String branchId, Date startDate, Date endDate, 
                         String status, Date createdDate) {
        this.reservationId = reservationId;
        this.equipmentId = equipmentId;
        this.customerId = customerId;
        this.branchId = branchId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdDate = createdDate;
    }
    
    // Getters and Setters
    public String getReservationId() { return reservationId; }
    public void setReservationId(String reservationId) { this.reservationId = reservationId; }
    
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
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
    
    @Override
    public String toString() {
        return "ReservationDTO{" + "reservationId='" + reservationId + '\'' + 
               ", equipmentId='" + equipmentId + '\'' + ", startDate=" + startDate + '}';
    }
}