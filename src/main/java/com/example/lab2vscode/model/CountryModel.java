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
public class CountryModel {
    @Id
    private Integer country_id;

    private String name;
    private String capital;
    private Integer population;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "country_language",
    joinColumns = {
            @JoinColumn(name = "ctr_id", referencedColumnName = "country_id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "lang_id", referencedColumnName = "language_id")
    }
    )
    private List<LanguageModel> languages;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionModel region;
}
