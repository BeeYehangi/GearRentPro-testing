// BranchBOImpl.java - Complete fixed version:
package com.GearRentPro.bo.custom.impl;

import com.GearRentPro.bo.custom.BranchBO;
import com.GearRentPro.dao.DAOFactory;
import com.GearRentPro.dao.custom.BranchDAO;
import com.GearRentPro.dto.BranchDTO;
import com.GearRentPro.entity.Branch;
import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {
    
    private final BranchDAO branchDAO = (BranchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BRANCH);
    
    @Override
    public boolean saveBranch(BranchDTO branchDTO) throws Exception {
        // Create Branch entity with correct constructor parameters
        Branch branch = new Branch(
            branchDTO.getBranchId(),
            branchDTO.getName(),      // Use getName() not getLocation()
            branchDTO.getAddress(),   // Use getAddress()
            branchDTO.getContact(),   // Use getContact()
            branchDTO.getManagerId(), // Use getManagerId()
            branchDTO.isStatus()      // Use isStatus()
        );
        return branchDAO.save(branch);
    }
    
    @Override
    public boolean updateBranch(BranchDTO branchDTO) throws Exception {
        Branch branch = new Branch(
            branchDTO.getBranchId(),
            branchDTO.getName(),
            branchDTO.getAddress(),
            branchDTO.getContact(),
            branchDTO.getManagerId(),
            branchDTO.isStatus()
        );
        return branchDAO.update(branch);
    }
    
    @Override
    public boolean deleteBranch(String branchId) throws Exception {
        return branchDAO.delete(branchId);
    }
    
    @Override
    public BranchDTO findBranch(String branchId) throws Exception {
        Branch branch = branchDAO.find(branchId);
        if (branch == null) return null;
        
        // Create DTO with correct fields
        return new BranchDTO(
            branch.getBranchId(),
            branch.getName(),        // Use getName()
            branch.getAddress(),     // Use getAddress()
            branch.getContact(),
            branch.getManagerId(),   // Use getManagerId()
            branch.isStatus()        // Use isStatus()
        );
    }
    
    @Override
    public List<BranchDTO> findAllBranches() throws Exception {
        List<Branch> branches = branchDAO.findAll();
        List<BranchDTO> branchDTOs = new ArrayList<>();
        
        for (Branch branch : branches) {
            branchDTOs.add(new BranchDTO(
                branch.getBranchId(),
                branch.getName(),
                branch.getAddress(),
                branch.getContact(),
                branch.getManagerId(),
                branch.isStatus()
            ));
        }
        
        return branchDTOs;
    }
    
    @Override
    public int getActiveBranchCount() throws Exception {
        return branchDAO.getActiveBranchCount();
    }
}