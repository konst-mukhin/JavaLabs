package com.example.lab2vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab2vscode.cache.Cache;
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
    private Cache<Integer, Optional<Language>> cache;

    public Language createLanguage(Language languageModel) {
        Language language = languageRepository.save(languageModel);
        cache.put(language.getLanguageId(), Optional.of(language));
        return language;
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Optional<Language> getLanguageById(Integer languageId) {
        Optional<Language> language;
        if(cache.containsKey(languageId)){
            language = cache.get(languageId);
        }
        else{
            language = languageRepository.findById(languageId);
            cache.put(languageId, language);
        }
        return language;
    }

    public void deleteAllLanguages() {
        cache.clear();
        languageRepository.deleteAll();
    }

    public void deleteLanguage(Integer languageId) {
        if(cache.containsKey(languageId)){
            cache.remove(languageId);
        }
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
        Optional<Language> language;
        if(cache.containsKey(languageId)){
            language = cache.get(languageId);
        }
        else{
            language = languageRepository.findById(languageId);
            cache.put(languageId, language);
        }
        if (language.isPresent()) {
            Language existingLanguage = language.get();
            existingLanguage.setName(languageDetails.getName());
            return languageRepository.save(existingLanguage);
        }
        return null;
    }
}
