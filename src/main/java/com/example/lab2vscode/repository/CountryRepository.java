package com.example.lab2vscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab2vscode.model.CountryModel;

public interface CountryRepository extends JpaRepository<CountryModel, Integer> { }
