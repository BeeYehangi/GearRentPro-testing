// src/com/GearRentPro/bo/custom/impl/RentalBOImpl.java
package com.GearRentPro.bo.custom.impl;

import com.GearRentPro.bo.custom.RentalBO;
import com.GearRentPro.dao.DAOFactory;
import com.GearRentPro.dao.custom.RentalDAO;
import com.GearRentPro.dto.RentalDTO;
import com.GearRentPro.entity.Rental;
import java.util.ArrayList;
import java.util.List;

public class RentalBOImpl implements RentalBO {
    
    private final RentalDAO rentalDAO = (RentalDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RENTAL);
    
    @Override
    public boolean saveRental(RentalDTO rentalDTO) throws Exception {
        Rental rental = new Rental(
            rentalDTO.getRentalId(),
            rentalDTO.getEquipmentId(),
            rentalDTO.getCustomerId(),
            rentalDTO.getBranchId(),
            rentalDTO.getStartDate(),
            rentalDTO.getEndDate(),
            rentalDTO.getActualReturnDate(),
            rentalDTO.getRentalAmount(),
            rentalDTO.getSecurityDeposit(),
            rentalDTO.getMembershipDiscount(),
            rentalDTO.getLongRentalDiscount(),
            rentalDTO.getFinalPayableAmount(),
            rentalDTO.getLateFee(),
            rentalDTO.getDamageCharge(),
            rentalDTO.getPaymentStatus(),
            rentalDTO.getRentalStatus(),
            rentalDTO.getCreatedDate()
        );
        return rentalDAO.save(rental);
    }
    
    @Override
    public boolean updateRental(RentalDTO rentalDTO) throws Exception {
        Rental rental = new Rental(
            rentalDTO.getRentalId(),
            rentalDTO.getEquipmentId(),
            rentalDTO.getCustomerId(),
            rentalDTO.getBranchId(),
            rentalDTO.getStartDate(),
            rentalDTO.getEndDate(),
            rentalDTO.getActualReturnDate(),
            rentalDTO.getRentalAmount(),
            rentalDTO.getSecurityDeposit(),
            rentalDTO.getMembershipDiscount(),
            rentalDTO.getLongRentalDiscount(),
            rentalDTO.getFinalPayableAmount(),
            rentalDTO.getLateFee(),
            rentalDTO.getDamageCharge(),
            rentalDTO.getPaymentStatus(),
            rentalDTO.getRentalStatus(),
            rentalDTO.getCreatedDate()
        );
        return rentalDAO.update(rental);
    }
    
    @Override
    public boolean deleteRental(String rentalId) throws Exception {
        return rentalDAO.delete(rentalId);
    }
    
    @Override
    public RentalDTO findRental(String rentalId) throws Exception {
        Rental rental = rentalDAO.find(rentalId);
        if (rental == null) return null;
        
        return new RentalDTO(
            rental.getRentalId(),
            rental.getEquipmentId(),
            rental.getCustomerId(),
            rental.getBranchId(),
            rental.getStartDate(),
            rental.getEndDate(),
            rental.getActualReturnDate(),
            rental.getRentalAmount(),
            rental.getSecurityDeposit(),
            rental.getMembershipDiscount(),
            rental.getLongRentalDiscount(),
            rental.getFinalPayableAmount(),
            rental.getLateFee(),
            rental.getDamageCharge(),
            rental.getPaymentStatus(),
            rental.getRentalStatus(),
            rental.getCreatedDate()
        );
    }
    
    @Override
    public RentalDTO findRentalByReservation(String reservationId) throws Exception {
        // Note: Your Rental entity doesn't have reservationId field!
        // This method might need to be removed or you need to add reservationId to Rental entity
        return null;
    }
    
    @Override
    public List<RentalDTO> findAllRentals() throws Exception {
        List<Rental> rentals = rentalDAO.findAll();
        List<RentalDTO> rentalDTOs = new ArrayList<>();
        
        for (Rental rental : rentals) {
            rentalDTOs.add(new RentalDTO(
                rental.getRentalId(),
                rental.getEquipmentId(),
                rental.getCustomerId(),
                rental.getBranchId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getActualReturnDate(),
                rental.getRentalAmount(),
                rental.getSecurityDeposit(),
                rental.getMembershipDiscount(),
                rental.getLongRentalDiscount(),
                rental.getFinalPayableAmount(),
                rental.getLateFee(),
                rental.getDamageCharge(),
                rental.getPaymentStatus(),
                rental.getRentalStatus(),
                rental.getCreatedDate()
            ));
        }
        
        return rentalDTOs;
    }
    
    @Override
    public List<RentalDTO> findRentalsByCustomer(String customerId) throws Exception {
        List<Rental> rentals = rentalDAO.findByCustomer(customerId);
        List<RentalDTO> rentalDTOs = new ArrayList<>();
        
        for (Rental rental : rentals) {
            rentalDTOs.add(new RentalDTO(
                rental.getRentalId(),
                rental.getEquipmentId(),
                rental.getCustomerId(),
                rental.getBranchId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getActualReturnDate(),
                rental.getRentalAmount(),
                rental.getSecurityDeposit(),
                rental.getMembershipDiscount(),
                rental.getLongRentalDiscount(),
                rental.getFinalPayableAmount(),
                rental.getLateFee(),
                rental.getDamageCharge(),
                rental.getPaymentStatus(),
                rental.getRentalStatus(),
                rental.getCreatedDate()
            ));
        }
        
        return rentalDTOs;
    }
    
    @Override
    public List<RentalDTO> findRentalsByStatus(String status) throws Exception {
        List<Rental> rentals = rentalDAO.findByStatus(status);
        List<RentalDTO> rentalDTOs = new ArrayList<>();
        
        for (Rental rental : rentals) {
            rentalDTOs.add(new RentalDTO(
                rental.getRentalId(),
                rental.getEquipmentId(),
                rental.getCustomerId(),
                rental.getBranchId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getActualReturnDate(),
                rental.getRentalAmount(),
                rental.getSecurityDeposit(),
                rental.getMembershipDiscount(),
                rental.getLongRentalDiscount(),
                rental.getFinalPayableAmount(),
                rental.getLateFee(),
                rental.getDamageCharge(),
                rental.getPaymentStatus(),
                rental.getRentalStatus(),
                rental.getCreatedDate()
            ));
        }
        
        return rentalDTOs;
    }
    
    @Override
    public boolean updateRentalStatus(String rentalId, String status) throws Exception {
        return rentalDAO.updateStatus(rentalId, status);
    }
    
    @Override
    public double calculateRentalFee(double dailyRate, int duration) throws Exception {
        return dailyRate * duration;
    }
}