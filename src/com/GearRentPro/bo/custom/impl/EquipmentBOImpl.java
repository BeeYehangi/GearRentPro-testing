// EquipmentBOImpl.java - Fixed with all 11 parameters
package com.GearRentPro.bo.custom.impl;

import com.GearRentPro.bo.custom.EquipmentBO;
import com.GearRentPro.dao.DAOFactory;
import com.GearRentPro.dao.custom.EquipmentDAO;
import com.GearRentPro.dto.EquipmentDTO;
import com.GearRentPro.entity.Equipment;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EquipmentBOImpl implements EquipmentBO {
    
    private final EquipmentDAO equipmentDAO = (EquipmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EQUIPMENT);
    
    @Override
    public boolean saveEquipment(EquipmentDTO equipmentDTO) throws Exception {
        Equipment equipment = new Equipment(
            equipmentDTO.getEquipmentId(),
            equipmentDTO.getCategoryId(),
            equipmentDTO.getBrand(),
            equipmentDTO.getModel(),
            equipmentDTO.getPurchaseYear(),
            equipmentDTO.getBaseDailyPrice(),
            equipmentDTO.getSecurityDeposit(),
            equipmentDTO.getStatus(),
            equipmentDTO.getBranchId(),
            equipmentDTO.getAddedDate(),
            equipmentDTO.getLastMaintenanceDate()
        );
        return equipmentDAO.save(equipment);
    }
    
    @Override
    public boolean updateEquipment(EquipmentDTO equipmentDTO) throws Exception {
        Equipment equipment = new Equipment(
            equipmentDTO.getEquipmentId(),
            equipmentDTO.getCategoryId(),
            equipmentDTO.getBrand(),
            equipmentDTO.getModel(),
            equipmentDTO.getPurchaseYear(),
            equipmentDTO.getBaseDailyPrice(),
            equipmentDTO.getSecurityDeposit(),
            equipmentDTO.getStatus(),
            equipmentDTO.getBranchId(),
            equipmentDTO.getAddedDate(),
            equipmentDTO.getLastMaintenanceDate()
        );
        return equipmentDAO.update(equipment);
    }
    
    @Override
    public boolean deleteEquipment(String equipmentId) throws Exception {
        return equipmentDAO.delete(equipmentId);
    }
    
    @Override
    public EquipmentDTO findEquipment(String equipmentId) throws Exception {
        Equipment equipment = equipmentDAO.find(equipmentId);
        if (equipment == null) return null;
        
        return new EquipmentDTO(
            equipment.getEquipmentId(),
            equipment.getCategoryId(),
            equipment.getBrand(),
            equipment.getModel(),
            equipment.getPurchaseYear(),
            equipment.getBaseDailyPrice(),
            equipment.getSecurityDeposit(),
            equipment.getStatus(),
            equipment.getBranchId(),
            equipment.getAddedDate(),
            equipment.getLastMaintenanceDate()
        );
    }
    
    @Override
    public List<EquipmentDTO> findAllEquipment() throws Exception {
        List<Equipment> equipments = equipmentDAO.findAll();
        List<EquipmentDTO> equipmentDTOs = new ArrayList<>();
        
        for (Equipment equipment : equipments) {
            equipmentDTOs.add(new EquipmentDTO(
                equipment.getEquipmentId(),
                equipment.getCategoryId(),
                equipment.getBrand(),
                equipment.getModel(),
                equipment.getPurchaseYear(),
                equipment.getBaseDailyPrice(),
                equipment.getSecurityDeposit(),
                equipment.getStatus(),
                equipment.getBranchId(),
                equipment.getAddedDate(),
                equipment.getLastMaintenanceDate()
            ));
        }
        
        return equipmentDTOs;
    }
    
    @Override
    public List<EquipmentDTO> findEquipmentByCategory(String categoryId) throws Exception {
        List<Equipment> equipments = equipmentDAO.findByCategory(categoryId);
        List<EquipmentDTO> equipmentDTOs = new ArrayList<>();
        
        for (Equipment equipment : equipments) {
            equipmentDTOs.add(new EquipmentDTO(
                equipment.getEquipmentId(),
                equipment.getCategoryId(),
                equipment.getBrand(),
                equipment.getModel(),
                equipment.getPurchaseYear(),
                equipment.getBaseDailyPrice(),
                equipment.getSecurityDeposit(),
                equipment.getStatus(),
                equipment.getBranchId(),
                equipment.getAddedDate(),
                equipment.getLastMaintenanceDate()
            ));
        }
        
        return equipmentDTOs;
    }
    
    @Override
    public List<EquipmentDTO> findEquipmentByBranch(String branchId) throws Exception {
        List<Equipment> equipments = equipmentDAO.findByBranch(branchId);
        List<EquipmentDTO> equipmentDTOs = new ArrayList<>();
        
        for (Equipment equipment : equipments) {
            equipmentDTOs.add(new EquipmentDTO(
                equipment.getEquipmentId(),
                equipment.getCategoryId(),
                equipment.getBrand(),
                equipment.getModel(),
                equipment.getPurchaseYear(),
                equipment.getBaseDailyPrice(),
                equipment.getSecurityDeposit(),
                equipment.getStatus(),
                equipment.getBranchId(),
                equipment.getAddedDate(),
                equipment.getLastMaintenanceDate()
            ));
        }
        
        return equipmentDTOs;
    }
    
    @Override
    public List<EquipmentDTO> findAvailableEquipment() throws Exception {
        List<Equipment> equipments = equipmentDAO.findAvailableEquipment();
        List<EquipmentDTO> equipmentDTOs = new ArrayList<>();
        
        for (Equipment equipment : equipments) {
            equipmentDTOs.add(new EquipmentDTO(
                equipment.getEquipmentId(),
                equipment.getCategoryId(),
                equipment.getBrand(),
                equipment.getModel(),
                equipment.getPurchaseYear(),
                equipment.getBaseDailyPrice(),
                equipment.getSecurityDeposit(),
                equipment.getStatus(),
                equipment.getBranchId(),
                equipment.getAddedDate(),
                equipment.getLastMaintenanceDate()
            ));
        }
        
        return equipmentDTOs;
    }
}