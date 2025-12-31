// src/com/GearRentPro/util/ConfigUtil.java
package com.GearRentPro.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    
    private static final String CONFIG_FILE = "config.properties";
    private static Properties properties;
    
    static {
        properties = new Properties();
        loadConfig();
    }
    
    // Load configuration from file
    private static void loadConfig() {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            LogUtil.info("Configuration loaded from " + CONFIG_FILE);
        } catch (IOException e) {
            // If file doesn't exist, create with default values
            setDefaultConfig();
            saveConfig();
            LogUtil.info("Created default configuration file");
        }
    }
    
    // Set default configuration
    private static void setDefaultConfig() {
        properties.setProperty("db.url", "jdbc:mysql://localhost:3306/gearrentpro");
        properties.setProperty("db.username", "root");
        properties.setProperty("db.password", "1234");
        properties.setProperty("app.name", "GearRent Pro");
        properties.setProperty("app.version", "1.0.0");
        properties.setProperty("app.theme", "default");
        properties.setProperty("tax.rate", "8.0");
        properties.setProperty("currency", "USD");
        properties.setProperty("max.rental.days", "30");
        properties.setProperty("late.fee.rate", "0.5");
        properties.setProperty("security.deposit.rate", "0.3");
    }
    
    // Save configuration to file
    public static void saveConfig() {
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            properties.store(fos, "GearRent Pro Configuration");
            LogUtil.info("Configuration saved to " + CONFIG_FILE);
        } catch (IOException e) {
            LogUtil.error("Failed to save configuration", e);
        }
    }
    
    // Get property value
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    // Get property with default value
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    // Set property value
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveConfig();
    }
    
    // Get database URL
    public static String getDbUrl() {
        return getProperty("db.url");
    }
    
    // Get database username
    public static String getDbUsername() {
        return getProperty("db.username");
    }
    
    // Get database password
    public static String getDbPassword() {
        return getProperty("db.password");
    }
    
    // Get application name
    public static String getAppName() {
        return getProperty("app.name", "GearRent Pro");
    }
    
    // Get tax rate
    public static double getTaxRate() {
        try {
            return Double.parseDouble(getProperty("tax.rate", "8.0"));
        } catch (NumberFormatException e) {
            return 8.0;
        }
    }
    
    // Get currency symbol
    public static String getCurrency() {
        return getProperty("currency", "USD");
    }
    
    // Get maximum rental days
    public static int getMaxRentalDays() {
        try {
            return Integer.parseInt(getProperty("max.rental.days", "30"));
        } catch (NumberFormatException e) {
            return 30;
        }
    }
    
    // Get late fee rate
    public static double getLateFeeRate() {
        try {
            return Double.parseDouble(getProperty("late.fee.rate", "0.5"));
        } catch (NumberFormatException e) {
            return 0.5;
        }
    }
}