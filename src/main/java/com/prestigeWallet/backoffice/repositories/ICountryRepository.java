package com.prestigeWallet.backoffice.repositories;

import com.prestigeWallet.backoffice.entities.Country;

public interface ICountryRepository {
    Country getById(Integer id);
    void addCountry(Country country);
}
