// src/com/GearRentPro/controller/CustomerController.java
package com.GearRentPro.controller;

import com.GearRentPro.bo.BOFactory;
import com.GearRentPro.bo.custom.CustomerBO;
import com.GearRentPro.dto.CustomerDTO;
import java.util.List;

public class CustomerController {
    
    private final CustomerBO customerBO;
    
    public CustomerController() {
        this.customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    }
    
    public boolean saveCustomer(CustomerDTO customerDTO) throws Exception {
        return customerBO.saveCustomer(customerDTO);
    }
    
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        return customerBO.updateCustomer(customerDTO);
    }
    
    public boolean deleteCustomer(String customerId) throws Exception {
        return customerBO.deleteCustomer(customerId);
    }
    
    public CustomerDTO findCustomer(String customerId) throws Exception {
        return customerBO.findCustomer(customerId);
    }
    
    public CustomerDTO findCustomerByNIC(String nic) throws Exception {
        return customerBO.findCustomerByNIC(nic);
    }
    
    public CustomerDTO findCustomerByContact(String contact) throws Exception {
        return customerBO.findCustomerByContact(contact);
    }
    
    public CustomerDTO findCustomerByEmail(String email) throws Exception {
        return customerBO.findCustomerByEmail(email);
    }
    
    public List<CustomerDTO> getAllCustomers() throws Exception {
        return customerBO.findAllCustomers();
    }
}