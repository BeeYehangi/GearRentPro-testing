// ReservationBO.java - Remove methods that don't match your database
package com.GearRentPro.bo.custom;

import com.GearRentPro.bo.SuperBO;
import com.GearRentPro.dto.ReservationDTO;
import java.util.List;

public interface ReservationBO extends SuperBO {
    boolean saveReservation(ReservationDTO reservationDTO) throws Exception;
    boolean updateReservation(ReservationDTO reservationDTO) throws Exception;
    boolean deleteReservation(String reservationId) throws Exception;
    ReservationDTO findReservation(String reservationId) throws Exception;
    List<ReservationDTO> findAllReservations() throws Exception;
    List<ReservationDTO> findReservationsByCustomer(String customerId) throws Exception;
    List<ReservationDTO> findReservationsByEquipment(String equipmentId) throws Exception;
    List<ReservationDTO> findReservationsByStatus(String status) throws Exception;
    boolean updateReservationStatus(String reservationId, String status) throws Exception;
    // Remove this method - your Reservation doesn't have duration/total fields
    // double calculateReservationTotal(double dailyRate, int duration) throws Exception;
}