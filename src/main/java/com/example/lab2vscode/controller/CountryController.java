package com.example.lab2vscode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.service.CountryService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/country")
@RestController
@AllArgsConstructor
public class CountryController {
    private CountryService countryService;

    @PostMapping("/create")
    public Country createCountry(@Validated @RequestBody Country countryModel) {
        return countryService.createCountry(countryModel);
    }

    @GetMapping("/all")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
    
    @GetMapping("/{countryId}")
    public Optional<Country> getCountryById(@PathVariable Integer countryId)
    {
        return countryService.getCountryById(countryId);
    }
    
    @DeleteMapping("/all")
    public String deleteAllCountries() {
        countryService.deleteAllCountries();
        return "ops-s-s";
    }

    @DeleteMapping("/{countryId}")
    public void deleteCountry(@PathVariable Integer countryId) {
        countryService.deleteCountry(countryId);
    }

    @PutMapping("/{countryId}")
    public Country updateCountry(@PathVariable Integer countryId, @RequestBody Country countryDetails) {
        return countryService.updateCountry(countryId, countryDetails);
    }
}
