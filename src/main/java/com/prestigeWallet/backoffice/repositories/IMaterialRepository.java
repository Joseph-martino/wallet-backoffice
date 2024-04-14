package com.prestigeWallet.backoffice.repositories;


import com.prestigeWallet.backoffice.entities.Material;

public interface IMaterialRepository {

    Material getById(Integer id);
    Material getByName(String name);
    void save(Material material);
}
