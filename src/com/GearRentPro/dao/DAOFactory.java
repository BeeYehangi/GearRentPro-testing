// src/com/GearRentPro/dao/DAOFactory.java
package com.GearRentPro.dao;

import com.GearRentPro.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    
    private DAOFactory() {}
    
    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    
    // Add ALL entity types here:
    public enum DAOTypes {
        USER,
        BRANCH,
        CATEGORY,
        CUSTOMER,
        EQUIPMENT,
        RESERVATION,
        RENTAL,
        MEMBERSHIP
    }
    
    public SuperDAO getDAO(DAOTypes type) {
        switch (type) {
            case USER: return new UserDAOImpl();
            case BRANCH: return new BranchDAOImpl();
            case CATEGORY: return new CategoryDAOImpl();
            case CUSTOMER: return new CustomerDAOImpl();
            case EQUIPMENT: return new EquipmentDAOImpl();
            case RESERVATION: return new ReservationDAOImpl();
            case RENTAL: return new RentalDAOImpl();
            case MEMBERSHIP: return new MembershipDAOImpl();
            default: return null;
        }
    }
}