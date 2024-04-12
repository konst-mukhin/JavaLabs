package com.example.lab2vscode.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "region")
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @Id
    private Integer regionId;

    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Country> countries;
}
