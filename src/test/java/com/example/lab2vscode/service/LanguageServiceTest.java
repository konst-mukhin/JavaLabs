package com.example.lab2vscode.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.dto.LanguageDto;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Language;
import com.example.lab2vscode.repository.LanguageRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@ExtendWith(MockitoExtension.class)
public class LanguageServiceTest {
  @Mock private LanguageRepository languageRepository;

  @Mock private Cache<Integer, Optional<Language>> cache;

  @Spy private ModelMapper modelMapper = new ModelMapper();

  @InjectMocks private LanguageService languageService;

  private Language language;
  private LanguageDto languageDto;

  @BeforeEach
  void setUp() {
    language = new Language();
    languageDto = new LanguageDto();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    language.setLanguageId(1);
    language.setName("name");

    languageDto.setName("name");
  }

  @Test
  void createLanguageTest() throws ServerException {
    when(languageRepository.findById(1)).thenReturn(Optional.empty());
    when(languageRepository.save(language)).thenReturn(language);

    Language result = languageService.createLanguage(language);

    assertEquals(language, result);
    verify(cache, times(1)).put(1, Optional.of(language));
  }

  @Test
  void createLanguageExceptionTest() throws ServerException {
    when(languageRepository.findById(1)).thenReturn(Optional.of(language));

    assertThrows(ServerException.class, () -> languageService.createLanguage(language));
  }

  @Test
  void getAllLanguagesTest() throws NotFoundException {
    when(languageRepository.findAll()).thenReturn(Arrays.asList(language, language));

    List<LanguageDto> result =
        Arrays.asList(modelMapper.map(languageService.getAllLanguages(), LanguageDto[].class));

    assertEquals(2, result.size());
    assertEquals(languageDto, result.get(0));
  }

  @Test
  void getAllRegionsExceptionTest() throws NotFoundException {
    when(languageRepository.findAll()).thenReturn(Arrays.asList());

    assertThrows(NotFoundException.class, () -> languageService.getAllLanguages());
  }

  @Test
  void getLanguageByIdTest() throws NotFoundException {
    when(cache.containsKey(1)).thenReturn(false);
    when(languageRepository.findById(1)).thenReturn(Optional.of(language));

    LanguageDto result = languageService.getLanguageById(1);

    assertEquals(languageDto, result);
    verify(cache, times(1)).put(1, Optional.of(language));
  }

  @Test
  void getLanguageByIdCacheTest() throws NotFoundException {
    when(cache.containsKey(1)).thenReturn(true);
    when(cache.get(1)).thenReturn(Optional.of(language));

    LanguageDto result = languageService.getLanguageById(1);

    assertEquals(languageDto, result);
  }

  @Test
  void getLanguageByIdExceptionTest() throws NotFoundException {
    when(cache.containsKey(1)).thenReturn(false);
    when(languageRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> languageService.getLanguageById(1));
  }

  @Test
  void updateLanguageTest() throws BadRequestException {
    when(cache.containsKey(1)).thenReturn(false);
    when(languageRepository.findById(1)).thenReturn(Optional.of(language));

    Language result = languageService.updateLanguage(1, language);

    assertEquals(language, result);
    verify(cache, times(1)).put(1, Optional.of(language));
  }

  @Test
  void updateLanguageCacheTest() throws BadRequestException {
    when(cache.containsKey(1)).thenReturn(true);
    when(cache.get(1)).thenReturn(Optional.of(language));

    Language result = languageService.updateLanguage(1, language);

    assertEquals(language, result);
  }

  @Test
  void updateLanguageExceptionTest() throws BadRequestException {
    when(languageRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(BadRequestException.class, () -> languageService.updateLanguage(1, language));
  }
}
