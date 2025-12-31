// src/com/GearRentPro/bo/custom/CategoryBO.java
package com.GearRentPro.bo.custom;

import com.GearRentPro.bo.SuperBO;
import com.GearRentPro.dto.CategoryDTO;
import java.util.List;

public interface CategoryBO extends SuperBO {
    boolean saveCategory(CategoryDTO categoryDTO) throws Exception;
    boolean updateCategory(CategoryDTO categoryDTO) throws Exception;
    boolean deleteCategory(String categoryId) throws Exception;
    CategoryDTO findCategory(String categoryId) throws Exception;
    List<CategoryDTO> findAllCategories() throws Exception;
    boolean isCategoryInUse(String categoryId) throws Exception;
}