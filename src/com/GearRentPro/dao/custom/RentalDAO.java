// src/com/GearRentPro/dao/custom/RentalDAO.java
package com.GearRentPro.dao.custom;

import com.GearRentPro.dao.CrudDAO;
import com.GearRentPro.entity.Rental;
import java.util.List;

public interface RentalDAO extends CrudDAO<Rental, String> {
    List<Rental> findByCustomer(String customerId) throws Exception;
    List<Rental> findByStatus(String status) throws Exception;
    boolean updateStatus(String rentalId, String status) throws Exception;
}