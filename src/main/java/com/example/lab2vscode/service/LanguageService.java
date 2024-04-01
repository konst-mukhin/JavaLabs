package com.example.lab2vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab2vscode.model.Language;
import com.example.lab2vscode.repository.LanguageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LanguageService {
    private LanguageRepository languageRepository;

    @SuppressWarnings("null")
    public Language createLanguage(Language languageModel) {
        return languageRepository.save(languageModel);
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Language> getLanguageById(Integer languageId) {
        return languageRepository.findById(languageId);
    }

    public void deleteAllLanguages() {
        languageRepository.deleteAll();
    }

    @SuppressWarnings("null")
    public void deleteLanguage(Integer languageId) {
        languageRepository.deleteById(languageId);
    }

    public Language updateLanguage(Integer languageId , Language languageDetails) {
        @SuppressWarnings("null")
        Optional<Language> language = languageRepository.findById(languageId);
        if (language.isPresent()) {
            Language existingLanguage = language.get();
            existingLanguage.setName(languageDetails.getName());
            return languageRepository.save(existingLanguage);
        }
        return null;
    }
}
