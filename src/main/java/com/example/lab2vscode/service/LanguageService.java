package com.example.lab2vscode.service;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.dto.LanguageDto;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Country;
import com.example.lab2vscode.model.Language;
import com.example.lab2vscode.repository.CountryRepository;
import com.example.lab2vscode.repository.LanguageRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LanguageService {
  private LanguageRepository languageRepository;
  private CountryRepository countryRepository;
  private Cache<Integer, Optional<Language>> cache;
  private ModelMapper modelMapper;

  public Language createLanguage(Language languageModel) throws ServerException {
    if (languageRepository.findById(languageModel.getLanguageId()).isPresent()) {
      throw new ServerException("Change id");
    }
    Language language = languageRepository.save(languageModel);
    cache.put(language.getLanguageId(), Optional.of(language));
    return language;
  }

  public List<LanguageDto> getAllLanguages() throws NotFoundException {
    List<Language> languages = languageRepository.findAll();
    if (languages.isEmpty()) {
      throw new NotFoundException("Languages list is empty");
    }
    return Arrays.asList(modelMapper.map(languages, LanguageDto[].class));
  }

  public LanguageDto getLanguageById(Integer languageId) throws NotFoundException {
    Optional<Language> language;
    if (cache.containsKey(languageId)) {
      language = cache.get(languageId);
    } else {
      language = languageRepository.findById(languageId);
      cache.put(languageId, language);
    }
    if (language.isEmpty()) {
      throw new NotFoundException("No language with this id");
    }
    return modelMapper.map(language, LanguageDto.class);
  }

  public void deleteAllLanguages() {
    cache.clear();
    languageRepository.deleteAll();
  }

  public void deleteLanguage(Integer languageId) throws BadRequestException {
    if (languageRepository.findById(languageId).isEmpty()) {
      throw new BadRequestException("Wrong id");
    }
    if (cache.containsKey(languageId)) {
      cache.remove(languageId);
    }
    languageRepository.deleteById(languageId);
  }

  public void deleteLanguageFromCountry(Integer countryId, Integer languageId)
      throws BadRequestException {
    if (countryRepository.findById(countryId).isEmpty()) {
      throw new BadRequestException("Wrong country id");
    }
    if (languageRepository.findById(languageId).isEmpty()) {
      throw new BadRequestException("Wrong language id");
    }
    Optional<Country> country = countryRepository.findById(countryId);
    if (country.isPresent()) {
      country.get().removeLanguage(languageId);
      countryRepository.save(country.get());
    }
  }

  public LanguageDto updateLanguage(Integer languageId, Language languageDetails)
      throws BadRequestException {
    Optional<Language> language;
    if (cache.containsKey(languageId)) {
      language = cache.get(languageId);
    } else {
      language = languageRepository.findById(languageId);
      cache.put(languageId, language);
    }
    if (language.isEmpty()) {
      throw new BadRequestException("Language is empty");
    }
    if (language.isPresent()) {
      Language existingLanguage = language.get();
      existingLanguage.setName(languageDetails.getName());
      languageRepository.save(existingLanguage);
      return modelMapper.map(existingLanguage, LanguageDto.class);
    }
    return null;
  }
}
