package com.example.lab2vscode.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.dto.CountryDTO;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
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
    
    public Country createCountry(Country countryModel) throws ServerException {
        if(countryRepository.findById(countryModel.getCountryId()).isPresent()){
            throw new ServerException("Change id");
        }
        Country country = countryRepository.save(countryModel);
        cache.put(country.getCountryId(), Optional.of(country));
        return country;
    }

    public List<CountryDTO> getAllCountries() throws NotFoundException {
        List<Country> countries = countryRepository.findAll();
        if(countries.isEmpty()){
            throw new NotFoundException("Countries list is empty");
        }
        return Arrays.asList(modelMapper.map(countries, CountryDTO[].class));
    }

    public CountryDTO getCountryById(Integer countryId) throws NotFoundException {
        Optional<Country> country;
        if(cache.containsKey(countryId)){
            country = cache.get(countryId);
        }
        else{
            country = countryRepository.findById(countryId);
            cache.put(countryId, country);
        }
        if(country.isEmpty()){
            throw new NotFoundException("No country with this id");
        }
        return modelMapper.map(country, CountryDTO.class);
    }

    public void deleteAllCountries() {
        cache.clear();
        countryRepository.deleteAll();
    }

    public void deleteCountry(Integer countryId) throws BadRequestException {
        if(countryRepository.findById(countryId).isEmpty()){
            throw new BadRequestException("Wrong id");
        }
        if(cache.containsKey(countryId)){
            cache.remove(countryId);
        }
        countryRepository.deleteById(countryId);
    }

    public void deleteRegionFromCountry(Integer countryId) throws BadRequestException{
        if(countryRepository.findById(countryId).isEmpty()){
            throw new BadRequestException("No country with this id");
        }
        countryRepository.deleteRegionFromCountry(countryId);
    }

    public CountryDTO updateCountry(Integer countryId, Country countryDetails) throws BadRequestException {
        Optional<Country> country;
        if(cache.containsKey(countryId)){
            country = cache.get(countryId);
        }
        else{
            country = countryRepository.findById(countryId);
            cache.put(countryId, country);
        }
        if(country.isEmpty()){
            throw new BadRequestException("Country is empty");
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
