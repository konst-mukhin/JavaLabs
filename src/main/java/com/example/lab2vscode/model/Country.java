package com.example.lab2vscode.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "country")
@AllArgsConstructor
@NoArgsConstructor
public class Country {
  @Id private Integer countryId;

  private String name;
  private String capital;
  private Integer population;

  @ManyToMany(
      cascade = {CascadeType.MERGE, CascadeType.PERSIST},
      fetch = FetchType.LAZY)
  @JoinTable(
      name = "country_language",
      joinColumns = {@JoinColumn(name = "ctrId", referencedColumnName = "countryId")},
      inverseJoinColumns = {@JoinColumn(name = "langId", referencedColumnName = "languageId")})
  private List<Language> languages;

  @ManyToOne
  @JoinColumn(name = "regionId")
  private Region region;

  public void removeLanguage(Integer languageId) {
    Language language =
        this.languages.stream()
            .filter(t -> t.getLanguageId() == languageId)
            .findFirst()
            .orElse(null);
    if (language != null) {
      this.languages.remove(language);
      language.getCountries().remove(this);
    }
  }
}
