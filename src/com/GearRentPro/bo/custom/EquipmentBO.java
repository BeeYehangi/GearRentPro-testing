// EquipmentBO.java - Final version
package com.GearRentPro.bo.custom;

import com.GearRentPro.bo.SuperBO;
import com.GearRentPro.dto.EquipmentDTO;
import java.util.List;

public interface EquipmentBO extends SuperBO {
    boolean saveEquipment(EquipmentDTO equipmentDTO) throws Exception;
    boolean updateEquipment(EquipmentDTO equipmentDTO) throws Exception;
    boolean deleteEquipment(String equipmentId) throws Exception;
    EquipmentDTO findEquipment(String equipmentId) throws Exception;
    List<EquipmentDTO> findAllEquipment() throws Exception;
    List<EquipmentDTO> findEquipmentByCategory(String categoryId) throws Exception;
    List<EquipmentDTO> findEquipmentByBranch(String branchId) throws Exception;
    List<EquipmentDTO> findAvailableEquipment() throws Exception;
}