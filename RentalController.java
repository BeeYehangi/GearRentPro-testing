// src/com/GearRentPro/controller/RentalController.java
package com.GearRentPro.controller;

import com.GearRentPro.bo.BOFactory;
import com.GearRentPro.bo.custom.RentalBO;
import com.GearRentPro.dto.RentalDTO;
import java.sql.Date;
import java.util.List;

public class RentalController {
    
    private final RentalBO rentalBO;
    
    public RentalController() {
        this.rentalBO = (RentalBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RENTAL);
    }
    
    public boolean saveRental(String rentalId, String equipmentId, String customerId,
                             String branchId, Date startDate, Date endDate,
                             Date actualReturnDate, double rentalAmount,
                             double securityDeposit, double membershipDiscount,
                             double longRentalDiscount, double finalPayableAmount,
                             double lateFee, double damageCharge, String paymentStatus,
                             String rentalStatus, Date createdDate) throws Exception {
        RentalDTO rentalDTO = new RentalDTO(rentalId, equipmentId, customerId, branchId,
                                           startDate, endDate, actualReturnDate, rentalAmount,
                                           securityDeposit, membershipDiscount, longRentalDiscount,
                                           finalPayableAmount, lateFee, damageCharge,
                                           paymentStatus, rentalStatus, createdDate);
        return rentalBO.saveRental(rentalDTO);
    }
    
    public boolean updateRental(String rentalId, String equipmentId, String customerId,
                               String branchId, Date startDate, Date endDate,
                               Date actualReturnDate, double rentalAmount,
                               double securityDeposit, double membershipDiscount,
                               double longRentalDiscount, double finalPayableAmount,
                               double lateFee, double damageCharge, String paymentStatus,
                               String rentalStatus, Date createdDate) throws Exception {
        RentalDTO rentalDTO = new RentalDTO(rentalId, equipmentId, customerId, branchId,
                                           startDate, endDate, actualReturnDate, rentalAmount,
                                           securityDeposit, membershipDiscount, longRentalDiscount,
                                           finalPayableAmount, lateFee, damageCharge,
                                           paymentStatus, rentalStatus, createdDate);
        return rentalBO.updateRental(rentalDTO);
    }
    
    public boolean deleteRental(String rentalId) throws Exception {
        return rentalBO.deleteRental(rentalId);
    }
    
    public RentalDTO findRental(String rentalId) throws Exception {
        return rentalBO.findRental(rentalId);
    }
    
    public List<RentalDTO> getAllRentals() throws Exception {
        return rentalBO.findAllRentals();
    }
    
    public List<RentalDTO> getRentalsByCustomer(String customerId) throws Exception {
        return rentalBO.findRentalsByCustomer(customerId);
    }
    
    public List<RentalDTO> getRentalsByStatus(String status) throws Exception {
        return rentalBO.findRentalsByStatus(status);
    }
    
    public boolean updateRentalStatus(String rentalId, String status) throws Exception {
        return rentalBO.updateRentalStatus(rentalId, status);
    }
    
    public double calculateRentalFee(double dailyRate, int duration) throws Exception {
        return rentalBO.calculateRentalFee(dailyRate, duration);
    }
}