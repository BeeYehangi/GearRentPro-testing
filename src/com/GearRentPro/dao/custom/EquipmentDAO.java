// EquipmentDAO.java - Remove quantity methods
package com.GearRentPro.dao.custom;

import com.GearRentPro.dao.CrudDAO;
import com.GearRentPro.entity.Equipment;
import java.util.List;

public interface EquipmentDAO extends CrudDAO<Equipment, String> {
    List<Equipment> findByCategory(String categoryId) throws Exception;
    List<Equipment> findByBranch(String branchId) throws Exception;
    // REMOVE: boolean updateQuantity(String equipmentId, int newQuantity) throws Exception;
    List<Equipment> findAvailableEquipment() throws Exception;
    // Add maintenance method:
    boolean updateMaintenanceDate(String equipmentId, java.time.LocalDate maintenanceDate) throws Exception;
}