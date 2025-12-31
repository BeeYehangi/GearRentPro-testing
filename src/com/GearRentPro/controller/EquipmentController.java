// src/com/GearRentPro/controller/EquipmentController.java
package com.GearRentPro.controller;

import com.GearRentPro.bo.BOFactory;
import com.GearRentPro.bo.custom.EquipmentBO;
import com.GearRentPro.dto.EquipmentDTO;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EquipmentController {
    
    private final EquipmentBO equipmentBO;
    
    public EquipmentController() {
        this.equipmentBO = (EquipmentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EQUIPMENT);
    }
    
    public boolean saveEquipment(String equipmentId, String categoryId, String brand, 
                                String model, int purchaseYear, double baseDailyPrice, 
                                double securityDeposit, String status, String branchId,
                                Date addedDate, Date lastMaintenanceDate) throws Exception {
        EquipmentDTO equipmentDTO = new EquipmentDTO(equipmentId, categoryId, brand, model,
                                                    purchaseYear, baseDailyPrice, securityDeposit,
                                                    status, branchId, addedDate.toLocalDate(),
                                                    lastMaintenanceDate.toLocalDate());
        return equipmentBO.saveEquipment(equipmentDTO);
    }
    
    public boolean updateEquipment(String equipmentId, String categoryId, String brand, 
                                  String model, int purchaseYear, double baseDailyPrice, 
                                  double securityDeposit, String status, String branchId,
                                  Date addedDate, Date lastMaintenanceDate) throws Exception {
        EquipmentDTO equipmentDTO = new EquipmentDTO(equipmentId, categoryId, brand, model,
                                                    purchaseYear, baseDailyPrice, securityDeposit,
                                                    status, branchId, addedDate.toLocalDate(),
                                                    lastMaintenanceDate.toLocalDate());
        return equipmentBO.updateEquipment(equipmentDTO);
    }
    
    public boolean deleteEquipment(String equipmentId) throws Exception {
        return equipmentBO.deleteEquipment(equipmentId);
    }
    
    public EquipmentDTO findEquipment(String equipmentId) throws Exception {
        return equipmentBO.findEquipment(equipmentId);
    }
    
    public List<EquipmentDTO> getAllEquipment() throws Exception {
        return equipmentBO.findAllEquipment();
    }
    
    public List<EquipmentDTO> getEquipmentByCategory(String categoryId) throws Exception {
        return equipmentBO.findEquipmentByCategory(categoryId);
    }
    
    public List<EquipmentDTO> getEquipmentByBranch(String branchId) throws Exception {
        return equipmentBO.findEquipmentByBranch(branchId);
    }
    
    public List<EquipmentDTO> getAvailableEquipment() throws Exception {
        return equipmentBO.findAvailableEquipment();
    }
    
    public boolean updateMaintenanceDate(String equipmentId, Date maintenanceDate) throws Exception {
        // Note: You need to add this method to EquipmentBO interface first
        // return equipmentBO.updateMaintenanceDate(equipmentId, maintenanceDate.toLocalDate());
        throw new UnsupportedOperationException("Method not implemented yet");
    }
}