// src/com/GearRentPro/bo/BOFactory.java
package com.GearRentPro.bo;

import com.GearRentPro.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    
    private BOFactory() {}
    
    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    
    public enum BOTypes {
        USER, BRANCH, CATEGORY, CUSTOMER, EQUIPMENT, 
        RESERVATION, RENTAL, MEMBERSHIP
    }
    
    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case USER: return new UserBOImpl();
            case BRANCH: return new BranchBOImpl();
            case CATEGORY: return new CategoryBOImpl();
            case CUSTOMER: return new CustomerBOImpl();
            case EQUIPMENT: return new EquipmentBOImpl();
            case RESERVATION: return new ReservationBOImpl();
            case RENTAL: return new RentalBOImpl();
            case MEMBERSHIP: return new MembershipBOImpl();
            default: return null;
        }
    }
}