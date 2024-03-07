package com.example.mylab.service;

import com.example.mylab.model.CapitalContainer;
import com.example.mylab.model.CountryModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {
    private static final String Url = "https://restcountries.com/v3.1/name/%s?fields=capital";
    public CountryModel getCountryInfo(String countryName) {
        String restCountriesUrl = String.format(Url, countryName);
        CapitalContainer[] capitalContainers = new RestTemplate().getForEntity(
                                                   restCountriesUrl,
                                                   CapitalContainer[].class).getBody();

        return (capitalContainers != null) ?
                new CountryModel(countryName, capitalContainers[0].getCapital()[0]) :
                null;
    }
}
