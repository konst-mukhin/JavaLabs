package com.example.lab2vscode.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.lab2vscode.dto.LanguageDto;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Language;
import com.example.lab2vscode.service.CounterService;
import com.example.lab2vscode.service.LanguageService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LanguageControllerTest {

  @Mock private LanguageService languageService;

  @Mock private CounterService counterService;

  @InjectMocks private LanguageController languageController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    counterService = new CounterService();
  }

  @Test
  void createLanguageTest() throws ServerException {
    Language language = new Language();
    when(languageService.createLanguage(language)).thenReturn(language);
    Language result = languageController.createLanguage(language);
    assertEquals(language, result);
  }

  @Test
  void getAllLanguagesTest() throws NotFoundException {
    List<LanguageDto> languageDtos =
        Arrays.asList(new LanguageDto(), new LanguageDto(), new LanguageDto());
    when(languageService.getAllLanguages()).thenReturn(languageDtos);
    List<LanguageDto> result = languageController.getAllLanguages();
    assertEquals(languageDtos, result);
  }

  @Test
  void getLanguageByIdTest() throws NotFoundException {
    LanguageDto languageDto = new LanguageDto();
    when(languageService.getLanguageById(1)).thenReturn(languageDto);
    LanguageDto result = languageController.getLanguageById(1);
    assertEquals(languageDto, result);
  }

  @Test
  void deleteAllLanguagesTest() {
    doNothing().when(languageService).deleteAllLanguages();
    String result = languageController.deleteAllLanguages();
    assertEquals("nothing :(", result);
  }

  @Test
  void updateLanguageTest() throws BadRequestException {
    Language updatedLanguage = new Language();
    when(languageService.updateLanguage(1, updatedLanguage)).thenReturn(updatedLanguage);
    Language result = languageController.updateLanguage(1, updatedLanguage);
    assertEquals(updatedLanguage, result);
  }
}
