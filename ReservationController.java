// src/com/GearRentPro/controller/ReservationController.java
package com.GearRentPro.controller;

import com.GearRentPro.bo.BOFactory;
import com.GearRentPro.bo.custom.ReservationBO;
import com.GearRentPro.dto.ReservationDTO;
import java.sql.Date;
import java.util.List;

public class ReservationController {
    
    private final ReservationBO reservationBO;
    
    public ReservationController() {
        this.reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RESERVATION);
    }
    
    public boolean saveReservation(String reservationId, String equipmentId, String customerId,
                                  String branchId, Date startDate, Date endDate,
                                  String status, Date createdDate) throws Exception {
        ReservationDTO reservationDTO = new ReservationDTO(reservationId, equipmentId, customerId,
                                                          branchId, startDate, endDate,
                                                          status, createdDate);
        return reservationBO.saveReservation(reservationDTO);
    }
    
    public boolean updateReservation(String reservationId, String equipmentId, String customerId,
                                    String branchId, Date startDate, Date endDate,
                                    String status, Date createdDate) throws Exception {
        ReservationDTO reservationDTO = new ReservationDTO(reservationId, equipmentId, customerId,
                                                          branchId, startDate, endDate,
                                                          status, createdDate);
        return reservationBO.updateReservation(reservationDTO);
    }
    
    public boolean deleteReservation(String reservationId) throws Exception {
        return reservationBO.deleteReservation(reservationId);
    }
    
    public ReservationDTO findReservation(String reservationId) throws Exception {
        return reservationBO.findReservation(reservationId);
    }
    
    public List<ReservationDTO> getAllReservations() throws Exception {
        return reservationBO.findAllReservations();
    }
    
    public List<ReservationDTO> getReservationsByCustomer(String customerId) throws Exception {
        return reservationBO.findReservationsByCustomer(customerId);
    }
    
    public List<ReservationDTO> getReservationsByEquipment(String equipmentId) throws Exception {
        return reservationBO.findReservationsByEquipment(equipmentId);
    }
    
    public List<ReservationDTO> getReservationsByStatus(String status) throws Exception {
        return reservationBO.findReservationsByStatus(status);
    }
    
    public boolean updateReservationStatus(String reservationId, String status) throws Exception {
        return reservationBO.updateReservationStatus(reservationId, status);
    }
}