package com.example.lab2vscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab2vscode.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer>{ }
