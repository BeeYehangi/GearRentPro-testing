// src/com/GearRentPro/dao/custom/UserDAO.java
package com.GearRentPro.dao.custom;

import com.GearRentPro.dao.CrudDAO;
import com.GearRentPro.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
    User findByUsername(String username) throws Exception;      // Add throws Exception
    boolean updatePassword(String userId, String newPassword) throws Exception;  // Add throws Exception
    boolean updateStatus(String userId, boolean status) throws Exception;        // Add throws Exception
}