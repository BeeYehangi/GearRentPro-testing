// src/com/GearRentPro/bo/custom/UserBO.java
package com.GearRentPro.bo.custom;

import com.GearRentPro.bo.SuperBO;
import com.GearRentPro.dto.UserDTO;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDTO userDTO) throws Exception;
    boolean updateUser(UserDTO userDTO) throws Exception;
    boolean deleteUser(String userId) throws Exception;
    UserDTO findUser(String userId) throws Exception;
    UserDTO findByUsername(String username) throws Exception;
    List<UserDTO> findAllUsers() throws Exception;
    UserDTO authenticate(String username, String password) throws Exception;
    boolean updatePassword(String userId, String newPassword) throws Exception;
}