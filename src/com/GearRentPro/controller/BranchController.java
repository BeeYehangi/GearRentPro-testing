// src/com/GearRentPro/controller/BranchController.java
package com.GearRentPro.controller;

import com.GearRentPro.bo.BOFactory;
import com.GearRentPro.bo.custom.BranchBO;
import com.GearRentPro.dto.BranchDTO;
import java.util.List;

public class BranchController {
    
    private final BranchBO branchBO;
    
    public BranchController() {
        this.branchBO = (BranchBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BRANCH);
    }
    
    public boolean saveBranch(BranchDTO branchDTO) throws Exception {
        return branchBO.saveBranch(branchDTO);
    }
    
    public boolean updateBranch(BranchDTO branchDTO) throws Exception {
        return branchBO.updateBranch(branchDTO);
    }
    
    public boolean deleteBranch(String branchId) throws Exception {
        return branchBO.deleteBranch(branchId);
    }
    
    public BranchDTO findBranch(String branchId) throws Exception {
        return branchBO.findBranch(branchId);
    }
    
    public List<BranchDTO> getAllBranches() throws Exception {
        return branchBO.findAllBranches();
    }
}