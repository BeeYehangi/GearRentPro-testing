// src/com/GearRentPro/controller/MembershipController.java
package com.GearRentPro.controller;

import com.GearRentPro.bo.BOFactory;
import com.GearRentPro.bo.custom.MembershipBO;
import com.GearRentPro.dto.MembershipDTO;
import java.util.List;

public class MembershipController {
    
    private final MembershipBO membershipBO;
    
    public MembershipController() {
        this.membershipBO = (MembershipBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MEMBERSHIP);
    }
    
    public boolean saveMembership(String membershipId, String customerId, String type,
                                 double fee, String startDate, String endDate,
                                 boolean status) throws Exception {
        MembershipDTO membershipDTO = new MembershipDTO(membershipId, customerId, type,
                                                       fee, startDate, endDate, status);
        return membershipBO.saveMembership(membershipDTO);
    }
    
    public boolean updateMembership(String membershipId, String customerId, String type,
                                   double fee, String startDate, String endDate,
                                   boolean status) throws Exception {
        MembershipDTO membershipDTO = new MembershipDTO(membershipId, customerId, type,
                                                       fee, startDate, endDate, status);
        return membershipBO.updateMembership(membershipDTO);
    }
    
    public boolean deleteMembership(String membershipId) throws Exception {
        return membershipBO.deleteMembership(membershipId);
    }
    
    public MembershipDTO findMembership(String membershipId) throws Exception {
        return membershipBO.findMembership(membershipId);
    }
    
    public MembershipDTO findMembershipByCustomer(String customerId) throws Exception {
        return membershipBO.findMembershipByCustomer(customerId);
    }
    
    public List<MembershipDTO> getAllMemberships() throws Exception {
        return membershipBO.findAllMemberships();
    }
    
    public List<MembershipDTO> getMembershipsByType(String type) throws Exception {
        return membershipBO.findMembershipsByType(type);
    }
    
    public List<MembershipDTO> getActiveMemberships() throws Exception {
        return membershipBO.findActiveMemberships();
    }
    
    public List<MembershipDTO> getExpiringMemberships(int daysBeforeExpiry) throws Exception {
        return membershipBO.findExpiringMemberships(daysBeforeExpiry);
    }
    
    public boolean renewMembership(String membershipId, String newEndDate) throws Exception {
        return membershipBO.renewMembership(membershipId, newEndDate);
    }
    
    public double calculateMemberDiscount(String customerId) throws Exception {
        return membershipBO.calculateDiscountForMember(customerId);
    }
}