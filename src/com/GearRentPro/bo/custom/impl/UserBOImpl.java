// src/com/GearRentPro/bo/custom/impl/UserBOImpl.java
package com.GearRentPro.bo.custom.impl;

import com.GearRentPro.bo.custom.UserBO;
import com.GearRentPro.dao.DAOFactory;
import com.GearRentPro.dao.custom.UserDAO;
import com.GearRentPro.dto.UserDTO;
import com.GearRentPro.entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    
    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    
    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        User user = new User(
            userDTO.getUserId(),
            userDTO.getUsername(),
            userDTO.getPassword(),
            userDTO.getRole(),
            userDTO.getBranchId(),
            userDTO.isStatus()
        );
        return userDAO.save(user);
    }
    
    @Override
    public UserDTO authenticate(String username, String password) throws Exception {
        User user = userDAO.findByUsername(username);
        
        if (user == null || !user.getPassword().equals(password)) {
            throw new Exception("Invalid username or password!");
        }
        
        return new UserDTO(
            user.getUserId(),
            user.getUsername(),
            user.getRole(),
            user.getBranchId()
        );
    }
    
    @Override
    public UserDTO findUser(String userId) throws Exception {
        User user = userDAO.find(userId);
        if (user == null) return null;
        
        return new UserDTO(
            user.getUserId(),
            user.getUsername(),
            user.getRole(),
            user.getBranchId()
        );
    }
    
    @Override
    public UserDTO findByUsername(String username) throws Exception {
        User user = userDAO.findByUsername(username);
        if (user == null) return null;
        
        return new UserDTO(
            user.getUserId(),
            user.getUsername(),
            user.getRole(),
            user.getBranchId()
        );
    }
    
    @Override
    public List<UserDTO> findAllUsers() throws Exception {
        List<User> users = userDAO.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        
        for (User user : users) {
            userDTOs.add(new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRole(),
                user.getBranchId()
            ));
        }
        
        return userDTOs;
    }
    
    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        User user = new User(
            userDTO.getUserId(),
            userDTO.getUsername(),
            userDTO.getPassword(),
            userDTO.getRole(),
            userDTO.getBranchId(),
            userDTO.isStatus()
        );
        return userDAO.update(user);
    }
    
    @Override
    public boolean deleteUser(String userId) throws Exception {
        return userDAO.delete(userId);
    }
    
    @Override
    public boolean updatePassword(String userId, String newPassword) throws Exception {
        return userDAO.updatePassword(userId, newPassword);
    }
}