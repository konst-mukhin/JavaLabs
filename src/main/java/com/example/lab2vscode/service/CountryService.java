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
    public Optional<CountryModel> getCountryById(Integer country_id) {
        return countryRepository.findById(country_id);
    }

    public void deleteAllCountries() {
        countryRepository.deleteAll();
    }

    @SuppressWarnings("null")
    public void deleteCountry(Integer country_id) {
        countryRepository.deleteById(country_id);
    }

    public CountryModel updateCountry(Integer country_id, CountryModel countryDetails) {
        @SuppressWarnings("null")
        Optional<CountryModel> country = countryRepository.findById(country_id);
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
