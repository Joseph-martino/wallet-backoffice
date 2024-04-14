package com.prestigeWallet.backoffice.repositories;


import com.prestigeWallet.backoffice.entities.Coulour;

import java.util.Set;

public interface ICoulourRepository {

    Coulour getById(Integer id);
    Set<Coulour> getAllCoulours();
    Coulour getByName(String coulourName);
    void addCoulour(Coulour coulour);
}
