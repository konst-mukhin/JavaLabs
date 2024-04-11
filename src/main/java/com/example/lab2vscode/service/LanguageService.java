package com.example.lab2vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.model.Language;
import com.example.lab2vscode.repository.CountryRepository;
import com.example.lab2vscode.repository.LanguageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LanguageService {
    private LanguageRepository languageRepository;
    private CountryRepository countryRepository;

    public Language createLanguage(Language languageModel) {
        return languageRepository.save(languageModel);
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Optional<Language> getLanguageById(Integer languageId) {
        return languageRepository.findById(languageId);
    }

    public void deleteAllLanguages() {
        languageRepository.deleteAll();
    }

    public void deleteLanguage(Integer languageId) {
        languageRepository.deleteById(languageId);
    }

    public void deleteLanguageFromCountry(Integer countryId, Integer languageId) {
        Optional<Country> country = countryRepository.findById(countryId);
        if(country.isPresent())
        {
            country.get().removeLanguage(languageId);
            countryRepository.save(country.get());
        }
      } 

    public Language updateLanguage(Integer languageId , Language languageDetails) {
        Optional<Language> language = languageRepository.findById(languageId);
        if (language.isPresent()) {
            Language existingLanguage = language.get();
            existingLanguage.setName(languageDetails.getName());
            return languageRepository.save(existingLanguage);
        }
        return null;
    }
}
