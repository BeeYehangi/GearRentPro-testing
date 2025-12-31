// src/com/GearRentPro/bo/custom/RentalBO.java
package com.GearRentPro.bo.custom;

import com.GearRentPro.bo.SuperBO;
import com.GearRentPro.dto.RentalDTO;
import java.util.List;

public interface RentalBO extends SuperBO {
    boolean saveRental(RentalDTO rentalDTO) throws Exception;
    boolean updateRental(RentalDTO rentalDTO) throws Exception;
    boolean deleteRental(String rentalId) throws Exception;
    RentalDTO findRental(String rentalId) throws Exception;
    RentalDTO findRentalByReservation(String reservationId) throws Exception;
    List<RentalDTO> findAllRentals() throws Exception;
    List<RentalDTO> findRentalsByCustomer(String customerId) throws Exception;
    List<RentalDTO> findRentalsByStatus(String status) throws Exception;
    boolean updateRentalStatus(String rentalId, String status) throws Exception;
    double calculateRentalFee(double dailyRate, int duration) throws Exception;
}