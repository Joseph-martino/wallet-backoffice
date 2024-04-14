package com.prestigeWallet.backoffice.services;


import com.prestigeWallet.backoffice.dto.CountryDTO;

public interface ICountryService {

    CountryDTO getCountryById(Integer id);
    void addCountry(CountryDTO countryDTO);
}
