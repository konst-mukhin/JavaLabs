package com.example.lab2vscode.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "country")
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    private Integer countryId;

    private String name;
    private String capital;
    private Integer population;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "country_language",
    joinColumns = {
            @JoinColumn(name = "ctrId", referencedColumnName = "countryId")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "langId", referencedColumnName = "languageId")
    }
    )
    private List<Language> languages;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;
}
