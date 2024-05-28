package com.example.lab2vscode.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto {
  private String name;
  private String capital;
  private Integer population;
  private List<LanguageDto> languages;
  private RegionDto region;
}
