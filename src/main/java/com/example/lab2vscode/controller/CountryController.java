package com.example.lab2vscode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab2vscode.dto.CountryDTO;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.service.CountryService;
import com.example.lab2vscode.service.LanguageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/country")
@RestController
@AllArgsConstructor
@Tag(name = "Country", description = "Work with countries")
public class CountryController {
    private CountryService countryService;
    private LanguageService languageService;

    @PostMapping("/create")
    @Operation(summary = "Create country", description = "Enable to create country")
    public Country createCountry(@RequestBody Country countryModel) throws ServerException {
        return countryService.createCountry(countryModel);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all countries", description = "Enable to get all countries")
    public List<CountryDTO> getAllCountries() throws NotFoundException {
        return countryService.getAllCountries();
    }
    
    @GetMapping("/{countryId}")
    @Operation(summary = "Get country by id", description = "Enable to get country by id")
    public CountryDTO getCountryById(@PathVariable Integer countryId) throws NotFoundException
    {
        return countryService.getCountryById(countryId);
    }
    
    @DeleteMapping("/all")
    @Operation(summary = "Delete all countries", description = "Enable to delete all countries")
    public String deleteAllCountries() {
        countryService.deleteAllCountries();
        return "ops-s-s";
    }

    @DeleteMapping("/{countryId}")
    @Operation(summary = "Delete country by id", description = "Enable to delete country by id")
    public void deleteCountry(@PathVariable Integer countryId) throws BadRequestException {
        countryService.deleteCountry(countryId);
    }

    @DeleteMapping("/{countryId}/language/{languageId}")
    @Operation(summary = "Delete language from country", description = "Enable to delete language from country")
    public void deleteLanguageFromCountry(@PathVariable(value = "countryId") Integer countryId, @PathVariable(value = "languageId") Integer languageId) 
    throws BadRequestException{
        languageService.deleteLanguageFromCountry(countryId, languageId);
    }

    @PutMapping("/{countryId}")
    @Operation(summary = "Update country", description = "Enable to update country")
    public CountryDTO updateCountry(@PathVariable Integer countryId, @RequestBody Country countryDetails) throws BadRequestException {
        return countryService.updateCountry(countryId, countryDetails);
    }

    @PutMapping("/region/{countryId}")
    @Operation(summary = "Delete region from country", description = "Enable to delete region from country")
    public void deleteRegion(@PathVariable Integer countryId) throws BadRequestException{
        countryService.deleteRegionFromCountry(countryId);
    }
}
