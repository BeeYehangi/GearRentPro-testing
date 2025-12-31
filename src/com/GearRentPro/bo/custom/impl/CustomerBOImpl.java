// src/com/GearRentPro/bo/custom/impl/CustomerBOImpl.java
package com.GearRentPro.bo.custom.impl;

import com.GearRentPro.bo.custom.CustomerBO;
import com.GearRentPro.dao.DAOFactory;
import com.GearRentPro.dao.custom.CustomerDAO;
import com.GearRentPro.dto.CustomerDTO;
import com.GearRentPro.entity.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    
    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws Exception {
        Customer customer = new Customer(
            customerDTO.getCustomerId(),
            customerDTO.getName(),
            customerDTO.getNicPassport(),      // Use getNicPassport()
            customerDTO.getContactNo(),        // Use getContactNo()
            customerDTO.getEmail(),
            customerDTO.getAddress(),
            customerDTO.getMembershipLevel(),  // Use getMembershipLevel()
            customerDTO.isStatus()
        );
        return customerDAO.save(customer);
    }
    
    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        Customer customer = new Customer(
            customerDTO.getCustomerId(),
            customerDTO.getName(),
            customerDTO.getNicPassport(),
            customerDTO.getContactNo(),
            customerDTO.getEmail(),
            customerDTO.getAddress(),
            customerDTO.getMembershipLevel(),
            customerDTO.isStatus()
        );
        return customerDAO.update(customer);
    }
    
    @Override
    public boolean deleteCustomer(String customerId) throws Exception {
        return customerDAO.delete(customerId);
    }
    
    @Override
    public CustomerDTO findCustomer(String customerId) throws Exception {
        Customer customer = customerDAO.find(customerId);
        if (customer == null) return null;
        
        return new CustomerDTO(
            customer.getCustomerId(),
            customer.getName(),
            customer.getNicPassport(),
            customer.getContactNo(),
            customer.getEmail(),
            customer.getAddress(),
            customer.getMembershipLevel(),
            customer.isStatus()
        );
    }
    
    @Override
    public CustomerDTO findCustomerByNIC(String nicPassport) throws Exception {  // Change parameter name
        Customer customer = customerDAO.findByNIC(nicPassport);
        if (customer == null) return null;
        
        return new CustomerDTO(
            customer.getCustomerId(),
            customer.getName(),
            customer.getNicPassport(),
            customer.getContactNo(),
            customer.getEmail(),
            customer.getAddress(),
            customer.getMembershipLevel(),
            customer.isStatus()
        );
    }
    
    @Override
    public CustomerDTO findCustomerByContact(String contactNo) throws Exception {  // Change parameter name
        Customer customer = customerDAO.findByContact(contactNo);
        if (customer == null) return null;
        
        return new CustomerDTO(
            customer.getCustomerId(),
            customer.getName(),
            customer.getNicPassport(),
            customer.getContactNo(),
            customer.getEmail(),
            customer.getAddress(),
            customer.getMembershipLevel(),
            customer.isStatus()
        );
    }
    
    @Override
    public CustomerDTO findCustomerByEmail(String email) throws Exception {
        Customer customer = customerDAO.findByEmail(email);
        if (customer == null) return null;
        
        return new CustomerDTO(
            customer.getCustomerId(),
            customer.getName(),
            customer.getNicPassport(),
            customer.getContactNo(),
            customer.getEmail(),
            customer.getAddress(),
            customer.getMembershipLevel(),
            customer.isStatus()
        );
    }
    
    @Override
    public List<CustomerDTO> findAllCustomers() throws Exception {
        List<Customer> customers = customerDAO.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(
                customer.getCustomerId(),
                customer.getName(),
                customer.getNicPassport(),
                customer.getContactNo(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getMembershipLevel(),
                customer.isStatus()
            ));
        }
        
        return customerDTOs;
    }
}