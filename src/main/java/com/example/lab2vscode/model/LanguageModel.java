package com.example.lab2vscode.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "language")
@AllArgsConstructor
@NoArgsConstructor
public class LanguageModel {
    @Id
    private Integer language_id;

    private String name;

    @ManyToMany(mappedBy = "languages", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<CountryModel> countries;
}
