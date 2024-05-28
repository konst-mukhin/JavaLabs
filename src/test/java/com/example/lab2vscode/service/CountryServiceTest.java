package com.example.lab2vscode.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.dto.CountryDto;
import com.example.lab2vscode.dto.LanguageDto;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.model.Language;
import com.example.lab2vscode.repository.CountryRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {
  @Mock private CountryRepository countryRepository;

  @Mock private Cache<Integer, Optional<Country>> cache;

  @Spy private ModelMapper modelMapper = new ModelMapper();

  @InjectMocks private CountryService countryService;

  private Country country;
  private CountryDto countryDto;
  private Language language;
  private LanguageDto languageDto;

  @BeforeEach
  void setUp() {
    country = new Country();
    countryDto = new CountryDto();
    language = new Language();
    languageDto = new LanguageDto();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    country.setCountryId(1);
    country.setCapital("capital");
    country.setPopulation(1);
    country.setName("name");
    country.setLanguages(new ArrayList<>());
    country.getLanguages().add(language);

    countryDto.setCapital("capital");
    countryDto.setPopulation(1);
    countryDto.setName("name");
    countryDto.setLanguages(new ArrayList<>());
    countryDto.getLanguages().add(languageDto);

    language.setName("name");
    language.setLanguageId(1);
    language.setCountries(new HashSet<>());
    language.getCountries().add(country);

    languageDto.setName("name");
  }

  @Test
  void createCountryTest() throws ServerException {
    when(countryRepository.findById(1)).thenReturn(Optional.empty());
    when(countryRepository.save(country)).thenReturn(country);

    Country result = countryService.createCountry(country);

    assertEquals(country, result);
    verify(cache, times(1)).put(1, Optional.of(country));
  }

  @Test
  void createCountryExceptionTest() {
    when(countryRepository.findById(1)).thenReturn(Optional.of(country));

    assertThrows(ServerException.class, () -> countryService.createCountry(country));
  }

  @Test
  void getAllCountriesTest() throws NotFoundException {
    when(countryRepository.findAll()).thenReturn(Arrays.asList(country, country));

    List<CountryDto> result =
        Arrays.asList(modelMapper.map(countryService.getAllCountries(), CountryDto[].class));

    assertEquals(2, result.size());
    assertEquals(countryDto, result.get(0));
  }

  @Test
  void getAllCountriesExceptionTest() {
    when(countryRepository.findAll()).thenReturn(Arrays.asList());

    assertThrows(NotFoundException.class, () -> countryService.getAllCountries());
  }

  @Test
  void getCountryByIdTest() throws NotFoundException {
    when(cache.containsKey(1)).thenReturn(false);
    when(countryRepository.findById(1)).thenReturn(Optional.of(country));

    CountryDto result = countryService.getCountryById(1);

    assertEquals(countryDto, result);
    verify(cache, times(1)).put(1, Optional.of(country));
  }

  @Test
  void getCountryByIdCacheTest() throws NotFoundException {
    when(cache.containsKey(1)).thenReturn(true);
    when(cache.get(1)).thenReturn(Optional.of(country));

    CountryDto result = countryService.getCountryById(1);

    assertEquals(countryDto, result);
  }

  @Test
  void getCountryByIdExceptionTest() {
    when(cache.containsKey(1)).thenReturn(false);
    when(countryRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> countryService.getCountryById(1));
  }

  @Test
  void updateRegionTest() throws BadRequestException {
    when(cache.containsKey(1)).thenReturn(false);
    when(countryRepository.findById(1)).thenReturn(Optional.of(country));

    Country result = countryService.updateCountry(1, country);

    assertEquals(country, result);
    verify(cache, times(1)).put(1, Optional.of(country));
  }

  @Test
  void updateRegionCacheTest() throws BadRequestException {
    when(cache.containsKey(1)).thenReturn(true);
    when(cache.get(1)).thenReturn(Optional.of(country));

    Country result = countryService.updateCountry(1, country);

    assertEquals(country, result);
  }

  @Test
  void updateCountryExceptionTest() {
    when(countryRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(BadRequestException.class, () -> countryService.updateCountry(1, country));
  }

  @Test
  void createCountriesTest() throws ServerException {
    when(countryRepository.findAll()).thenReturn(Arrays.asList());

    List<Country> result = countryService.createCountries(Arrays.asList(country));

    assertEquals(1, result.size());
  }

  @Test
  void createCountriesExceptionTest() {
    List<Country> countries = new ArrayList<>();
    countries.add(country);
    countries.add(country);

    when(countryRepository.findAll()).thenReturn(Arrays.asList(country, country));

    assertThrows(ServerException.class, () -> countryService.createCountries(countries));
  }

  @Test
  void getCountryByNameTest() throws NotFoundException {
    when(countryRepository.findByName("name")).thenReturn(Optional.of(country));

    Country result = countryService.getCountryByName("name");

    assertEquals(result, country);
  }

  @Test
  void getCountryByNameExceptionTest() {
    when(countryRepository.findByName(any(String.class))).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> countryService.getCountryByName("name"));
  }
}
