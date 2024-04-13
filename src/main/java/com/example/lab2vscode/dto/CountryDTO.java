package com.example.lab2vscode.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    
    private String name;
    private String capital;
    private Integer population;
    private List<LanguageDTO> languages;
    private RegionDTO region;
}
