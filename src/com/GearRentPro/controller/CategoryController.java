// src/com/GearRentPro/controller/CategoryController.java
package com.GearRentPro.controller;

import com.GearRentPro.bo.BOFactory;
import com.GearRentPro.bo.custom.CategoryBO;
import com.GearRentPro.dto.CategoryDTO;
import java.util.List;

public class CategoryController {
    
    private final CategoryBO categoryBO;
    
    public CategoryController() {
        this.categoryBO = (CategoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CATEGORY);
    }
    
    public boolean saveCategory(CategoryDTO categoryDTO) throws Exception {
        return categoryBO.saveCategory(categoryDTO);
    }
    
    public boolean updateCategory(CategoryDTO categoryDTO) throws Exception {
        return categoryBO.updateCategory(categoryDTO);
    }
    
    public boolean deleteCategory(String categoryId) throws Exception {
        return categoryBO.deleteCategory(categoryId);
    }
    
    public CategoryDTO findCategory(String categoryId) throws Exception {
        return categoryBO.findCategory(categoryId);
    }
    
    public List<CategoryDTO> getAllCategories() throws Exception {
        return categoryBO.findAllCategories();
    }
}