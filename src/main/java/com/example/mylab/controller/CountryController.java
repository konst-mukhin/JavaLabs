package com.example.mylab.controller;

import com.example.mylab.service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController() {
        countryService = new CountryService();
    }

    @GetMapping(value="/country")
    public String getCountryInfo(
            @RequestParam(name="countryName") String name)
            throws JsonProcessingException {
       return countryService.getCountryInfo(name).toJson();
    }
}
