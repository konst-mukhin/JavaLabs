package com.example.lab2vscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab2vscode.model.LanguageModel;

public interface LanguageRepository extends JpaRepository<LanguageModel, Integer>{ }
