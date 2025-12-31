package com.GearRentPro.service;

import com.GearRentPro.entity.Equipment;
import com.GearRentPro.entity.Customer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SimplePricingService {
    
    // ======================
    // COURSEWORK PRICING RULES
    // ======================
    
    // 1. Category Factors
    private BigDecimal getCategoryFactor(String category) {
        if (category == null) return BigDecimal.ONE;
        
        String catLower = category.toLowerCase();
        if (catLower.contains("drone")) return new BigDecimal("1.5");
        if (catLower.contains("camera") || catLower.contains("dslr")) return new BigDecimal("1.3");
        if (catLower.contains("lens")) return new BigDecimal("1.2");
        if (catLower.contains("audio") || catLower.contains("mic")) return new BigDecimal("1.15");
        if (catLower.contains("light") || catLower.contains("studio")) return new BigDecimal("1.1");
        return BigDecimal.ONE;
    }
    
    // 2. Weekend Multiplier
    private BigDecimal getWeekendMultiplier() {
        return new BigDecimal("1.2");
    }
    
    // ======================
    // MAIN CALCULATION
    // ======================
    
    public RentalCalculation calculateRental(Equipment equipment, LocalDate startDate, 
                                            LocalDate endDate, Customer customer) {
        
        // Validate max 30 days
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        if (totalDays > 30) {
            throw new IllegalArgumentException("Maximum rental duration is 30 days");
        }
        
        // Count weekend days
        long weekendDays = countWeekendDays(startDate, endDate);
        long weekdayDays = totalDays - weekendDays;
        
        // Get factors
        BigDecimal categoryFactor = getCategoryFactor(equipment.getCategory());
        BigDecimal weekendMultiplier = getWeekendMultiplier();
        
        // Calculate
        BigDecimal weekdayRate = equipment.getDailyRate()
            .multiply(categoryFactor)
            .setScale(2, RoundingMode.HALF_UP);
            
        BigDecimal weekendRate = weekdayRate.multiply(weekendMultiplier)
            .setScale(2, RoundingMode.HALF_UP);
        
        BigDecimal subtotal = weekdayRate.multiply(BigDecimal.valueOf(weekdayDays))
            .add(weekendRate.multiply(BigDecimal.valueOf(weekendDays)));
        
        // Apply discounts
        BigDecimal longRentalDiscount = BigDecimal.ZERO;
        if (totalDays >= 7) {
            longRentalDiscount = subtotal.multiply(new BigDecimal("0.10"));
        }
        
        BigDecimal membershipDiscount = BigDecimal.ZERO;
        if (customer != null && customer.getMembershipLevel() != null) {
            String level = customer.getMembershipLevel().toUpperCase();
            if (level.contains("SILVER")) {
                membershipDiscount = subtotal.multiply(new BigDecimal("0.05"));
            } else if (level.contains("GOLD")) {
                membershipDiscount = subtotal.multiply(new BigDecimal("0.10"));
            }
        }
        
        BigDecimal totalDiscount = longRentalDiscount.add(membershipDiscount);
        BigDecimal finalAmount = subtotal.subtract(totalDiscount);
        
        // Calculate deposit
        BigDecimal deposit = equipment.getDepositAmount();
        BigDecimal minDeposit = finalAmount.multiply(new BigDecimal("0.5"));
        if (deposit.compareTo(minDeposit) < 0) {
            deposit = minDeposit;
        }
        
        // Return result
        RentalCalculation result = new RentalCalculation();
        result.setTotalDays((int) totalDays);
        result.setWeekendDays((int) weekendDays);
        result.setSubtotal(subtotal);
        result.setLongRentalDiscount(longRentalDiscount);
        result.setMembershipDiscount(membershipDiscount);
        result.setFinalAmount(finalAmount);
        result.setDeposit(deposit);
        
        return result;
    }
    
    // Helper method
    private long countWeekendDays(LocalDate start, LocalDate end) {
        long weekendDays = 0;
        LocalDate date = start;
        while (!date.isAfter(end)) {
            int dayOfWeek = date.getDayOfWeek().getValue();
            if (dayOfWeek == 6 || dayOfWeek == 7) weekendDays++;
            date = date.plusDays(1);
        }
        return weekendDays;
    }
    
    // ======================
    // INNER CLASS
    // ======================
    
    public static class RentalCalculation {
        private int totalDays;
        private int weekendDays;
        private BigDecimal subtotal;
        private BigDecimal longRentalDiscount;
        private BigDecimal membershipDiscount;
        private BigDecimal finalAmount;
        private BigDecimal deposit;
        
        // Getters and setters
        public int getTotalDays() { return totalDays; }
        public void setTotalDays(int totalDays) { this.totalDays = totalDays; }
        
        public int getWeekendDays() { return weekendDays; }
        public void setWeekendDays(int weekendDays) { this.weekendDays = weekendDays; }
        
        public BigDecimal getSubtotal() { return subtotal; }
        public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
        
        public BigDecimal getLongRentalDiscount() { return longRentalDiscount; }
        public void setLongRentalDiscount(BigDecimal discount) { this.longRentalDiscount = discount; }
        
        public BigDecimal getMembershipDiscount() { return membershipDiscount; }
        public void setMembershipDiscount(BigDecimal discount) { this.membershipDiscount = discount; }
        
        public BigDecimal getFinalAmount() { return finalAmount; }
        public void setFinalAmount(BigDecimal finalAmount) { this.finalAmount = finalAmount; }
        
        public BigDecimal getDeposit() { return deposit; }
        public void setDeposit(BigDecimal deposit) { this.deposit = deposit; }
    }
}