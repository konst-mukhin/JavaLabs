package com.example.mylab.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class CountryModel {
    private String name;
    private String capital;

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().
                   writer().
                   withDefaultPrettyPrinter().
                   writeValueAsString(this);
    }

    public CountryModel(String name, String capital) {
        this.name = name.toLowerCase();
        this.capital = capital.toLowerCase();
    }
}
