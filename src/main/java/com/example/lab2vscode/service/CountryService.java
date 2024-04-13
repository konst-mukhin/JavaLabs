package com.example.lab2vscode.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.dto.CountryDTO;
import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.repository.CountryRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class CountryService {
    private CountryRepository countryRepository;
    private Cache<Integer, Optional<Country>> cache;
    private ModelMapper modelMapper;
    
    public Country createCountry(Country countryModel) {
        Country country = countryRepository.save(countryModel);
        cache.put(country.getCountryId(), Optional.of(country));
        return country;
    }

    public List<CountryDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return Arrays.asList(modelMapper.map(countries, CountryDTO[].class));
    }

    public CountryDTO getCountryById(Integer countryId) {
        Optional<Country> country;
        if(cache.containsKey(countryId)){
            country = cache.get(countryId);
        }
        else{
            country = countryRepository.findById(countryId);
            cache.put(countryId, country);
        }
        return modelMapper.map(country, CountryDTO.class);
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

    public CountryDTO updateCountry(Integer countryId, Country countryDetails) {
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
            countryRepository.save(existingCountry);
            return modelMapper.map(existingCountry, CountryDTO.class);
        }
        return null;
    }
}
