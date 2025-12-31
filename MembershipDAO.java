// src/com/GearRentPro/dao/custom/MembershipDAO.java
package com.GearRentPro.dao.custom;

import com.GearRentPro.dao.CrudDAO;
import com.GearRentPro.entity.Membership;
import java.util.List;

public interface MembershipDAO extends CrudDAO<Membership, String> {
    Membership findByCustomer(String customerId) throws Exception;
    List<Membership> findByType(String type) throws Exception;
    List<Membership> findActiveMemberships() throws Exception;
    List<Membership> findExpiringMemberships(int daysBeforeExpiry) throws Exception;
    boolean renewMembership(String membershipId, String newEndDate) throws Exception;
}