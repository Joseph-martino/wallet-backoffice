package com.prestigeWallet.backoffice.services;

import com.prestigeWallet.backoffice.dto.CoulourDTO;
import com.prestigeWallet.backoffice.entities.Coulour;

import java.util.Set;

public interface ICoulourService {

    CoulourDTO getCoulourById(Integer id);
    Coulour getCoulourByName(String name);
    Set<CoulourDTO> getAllCoulours();
    void addCoulour(CoulourDTO coulour);
}
