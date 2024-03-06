package com.example.mylab.Controller;

import com.example.mylab.Service.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController() {
        countryService = new CountryService();
    }

    @RequestMapping(value="/country", method = RequestMethod.GET)
    public @ResponseBody String getCountryInfo(
            @RequestParam(name="countryName") String name)
            throws JsonProcessingException {
       return countryService.getCountryInfo(name).toJson();
    }
}
