package com.example.lab2vscode.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab2vscode.model.CountryModel;
import com.example.lab2vscode.service.CountryService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/country")
@RestController
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping("/create")
    public CountryModel createCountry(@Validated @RequestBody CountryModel countryModel) {
        return countryService.createCountry(countryModel);
    }

    @GetMapping("/all")
    public List<CountryModel> getAllCountries() {
        return countryService.getAllCountries();
    }
    
    @GetMapping("/{country_id}")
    public Optional<CountryModel> getCountryById(@PathVariable Integer country_id)
    {
        return countryService.getCountryById(country_id);
    }
    
    @DeleteMapping("/all")
    public String deleteAllCountries() {
        countryService.deleteAllCountries();
        return "ops-s-s";
    }

    @DeleteMapping("/{country_id}")
    public void deleteCountry(@PathVariable Integer country_id) {
        countryService.deleteCountry(country_id);
    }

    @PutMapping("/{country_id}")
    public CountryModel updateCountry(@PathVariable Integer country_id, @RequestBody CountryModel countryDetails) {
        return countryService.updateCountry(country_id, countryDetails);
    }
}
