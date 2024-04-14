package com.prestigeWallet.backoffice.services;



import com.prestigeWallet.backoffice.dto.CountryDTO;
import com.prestigeWallet.backoffice.entities.Country;
import com.prestigeWallet.backoffice.repositories.CountryRepositoryImpl;
import com.prestigeWallet.backoffice.repositories.ICountryRepository;
import org.modelmapper.ModelMapper;


public class CountryServiceImpl implements ICountryService{

    private ICountryRepository countryRepository;
    private ModelMapper modelMapper;

    public CountryServiceImpl(CountryRepositoryImpl countryRepository, ModelMapperProvider modelMapperProvider){
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapperProvider.getModelMapper();
    }

    public CountryDTO getCountryById(Integer id){
        Country country = this.countryRepository.getById(id);
        return this.modelMapper.map(country, CountryDTO.class);
    }

    public void addCountry(CountryDTO countryDTO){
        Country country = this.modelMapper.map(countryDTO, Country.class);
        this.countryRepository.addCountry(country);
    }
}
