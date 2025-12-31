/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.GearRentPro.entity;

/**
 *
 * @author MCS
 */
public class Category implements SuperEntity {
    private String categoryId;
    private String name;
    private String description;
    private double basePriceFactor;
    private double weekendMultiplier;
    private double lateFeePerDay;
    private boolean status;
    
    public Category() {}
    
    public Category(String categoryId, String name, String description, 
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
        return "Category{" + "categoryId=" + categoryId + ", name=" + name + 
               ", basePriceFactor=" + basePriceFactor + '}';
    }
}