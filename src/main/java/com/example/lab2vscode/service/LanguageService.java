package com.example.lab2vscode.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.dto.LanguageDTO;
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
    private ModelMapper modelMapper;

    public Language createLanguage(Language languageModel) {
        Language language = languageRepository.save(languageModel);
        cache.put(language.getLanguageId(), Optional.of(language));
        return language;
    }

    public List<LanguageDTO> getAllLanguages() {
        List<Language> languages = languageRepository.findAll();
        return Arrays.asList(modelMapper.map(languages, LanguageDTO[].class));
    }

    public LanguageDTO getLanguageById(Integer languageId) {
        Optional<Language> language;
        if(cache.containsKey(languageId)){
            language = cache.get(languageId);
        }
        else{
            language = languageRepository.findById(languageId);
            cache.put(languageId, language);
        }
        return modelMapper.map(language, LanguageDTO.class);
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

    public LanguageDTO updateLanguage(Integer languageId , Language languageDetails) {
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
            languageRepository.save(existingLanguage);
            return modelMapper.map(existingLanguage, LanguageDTO.class);
        }
        return null;
    }
}
