// src/com/GearRentPro/dao/custom/CustomerDAO.java
package com.GearRentPro.dao.custom;

import com.GearRentPro.dao.CrudDAO;
import com.GearRentPro.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, String> {
    Customer findByNIC(String nicPassport) throws Exception;
    Customer findByContact(String contactNo) throws Exception;
    Customer findByEmail(String email) throws Exception;
}