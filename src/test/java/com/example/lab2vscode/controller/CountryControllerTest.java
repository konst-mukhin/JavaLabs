package com.example.lab2vscode.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.lab2vscode.dto.CountryDto;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.service.CounterService;
import com.example.lab2vscode.service.CountryService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CountryControllerTest {

  @Mock private CountryService countryService;

  @Mock private CounterService counterService;

  @InjectMocks private CountryController countryController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    counterService = new CounterService();
  }

  @Test
  void createCountryTest() throws ServerException {
    Country country = new Country();
    when(countryService.createCountry(country)).thenReturn(country);
    Country result = countryController.createCountry(country);
    assertEquals(country, result);
  }

  @Test
  void getAllCountriesTest() throws NotFoundException {
    List<CountryDto> countryDtos =
        Arrays.asList(new CountryDto(), new CountryDto(), new CountryDto());
    when(countryService.getAllCountries()).thenReturn(countryDtos);
    List<CountryDto> result = countryController.getAllCountries();
    assertEquals(countryDtos, result);
  }

  @Test
  void getCountryByIdTest() throws NotFoundException {
    CountryDto countryDto = new CountryDto();
    when(countryService.getCountryById(1)).thenReturn(countryDto);
    CountryDto result = countryController.getCountryById(1);
    assertEquals(countryDto, result);
  }

  @Test
  void deleteAllCountriesTest() {
    doNothing().when(countryService).deleteAllCountries();
    String result = countryController.deleteAllCountries();
    assertEquals("ops-s-s", result);
  }

  @Test
  void updateCountryTest() throws BadRequestException {
    Country updatedCountry = new Country();
    when(countryService.updateCountry(1, updatedCountry)).thenReturn(updatedCountry);
    Country result = countryController.updateCountry(1, updatedCountry);
    assertEquals(updatedCountry, result);
  }

  @Test
  void createCountriesTest() throws ServerException {
    List<Country> countries = Arrays.asList(new Country(), new Country(), new Country());
    when(countryService.createCountries(countries)).thenReturn(countries);
    List<Country> result = countryController.createCountries(countries);
    assertEquals(countries, result);
  }

  @Test
  void getCountryByNameTest() throws NotFoundException {
    Country country = new Country();
    when(countryService.getCountryByName("name")).thenReturn(country);
    Country result = countryController.getCountryByName("name");
    assertEquals(result, country);
  }
}