package com.prestigeWallet.backoffice.services;


import com.prestigeWallet.backoffice.dto.MaterialDTO;

public interface IMaterialService {

    MaterialDTO getMaterialById(Integer id);
    MaterialDTO getMaterialByName(String name);
    void addMaterial(MaterialDTO materialDTO);
}
