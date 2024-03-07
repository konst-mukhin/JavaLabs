package com.example.mylab.service;

import com.example.mylab.model.CapitalContainer;
import com.example.mylab.model.CountryModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class CountryService {
    private static final String URL = "https://restcountries.com/v3.1/name/{countryName}?fields=capital";
    public CountryModel getCountryInfo(String countryName) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("countryName", countryName);

        CapitalContainer[] capitalContainers = new RestTemplate().getForEntity(
                                                   URL,
                                                   CapitalContainer[].class,
                                                   parameters).getBody();

        return (capitalContainers != null) ?
                new CountryModel(countryName, capitalContainers[0].getCapital()[0]) :
                null;
    }
}
