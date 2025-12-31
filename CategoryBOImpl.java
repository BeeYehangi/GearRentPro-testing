// CategoryBOImpl.java - Updated with all 7 parameters
package com.GearRentPro.bo.custom.impl;

import com.GearRentPro.bo.custom.CategoryBO;
import com.GearRentPro.dao.DAOFactory;
import com.GearRentPro.dao.custom.CategoryDAO;
import com.GearRentPro.dto.CategoryDTO;
import com.GearRentPro.entity.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryBOImpl implements CategoryBO {
    
    private final CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CATEGORY);
    
    @Override
    public boolean saveCategory(CategoryDTO categoryDTO) throws Exception {
        Category category = new Category(
            categoryDTO.getCategoryId(),
            categoryDTO.getName(),
            categoryDTO.getDescription(),
            categoryDTO.getBasePriceFactor(),    // Add this
            categoryDTO.getWeekendMultiplier(),  // Add this
            categoryDTO.getLateFeePerDay(),      // Add this
            categoryDTO.isStatus()
        );
        return categoryDAO.save(category);
    }
    
    @Override
    public boolean updateCategory(CategoryDTO categoryDTO) throws Exception {
        Category category = new Category(
            categoryDTO.getCategoryId(),
            categoryDTO.getName(),
            categoryDTO.getDescription(),
            categoryDTO.getBasePriceFactor(),    // Add this
            categoryDTO.getWeekendMultiplier(),  // Add this
            categoryDTO.getLateFeePerDay(),      // Add this
            categoryDTO.isStatus()
        );
        return categoryDAO.update(category);
    }
    
    @Override
    public boolean deleteCategory(String categoryId) throws Exception {
        return categoryDAO.delete(categoryId);
    }
    
    @Override
    public CategoryDTO findCategory(String categoryId) throws Exception {
        Category category = categoryDAO.find(categoryId);
        if (category == null) return null;
        
        return new CategoryDTO(
            category.getCategoryId(),
            category.getName(),
            category.getDescription(),
            category.getBasePriceFactor(),    // Add this
            category.getWeekendMultiplier(),  // Add this
            category.getLateFeePerDay(),      // Add this
            category.isStatus()
        );
    }
    
    @Override
    public List<CategoryDTO> findAllCategories() throws Exception {
        List<Category> categories = categoryDAO.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        
        for (Category category : categories) {
            categoryDTOs.add(new CategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription(),
                category.getBasePriceFactor(),    // Add this
                category.getWeekendMultiplier(),  // Add this
                category.getLateFeePerDay(),      // Add this
                category.isStatus()
            ));
        }
        
        return categoryDTOs;
    }
    
    @Override
    public boolean isCategoryInUse(String categoryId) throws Exception {
        return categoryDAO.isCategoryInUse(categoryId);
    }
}