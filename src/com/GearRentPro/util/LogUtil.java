// src/com/GearRentPro/util/LogUtil.java
package com.GearRentPro.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {
    
    private static final String LOG_DIR = "logs";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_FORMAT = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    // Log levels
    public static final int INFO = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;
    public static final int DEBUG = 4;
    
    static {
        // Create logs directory if it doesn't exist
        File logDir = new File(LOG_DIR);
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }
    
    // Log message to file and console
    public static void log(int level, String message) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String logDate = LocalDateTime.now().format(DATE_FORMAT);
        String levelStr = getLevelString(level);
        
        String logMessage = String.format("[%s] [%s] %s", timestamp, levelStr, message);
        
        // Print to console
        System.out.println(logMessage);
        
        // Write to log file
        writeToFile(logDate, logMessage);
    }
    
    // Log info message
    public static void info(String message) {
        log(INFO, message);
    }
    
    // Log warning message
    public static void warning(String message) {
        log(WARNING, message);
    }
    
    // Log error message
    public static void error(String message) {
        log(ERROR, message);
    }
    
    // Log error with exception
    public static void error(String message, Exception e) {
        error(message + " - " + e.getMessage());
        e.printStackTrace();
    }
    
    // Log debug message
    public static void debug(String message) {
        log(DEBUG, message);
    }
    
    // Log user activity
    public static void logUserActivity(String username, String action) {
        String message = String.format("User: %s - Action: %s", username, action);
        info(message);
    }
    
    // Log database operation
    public static void logDatabaseOperation(String operation, String table, boolean success) {
        String status = success ? "SUCCESS" : "FAILED";
        String message = String.format("DB Operation: %s on %s - %s", operation, table, status);
        info(message);
    }
    
    // Write log to file
    private static void writeToFile(String date, String message) {
        String filename = LOG_DIR + "/app_" + date + ".log";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(message);
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
    
    // Get level string
    private static String getLevelString(int level) {
        switch (level) {
            case INFO: return "INFO";
            case WARNING: return "WARNING";
            case ERROR: return "ERROR";
            case DEBUG: return "DEBUG";
            default: return "UNKNOWN";
        }
    }
}