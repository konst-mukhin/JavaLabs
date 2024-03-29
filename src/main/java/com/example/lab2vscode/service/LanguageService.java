package com.example.lab2vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab2vscode.model.LanguageModel;
import com.example.lab2vscode.repository.LanguageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LanguageService {
    private LanguageRepository languageRepository;

    @SuppressWarnings("null")
    public LanguageModel createLanguage(LanguageModel languageModel) {
        return languageRepository.save(languageModel);
    }

    public List<LanguageModel> getAllLanguages() {
        return languageRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<LanguageModel> getLanguageById(Integer languageId) {
        return languageRepository.findById(languageId);
    }

    public void deleteAllLanguages() {
        languageRepository.deleteAll();
    }

    @SuppressWarnings("null")
    public void deleteLanguage(Integer languageId) {
        languageRepository.deleteById(languageId);
    }

    public LanguageModel updateLanguage(Integer languageId , LanguageModel languageDetails) {
        @SuppressWarnings("null")
        Optional<LanguageModel> language = languageRepository.findById(languageId);
        if (language.isPresent()) {
            LanguageModel existingLanguage = language.get();
            existingLanguage.setName(languageDetails.getName());
            return languageRepository.save(existingLanguage);
        }
        return null;
    }
}
