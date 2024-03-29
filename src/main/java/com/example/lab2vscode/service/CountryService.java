package com.example.lab2vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab2vscode.model.CountryModel;
import com.example.lab2vscode.repository.CountryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;
    
    @SuppressWarnings("null")
    public CountryModel createCountry(CountryModel countryModel) {
        return countryRepository.save(countryModel);
    }

    public List<CountryModel> getAllCountries() {
        return countryRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<CountryModel> getCountryById(Integer countryId) {
        return countryRepository.findById(countryId);
    }

    public void deleteAllCountries() {
        countryRepository.deleteAll();
    }

    @SuppressWarnings("null")
    public void deleteCountry(Integer countryId) {
        countryRepository.deleteById(countryId);
    }

    public CountryModel updateCountry(Integer countryId, CountryModel countryDetails) {
        @SuppressWarnings("null")
        Optional<CountryModel> country = countryRepository.findById(countryId);
        if (country.isPresent()) {
            CountryModel existingCountry = country.get();
            existingCountry.setName(countryDetails.getName());
            existingCountry.setCapital(countryDetails.getCapital());
            existingCountry.setPopulation(countryDetails.getPopulation());
            return countryRepository.save(existingCountry);
        }
        return null;
    }
}
