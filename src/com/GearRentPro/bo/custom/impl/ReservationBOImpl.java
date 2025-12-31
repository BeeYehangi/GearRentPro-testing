// ReservationBOImpl.java - Fixed with 8 parameters
package com.GearRentPro.bo.custom.impl;

import com.GearRentPro.bo.custom.ReservationBO;
import com.GearRentPro.dao.DAOFactory;
import com.GearRentPro.dao.custom.ReservationDAO;
import com.GearRentPro.dto.ReservationDTO;
import com.GearRentPro.entity.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RESERVATION);
    
    @Override
    public boolean saveReservation(ReservationDTO reservationDTO) throws Exception {
        Reservation reservation = new Reservation(
            reservationDTO.getReservationId(),
            reservationDTO.getEquipmentId(),
            reservationDTO.getCustomerId(),
            reservationDTO.getBranchId(),
            reservationDTO.getStartDate(),
            reservationDTO.getEndDate(),
            reservationDTO.getStatus(),
            reservationDTO.getCreatedDate()
        );
        return reservationDAO.save(reservation);
    }
    
    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        Reservation reservation = new Reservation(
            reservationDTO.getReservationId(),
            reservationDTO.getEquipmentId(),
            reservationDTO.getCustomerId(),
            reservationDTO.getBranchId(),
            reservationDTO.getStartDate(),
            reservationDTO.getEndDate(),
            reservationDTO.getStatus(),
            reservationDTO.getCreatedDate()
        );
        return reservationDAO.update(reservation);
    }
    
    @Override
    public boolean deleteReservation(String reservationId) throws Exception {
        return reservationDAO.delete(reservationId);
    }
    
    @Override
    public ReservationDTO findReservation(String reservationId) throws Exception {
        Reservation reservation = reservationDAO.find(reservationId);
        if (reservation == null) return null;
        
        return new ReservationDTO(
            reservation.getReservationId(),
            reservation.getEquipmentId(),
            reservation.getCustomerId(),
            reservation.getBranchId(),
            reservation.getStartDate(),
            reservation.getEndDate(),
            reservation.getStatus(),
            reservation.getCreatedDate()
        );
    }
    
    @Override
    public List<ReservationDTO> findAllReservations() throws Exception {
        List<Reservation> reservations = reservationDAO.findAll();
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        
        for (Reservation reservation : reservations) {
            reservationDTOs.add(new ReservationDTO(
                reservation.getReservationId(),
                reservation.getEquipmentId(),
                reservation.getCustomerId(),
                reservation.getBranchId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStatus(),
                reservation.getCreatedDate()
            ));
        }
        
        return reservationDTOs;
    }
    
    @Override
    public List<ReservationDTO> findReservationsByCustomer(String customerId) throws Exception {
        List<Reservation> reservations = reservationDAO.findByCustomer(customerId);
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        
        for (Reservation reservation : reservations) {
            reservationDTOs.add(new ReservationDTO(
                reservation.getReservationId(),
                reservation.getEquipmentId(),
                reservation.getCustomerId(),
                reservation.getBranchId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStatus(),
                reservation.getCreatedDate()
            ));
        }
        
        return reservationDTOs;
    }
    
    @Override
    public List<ReservationDTO> findReservationsByEquipment(String equipmentId) throws Exception {
        List<Reservation> reservations = reservationDAO.findByEquipment(equipmentId);
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        
        for (Reservation reservation : reservations) {
            reservationDTOs.add(new ReservationDTO(
                reservation.getReservationId(),
                reservation.getEquipmentId(),
                reservation.getCustomerId(),
                reservation.getBranchId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStatus(),
                reservation.getCreatedDate()
            ));
        }
        
        return reservationDTOs;
    }
    
    @Override
    public List<ReservationDTO> findReservationsByStatus(String status) throws Exception {
        List<Reservation> reservations = reservationDAO.findByStatus(status);
        List<ReservationDTO> reservationDTOs = new ArrayList<>();
        
        for (Reservation reservation : reservations) {
            reservationDTOs.add(new ReservationDTO(
                reservation.getReservationId(),
                reservation.getEquipmentId(),
                reservation.getCustomerId(),
                reservation.getBranchId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStatus(),
                reservation.getCreatedDate()
            ));
        }
        
        return reservationDTOs;
    }
    
    @Override
    public boolean updateReservationStatus(String reservationId, String status) throws Exception {
        return reservationDAO.updateStatus(reservationId, status);
    }
}