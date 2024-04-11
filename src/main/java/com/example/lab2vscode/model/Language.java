package com.example.lab2vscode.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class Language {
    @Id
    private Integer languageId;

    private String name;

    @ManyToMany(mappedBy = "languages", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JsonIgnore
    private Set<Country> countries;
}
