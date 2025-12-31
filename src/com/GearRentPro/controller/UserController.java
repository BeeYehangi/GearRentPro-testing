// src/com/GearRentPro/controller/UserController.java
package com.GearRentPro.controller;

import com.GearRentPro.bo.BOFactory;
import com.GearRentPro.bo.custom.UserBO;
import com.GearRentPro.dto.UserDTO;
import java.util.List;

public class UserController {
    
    private final UserBO userBO;
    
    public UserController() {
        this.userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    }
    
    public boolean saveUser(String userId, String username, String password, 
                           String role, String branchId, boolean status) throws Exception {
        UserDTO userDTO = new UserDTO(userId, username, password, role, branchId, status);
        return userBO.saveUser(userDTO);
    }
    
    public boolean updateUser(String userId, String username, String password, 
                             String role, String branchId, boolean status) throws Exception {
        UserDTO userDTO = new UserDTO(userId, username, password, role, branchId, status);
        return userBO.updateUser(userDTO);
    }
    
    public boolean deleteUser(String userId) throws Exception {
        return userBO.deleteUser(userId);
    }
    
    public UserDTO findUser(String userId) throws Exception {
        return userBO.findUser(userId);
    }
    
    public UserDTO authenticate(String username, String password) throws Exception {
        return userBO.authenticate(username, password);
    }
    
    public List<UserDTO> getAllUsers() throws Exception {
        return userBO.findAllUsers();
    }
    
    public boolean changePassword(String userId, String newPassword) throws Exception {
        return userBO.updatePassword(userId, newPassword);
    }
    
    public UserDTO findByUsername(String username) throws Exception {
        return userBO.findByUsername(username);
    }
}