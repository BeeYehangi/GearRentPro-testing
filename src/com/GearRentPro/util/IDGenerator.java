// src/com/GearRentPro/util/IDGenerator.java
package com.GearRentPro.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDGenerator {
    
    // Generate ID with prefix and timestamp
    public static String generateId(String prefix) {
        String timestamp = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
        return prefix + timestamp;
    }
    
    // Generate sequential ID
    public static String generateSequentialId(String prefix, int lastNumber) {
        return String.format("%s%04d", prefix, lastNumber + 1);
    }
    
    // Generate user ID
    public static String generateUserId() {
        return generateId("U");
    }
    
    // Generate customer ID
    public static String generateCustomerId() {
        return generateId("CUST");
    }
    
    // Generate equipment ID
    public static String generateEquipmentId() {
        return generateId("EQ");
    }
    
    // Generate reservation ID
    public static String generateReservationId() {
        return generateId("RES");
    }
    
    // Generate rental ID
    public static String generateRentalId() {
        return generateId("RENT");
    }
    
    // Generate membership ID
    public static String generateMembershipId() {
        return generateId("MEM");
    }
    
    // Generate branch ID
    public static String generateBranchId(int branchCount) {
        return generateSequentialId("BR", branchCount);
    }
    
    // Generate category ID
    public static String generateCategoryId(int categoryCount) {
        return generateSequentialId("CAT", categoryCount);
    }
}