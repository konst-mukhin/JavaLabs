package com.example.lab2vscode.service;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.dto.CountryDto;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.repository.CountryRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class CountryService {
  private CountryRepository countryRepository;
  private Cache<Integer, Optional<Country>> cache;
  private ModelMapper modelMapper;

  public Country createCountry(Country countryModel) throws ServerException {
    if (countryRepository.findById(countryModel.getCountryId()).isPresent()) {
      throw new ServerException("Change id");
    }
    Country country = countryRepository.save(countryModel);
    cache.put(country.getCountryId(), Optional.of(country));
    return country;
  }

  public List<CountryDto> getAllCountries() throws NotFoundException {
    List<Country> countries = countryRepository.findAll();
    if (countries.isEmpty()) {
      throw new NotFoundException("Countries list is empty");
    }
    return countries.stream()
        .map(country -> modelMapper.map(country, CountryDto.class))
        .collect(Collectors.toList());
  }

  public CountryDto getCountryById(Integer countryId) throws NotFoundException {
    Optional<Country> country;
    if (cache.containsKey(countryId)) {
      country = cache.get(countryId);
    } else {
      country = countryRepository.findById(countryId);
      cache.put(countryId, country);
    }
    if (country.isEmpty()) {
      throw new NotFoundException("No country with this id");
    }
    return modelMapper.map(country, CountryDto.class);
  }

  public Country getCountryByName(String name) throws NotFoundException {
    Optional<Country> country = countryRepository.findByName(name);
    if (country.isEmpty()) {
      throw new NotFoundException("No country with this id");
    }
    return country.get();
  }

  public void deleteAllCountries() {
    cache.clear();
    countryRepository.deleteAll();
  }

  public void deleteRegionFromCountry(Integer countryId) throws BadRequestException {
    if (countryRepository.findById(countryId).isEmpty()) {
      throw new BadRequestException("Wrong country with this id");
    }
    countryRepository.deleteRegionFromCountry(countryId);
  }

  public Country updateCountry(Integer countryId, Country countryDetails)
      throws BadRequestException {
    Optional<Country> country;
    if (cache.containsKey(countryId)) {
      country = cache.get(countryId);
    } else {
      country = countryRepository.findById(countryId);
      cache.put(countryId, country);
    }
    if (country.isEmpty()) {
      throw new BadRequestException("Country is empty");
    }
    if (country.isPresent()) {
      Country existingCountry = country.get();
      existingCountry.setName(countryDetails.getName());
      existingCountry.setCapital(countryDetails.getCapital());
      existingCountry.setPopulation(countryDetails.getPopulation());
      countryRepository.save(existingCountry);
      return existingCountry;
    }
    return null;
  }

  public List<Country> createCountries(List<Country> countries) throws ServerException {
    List<Country> presentCountries = countryRepository.findAll();
    for (Country presentCountry : presentCountries) {
      for (Country country : countries) {
        if (presentCountry.getCountryId().equals(country.getCountryId())) {
          throw new ServerException("Wrong id");
        }
      }
    }
    countryRepository.saveAll(countries);
    return countries;
  }
}
