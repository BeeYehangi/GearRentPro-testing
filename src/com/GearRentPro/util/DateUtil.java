// src/com/GearRentPro/util/DateUtil.java
package com.GearRentPro.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    // Convert String to SQL Date
    public static Date stringToSqlDate(String dateString) {
        try {
            LocalDate localDate = LocalDate.parse(dateString, DATE_FORMATTER);
            return Date.valueOf(localDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Expected: yyyy-MM-dd");
        }
    }
    
    // Convert SQL Date to String
    public static String sqlDateToString(Date sqlDate) {
        if (sqlDate == null) return "";
        return sqlDate.toLocalDate().format(DATE_FORMATTER);
    }
    
    // Format date for display
    public static String formatForDisplay(Date sqlDate) {
        if (sqlDate == null) return "";
        return sqlDate.toLocalDate().format(DISPLAY_FORMATTER);
    }
    
    // Format date for display from String
    public static String formatForDisplay(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
            return date.format(DISPLAY_FORMATTER);
        } catch (Exception e) {
            return dateString;
        }
    }
    
    // Get current date as SQL Date
    public static Date getCurrentDate() {
        return Date.valueOf(LocalDate.now());
    }
    
    // Get current date as String
    public static String getCurrentDateString() {
        return LocalDate.now().format(DATE_FORMATTER);
    }
    
    // Calculate days between two dates
    public static long daysBetween(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) return 0;
        return ChronoUnit.DAYS.between(startDate.toLocalDate(), endDate.toLocalDate());
    }
    
    // Add days to a date
    public static Date addDays(Date date, int days) {
        if (date == null) return null;
        LocalDate localDate = date.toLocalDate().plusDays(days);
        return Date.valueOf(localDate);
    }
    
    // Check if date is within range
    public static boolean isWithinRange(Date checkDate, Date startDate, Date endDate) {
        if (checkDate == null || startDate == null || endDate == null) return false;
        LocalDate check = checkDate.toLocalDate();
        LocalDate start = startDate.toLocalDate();
        LocalDate end = endDate.toLocalDate();
        return !check.isBefore(start) && !check.isAfter(end);
    }
    
    // Calculate rental duration in days
    public static int calculateRentalDuration(Date startDate, Date endDate) {
        long days = daysBetween(startDate, endDate);
        return (int) Math.max(1, days); // Minimum 1 day rental
    }
}