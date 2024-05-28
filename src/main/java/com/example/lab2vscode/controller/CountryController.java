package com.example.lab2vscode.controller;

import com.example.lab2vscode.dto.CountryDto;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.service.CounterService;
import com.example.lab2vscode.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/country")
@RestController
@AllArgsConstructor
@Tag(name = "Country", description = "Work with countries")
public class CountryController {
  private CountryService countryService;
  private final CounterService counterService;

  @PostMapping("/create")
  @Operation(summary = "Create country", description = "Enable to create country")
  public Country createCountry(@RequestBody Country countryModel) throws ServerException {
    counterService.incrementCounter();
    return countryService.createCountry(countryModel);
  }

  @GetMapping("/all")
  @Operation(summary = "Get all countries", description = "Enable to get all countries")
  public List<CountryDto> getAllCountries() throws NotFoundException {
    counterService.incrementCounter();
    return countryService.getAllCountries();
  }

  @GetMapping("/{countryId}")
  @Operation(summary = "Get country by id", description = "Enable to get country by id")
  public CountryDto getCountryById(@PathVariable Integer countryId) throws NotFoundException {
    counterService.incrementCounter();
    return countryService.getCountryById(countryId);
  }

  @GetMapping("/name/{countryName}")
  @Operation(summary = "Get country by name", description = "Enable to get country by name")
  public Country getCountryByName(@PathVariable String countryName) throws NotFoundException {
    counterService.incrementCounter();
    return countryService.getCountryByName(countryName);
  }

  @DeleteMapping("/all")
  @Operation(summary = "Delete all countries", description = "Enable to delete all countries")
  public String deleteAllCountries() {
    counterService.incrementCounter();
    countryService.deleteAllCountries();
    return "ops-s-s";
  }

  @PutMapping("/{countryId}")
  @Operation(summary = "Update country", description = "Enable to update country")
  public Country updateCountry(@PathVariable Integer countryId, @RequestBody Country countryDetails)
      throws BadRequestException {
    counterService.incrementCounter();
    return countryService.updateCountry(countryId, countryDetails);
  }

  @PutMapping("/region/{countryId}")
  @Operation(
      summary = "Delete region from country",
      description = "Enable to delete region from country")
  public void deleteRegion(@PathVariable Integer countryId) throws BadRequestException {
    counterService.incrementCounter();
    countryService.deleteRegionFromCountry(countryId);
  }

  @PostMapping("/create/list")
  @Operation(summary = "Create countries", description = "Enable to create countries")
  public List<Country> createCountries(@RequestBody List<Country> countries)
      throws ServerException {
    counterService.incrementCounter();
    return countryService.createCountries(countries);
  }
}
