// src/com/GearRentPro/dao/custom/CategoryDAO.java
package com.GearRentPro.dao.custom;

import com.GearRentPro.dao.CrudDAO;
import com.GearRentPro.entity.Category;

public interface CategoryDAO extends CrudDAO<Category, String> {
    // Add category-specific methods here
    boolean isCategoryInUse(String categoryId) throws Exception;
}