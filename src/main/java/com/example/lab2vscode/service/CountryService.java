package com.example.lab2vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.repository.CountryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;
    private Cache<Integer, Optional<Country>> cache;
    
    public Country createCountry(Country countryModel) {
        Country country = countryRepository.save(countryModel);
        cache.put(country.getCountryId(), Optional.of(country));
        return country;
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryById(Integer countryId) {
        Optional<Country> country;
        if(cache.containsKey(countryId)){
            country = cache.get(countryId);
        }
        else{
            country = countryRepository.findById(countryId);
            cache.put(countryId, country);
        }
        return country;
    }

    public void deleteAllCountries() {
        cache.clear();
        countryRepository.deleteAll();
    }

    public void deleteCountry(Integer countryId) {
        if(cache.containsKey(countryId)){
            cache.remove(countryId);
        }
        countryRepository.deleteById(countryId);
    }

    public void deleteRegionFromCountry(Integer countryId){
        countryRepository.deleteRegionFromCountry(countryId);
    }

    public Country updateCountry(Integer countryId, Country countryDetails) {
        Optional<Country> country;
        if(cache.containsKey(countryId)){
            country = cache.get(countryId);
        }
        else{
            country = countryRepository.findById(countryId);
            cache.put(countryId, country);
        }
        if (country.isPresent()) {
            Country existingCountry = country.get();
            existingCountry.setName(countryDetails.getName());
            existingCountry.setCapital(countryDetails.getCapital());
            existingCountry.setPopulation(countryDetails.getPopulation());
            return countryRepository.save(existingCountry);
        }
        return null;
    }
}
