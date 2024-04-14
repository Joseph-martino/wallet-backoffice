package com.prestigeWallet.backoffice.services;
import com.prestigeWallet.backoffice.dto.CategoryDTO;

public interface ICategoryService {
    CategoryDTO getCategoryById(Integer id);
    CategoryDTO getCategoryByName(String name);
    void addCategory(CategoryDTO categoryDTO);
}
