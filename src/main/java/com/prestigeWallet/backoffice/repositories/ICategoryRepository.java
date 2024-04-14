package com.prestigeWallet.backoffice.repositories;
import com.prestigeWallet.backoffice.entities.Category;

public interface ICategoryRepository {
    Category getById(Integer id);
    Category getByName(String name);
    void save(Category category);
}
