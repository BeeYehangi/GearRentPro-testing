// src/com/GearRentPro/dao/custom/impl/CategoryDAOImpl.java
package com.GearRentPro.dao.custom.impl;

import com.GearRentPro.dao.custom.CategoryDAO;
import com.GearRentPro.entity.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    
    @Override
    public boolean save(Category category) throws Exception {
        System.out.println("Saving category: " + category);
        return true;
    }
    
    @Override
    public boolean update(Category category) throws Exception {
        System.out.println("Updating category: " + category);
        return true;
    }
    
    @Override
    public boolean delete(String categoryId) throws Exception {
        System.out.println("Deleting category: " + categoryId);
        return true;
    }
    
    @Override
    public Category find(String categoryId) throws Exception {
        System.out.println("Finding category: " + categoryId);
        return null;
    }
    
    @Override
    public List<Category> findAll() throws Exception {
        System.out.println("Finding all categories");
        return new ArrayList<>();
    }
    
    @Override
    public boolean isCategoryInUse(String categoryId) throws Exception {
        System.out.println("Checking if category is in use: " + categoryId);
        return false;
    }
}