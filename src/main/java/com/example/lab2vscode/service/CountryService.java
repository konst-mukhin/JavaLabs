package com.example.lab2vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.repository.CountryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;
    
    public Country createCountry(Country countryModel) {
        return countryRepository.save(countryModel);
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryById(Integer countryId) {
        return countryRepository.findById(countryId);
    }

    public void deleteAllCountries() {
        countryRepository.deleteAll();
    }

    public void deleteCountry(Integer countryId) {
        countryRepository.deleteById(countryId);
    }

    public Country updateCountry(Integer countryId, Country countryDetails) {
        Optional<Country> country = countryRepository.findById(countryId);
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
