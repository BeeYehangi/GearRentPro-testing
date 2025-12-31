// src/com/GearRentPro/dao/custom/ReservationDAO.java
package com.GearRentPro.dao.custom;

import com.GearRentPro.dao.CrudDAO;
import com.GearRentPro.entity.Reservation;
import java.util.List;

public interface ReservationDAO extends CrudDAO<Reservation, String> {
    List<Reservation> findByCustomer(String customerId) throws Exception;
    List<Reservation> findByEquipment(String equipmentId) throws Exception;
    List<Reservation> findByStatus(String status) throws Exception;
    boolean updateStatus(String reservationId, String status) throws Exception;
}