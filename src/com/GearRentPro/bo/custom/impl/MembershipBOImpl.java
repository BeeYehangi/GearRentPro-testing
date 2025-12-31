// src/com/GearRentPro/bo/custom/impl/MembershipBOImpl.java
package com.GearRentPro.bo.custom.impl;

import com.GearRentPro.bo.custom.MembershipBO;
import com.GearRentPro.dao.DAOFactory;
import com.GearRentPro.dao.custom.MembershipDAO;
import com.GearRentPro.dto.MembershipDTO;
import com.GearRentPro.entity.Membership;
import java.util.ArrayList;
import java.util.List;

public class MembershipBOImpl implements MembershipBO {
    
    private final MembershipDAO membershipDAO = (MembershipDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEMBERSHIP);
    
    @Override
    public boolean saveMembership(MembershipDTO membershipDTO) throws Exception {
        Membership membership = new Membership(
            membershipDTO.getMembershipId(),
            membershipDTO.getCustomerId(),
            membershipDTO.getType(),
            membershipDTO.getFee(),
            membershipDTO.getStartDate(),
            membershipDTO.getEndDate(),
            membershipDTO.isStatus()
        );
        return membershipDAO.save(membership);
    }
    
    @Override
    public boolean updateMembership(MembershipDTO membershipDTO) throws Exception {
        Membership membership = new Membership(
            membershipDTO.getMembershipId(),
            membershipDTO.getCustomerId(),
            membershipDTO.getType(),
            membershipDTO.getFee(),
            membershipDTO.getStartDate(),
            membershipDTO.getEndDate(),
            membershipDTO.isStatus()
        );
        return membershipDAO.update(membership);
    }
    
    @Override
    public boolean deleteMembership(String membershipId) throws Exception {
        return membershipDAO.delete(membershipId);
    }
    
    @Override
    public MembershipDTO findMembership(String membershipId) throws Exception {
        Membership membership = membershipDAO.find(membershipId);
        if (membership == null) return null;
        
        return new MembershipDTO(
            membership.getMembershipId(),
            membership.getCustomerId(),
            membership.getType(),
            membership.getFee(),
            membership.getStartDate(),
            membership.getEndDate(),
            membership.isStatus()
        );
    }
    
    @Override
    public MembershipDTO findMembershipByCustomer(String customerId) throws Exception {
        Membership membership = membershipDAO.findByCustomer(customerId);
        if (membership == null) return null;
        
        return new MembershipDTO(
            membership.getMembershipId(),
            membership.getCustomerId(),
            membership.getType(),
            membership.getFee(),
            membership.getStartDate(),
            membership.getEndDate(),
            membership.isStatus()
        );
    }
    
    @Override
    public List<MembershipDTO> findAllMemberships() throws Exception {
        List<Membership> memberships = membershipDAO.findAll();
        List<MembershipDTO> membershipDTOs = new ArrayList<>();
        
        for (Membership membership : memberships) {
            membershipDTOs.add(new MembershipDTO(
                membership.getMembershipId(),
                membership.getCustomerId(),
                membership.getType(),
                membership.getFee(),
                membership.getStartDate(),
                membership.getEndDate(),
                membership.isStatus()
            ));
        }
        
        return membershipDTOs;
    }
    
    @Override
    public List<MembershipDTO> findMembershipsByType(String type) throws Exception {
        List<Membership> memberships = membershipDAO.findByType(type);
        List<MembershipDTO> membershipDTOs = new ArrayList<>();
        
        for (Membership membership : memberships) {
            membershipDTOs.add(new MembershipDTO(
                membership.getMembershipId(),
                membership.getCustomerId(),
                membership.getType(),
                membership.getFee(),
                membership.getStartDate(),
                membership.getEndDate(),
                membership.isStatus()
            ));
        }
        
        return membershipDTOs;
    }
    
    @Override
    public List<MembershipDTO> findActiveMemberships() throws Exception {
        List<Membership> memberships = membershipDAO.findActiveMemberships();
        List<MembershipDTO> membershipDTOs = new ArrayList<>();
        
        for (Membership membership : memberships) {
            membershipDTOs.add(new MembershipDTO(
                membership.getMembershipId(),
                membership.getCustomerId(),
                membership.getType(),
                membership.getFee(),
                membership.getStartDate(),
                membership.getEndDate(),
                membership.isStatus()
            ));
        }
        
        return membershipDTOs;
    }
    
    @Override
    public List<MembershipDTO> findExpiringMemberships(int daysBeforeExpiry) throws Exception {
        List<Membership> memberships = membershipDAO.findExpiringMemberships(daysBeforeExpiry);
        List<MembershipDTO> membershipDTOs = new ArrayList<>();
        
        for (Membership membership : memberships) {
            membershipDTOs.add(new MembershipDTO(
                membership.getMembershipId(),
                membership.getCustomerId(),
                membership.getType(),
                membership.getFee(),
                membership.getStartDate(),
                membership.getEndDate(),
                membership.isStatus()
            ));
        }
        
        return membershipDTOs;
    }
    
    @Override
    public boolean renewMembership(String membershipId, String newEndDate) throws Exception {
        return membershipDAO.renewMembership(membershipId, newEndDate);
    }
    
    @Override
    public double calculateDiscountForMember(String customerId) throws Exception {
        Membership membership = membershipDAO.findByCustomer(customerId);
        if (membership == null || !membership.isStatus()) {
            return 0.0;
        }
        
        // Example discount logic: 10% discount for premium members
        if ("PREMIUM".equalsIgnoreCase(membership.getType())) {
            return 0.10; // 10% discount
        } else if ("STANDARD".equalsIgnoreCase(membership.getType())) {
            return 0.05; // 5% discount
        }
        
        return 0.0;
    }
}