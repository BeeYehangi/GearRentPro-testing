// src/com/GearRentPro/bo/custom/MembershipBO.java
package com.GearRentPro.bo.custom;

import com.GearRentPro.bo.SuperBO;
import com.GearRentPro.dto.MembershipDTO;
import java.util.List;

public interface MembershipBO extends SuperBO {
    boolean saveMembership(MembershipDTO membershipDTO) throws Exception;
    boolean updateMembership(MembershipDTO membershipDTO) throws Exception;
    boolean deleteMembership(String membershipId) throws Exception;
    MembershipDTO findMembership(String membershipId) throws Exception;
    MembershipDTO findMembershipByCustomer(String customerId) throws Exception;
    List<MembershipDTO> findAllMemberships() throws Exception;
    List<MembershipDTO> findMembershipsByType(String type) throws Exception;
    List<MembershipDTO> findActiveMemberships() throws Exception;
    List<MembershipDTO> findExpiringMemberships(int daysBeforeExpiry) throws Exception;
    boolean renewMembership(String membershipId, String newEndDate) throws Exception;
    double calculateDiscountForMember(String customerId) throws Exception;
}