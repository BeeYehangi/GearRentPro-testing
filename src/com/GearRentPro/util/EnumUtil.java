// src/com/GearRentPro/util/EnumUtil.java
package com.GearRentPro.util;

import com.GearRentPro.enums.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

public class EnumUtil {
    
    // Load enums into JavaFX ChoiceBox/ComboBox
    public static <T extends Enum<T>> void loadEnumToChoiceBox(ChoiceBox<T> choiceBox, Class<T> enumClass) {
        ObservableList<T> enumValues = FXCollections.observableArrayList(enumClass.getEnumConstants());
        choiceBox.setItems(enumValues);
        
        choiceBox.setConverter(new StringConverter<T>() {
            @Override
            public String toString(T enumValue) {
                return enumValue != null ? enumValue.toString() : "";
            }
            
            @Override
            public T fromString(String string) {
                return Enum.valueOf(enumClass, string);
            }
        });
    }
    
    // Get all status enums as display names
    public static ObservableList<String> getEquipmentStatusDisplayNames() {
        ObservableList<String> displayNames = FXCollections.observableArrayList();
        for (EquipmentStatus status : EquipmentStatus.values()) {
            displayNames.add(status.getDisplayName());
        }
        return displayNames;
    }
    
    // Get CSS color for status
    public static String getStatusColor(String statusDisplayName) {
        try {
            EquipmentStatus status = EquipmentStatus.fromString(statusDisplayName);
            return status.getColorCode();
        } catch (IllegalArgumentException e) {
            return "#95a5a6"; // Default gray
        }
    }
    
    // Validate if string is valid enum value
    public static <T extends Enum<T>> boolean isValidEnum(Class<T> enumClass, String value) {
        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}