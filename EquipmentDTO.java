// EquipmentDTO.java - Updated to match database
package com.GearRentPro.dto;

import java.time.LocalDate;

public class EquipmentDTO extends SuperDTO {
    private String equipmentId;
    private String categoryId;
    private String brand;
    private String model;
    private int purchaseYear;
    private double baseDailyPrice;
    private double securityDeposit;
    private String status;
    private String branchId;
    private LocalDate addedDate;
    private LocalDate lastMaintenanceDate;
    
    public EquipmentDTO() {}
    
    // 11-parameter constructor
    public EquipmentDTO(String equipmentId, String categoryId, String brand, 
                       String model, int purchaseYear, double baseDailyPrice, 
                       double securityDeposit, String status, String branchId,
                       LocalDate addedDate, LocalDate lastMaintenanceDate) {
        this.equipmentId = equipmentId;
        this.categoryId = categoryId;
        this.brand = brand;
        this.model = model;
        this.purchaseYear = purchaseYear;
        this.baseDailyPrice = baseDailyPrice;
        this.securityDeposit = securityDeposit;
        this.status = status;
        this.branchId = branchId;
        this.addedDate = addedDate;
        this.lastMaintenanceDate = lastMaintenanceDate;
    }
    
    // Getters and Setters (all 11 fields)
    public String getEquipmentId() { return equipmentId; }
    public void setEquipmentId(String equipmentId) { this.equipmentId = equipmentId; }
    
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    
    public int getPurchaseYear() { return purchaseYear; }
    public void setPurchaseYear(int purchaseYear) { this.purchaseYear = purchaseYear; }
    
    public double getBaseDailyPrice() { return baseDailyPrice; }
    public void setBaseDailyPrice(double baseDailyPrice) { this.baseDailyPrice = baseDailyPrice; }
    
    public double getSecurityDeposit() { return securityDeposit; }
    public void setSecurityDeposit(double securityDeposit) { this.securityDeposit = securityDeposit; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getBranchId() { return branchId; }
    public void setBranchId(String branchId) { this.branchId = branchId; }
    
    public LocalDate getAddedDate() { return addedDate; }
    public void setAddedDate(LocalDate addedDate) { this.addedDate = addedDate; }
    
    public LocalDate getLastMaintenanceDate() { return lastMaintenanceDate; }
    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) { this.lastMaintenanceDate = lastMaintenanceDate; }
    
    @Override
    public String toString() {
        return "EquipmentDTO{" + "equipmentId='" + equipmentId + '\'' + ", brand='" + brand + '\'' + 
               ", model='" + model + '\'' + ", status='" + status + '\'' + '}';
    }
}