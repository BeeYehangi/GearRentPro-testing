// CategoryDTO.java - Add the 3 double fields
package com.GearRentPro.dto;

public class CategoryDTO extends SuperDTO {
    private String categoryId;
    private String name;
    private String description;
    private double basePriceFactor;    // Add this
    private double weekendMultiplier;  // Add this
    private double lateFeePerDay;      // Add this
    private boolean status;
    
    public CategoryDTO() {}
    
    // 7-parameter constructor
    public CategoryDTO(String categoryId, String name, String description, 
                      double basePriceFactor, double weekendMultiplier, 
                      double lateFeePerDay, boolean status) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.basePriceFactor = basePriceFactor;
        this.weekendMultiplier = weekendMultiplier;
        this.lateFeePerDay = lateFeePerDay;
        this.status = status;
    }
    
    // Getters and Setters
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getBasePriceFactor() { return basePriceFactor; }
    public void setBasePriceFactor(double basePriceFactor) { this.basePriceFactor = basePriceFactor; }
    
    public double getWeekendMultiplier() { return weekendMultiplier; }
    public void setWeekendMultiplier(double weekendMultiplier) { this.weekendMultiplier = weekendMultiplier; }
    
    public double getLateFeePerDay() { return lateFeePerDay; }
    public void setLateFeePerDay(double lateFeePerDay) { this.lateFeePerDay = lateFeePerDay; }
    
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    
    @Override
    public String toString() {
        return "CategoryDTO{" + "categoryId=" + categoryId + ", name=" + name + 
               ", basePriceFactor=" + basePriceFactor + '}';
    }
}