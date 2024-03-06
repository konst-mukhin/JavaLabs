package com.example.mylab.Service;

import com.example.mylab.Model.Capital;
import com.example.mylab.Model.CountryModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryService {

    public CountryService() {
    }

    public CountryModel getCountryInfo(String countryName) {
        String restCountriesUrl = "https://restcountries.com/v3.1/name/" +
                                  countryName +
                                  "?fields=capital";
        Capital[] capitals = new RestTemplate().getForEntity(
                                                restCountriesUrl,
                                                Capital[].class).getBody();

        return (capitals != null) ?
                new CountryModel(countryName, capitals[0].getCapital()[0]) :
                null;
    }
}
