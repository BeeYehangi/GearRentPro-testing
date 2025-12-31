// src/com/GearRentPro/bo/custom/CustomerBO.java
package com.GearRentPro.bo.custom;

import com.GearRentPro.bo.SuperBO;
import com.GearRentPro.dto.CustomerDTO;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO customerDTO) throws Exception;
    boolean updateCustomer(CustomerDTO customerDTO) throws Exception;
    boolean deleteCustomer(String customerId) throws Exception;
    CustomerDTO findCustomer(String customerId) throws Exception;
    CustomerDTO findCustomerByNIC(String nic) throws Exception;
    CustomerDTO findCustomerByContact(String contact) throws Exception;
    CustomerDTO findCustomerByEmail(String email) throws Exception;
    List<CustomerDTO> findAllCustomers() throws Exception;
}