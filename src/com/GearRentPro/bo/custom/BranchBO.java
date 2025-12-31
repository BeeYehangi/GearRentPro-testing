// src/com/GearRentPro/bo/custom/BranchBO.java
package com.GearRentPro.bo.custom;

import com.GearRentPro.bo.SuperBO;
import com.GearRentPro.dto.BranchDTO;
import java.util.List;

public interface BranchBO extends SuperBO {
    boolean saveBranch(BranchDTO branchDTO) throws Exception;
    boolean updateBranch(BranchDTO branchDTO) throws Exception;
    boolean deleteBranch(String branchId) throws Exception;
    BranchDTO findBranch(String branchId) throws Exception;
    List<BranchDTO> findAllBranches() throws Exception;
    int getActiveBranchCount() throws Exception;
}