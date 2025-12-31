// src/com/GearRentPro/dao/custom/BranchDAO.java
package com.GearRentPro.dao.custom;

import com.GearRentPro.dao.CrudDAO;
import com.GearRentPro.entity.Branch;

public interface BranchDAO extends CrudDAO<Branch, String> {
    // Add branch-specific methods here
    int getActiveBranchCount() throws Exception;
}