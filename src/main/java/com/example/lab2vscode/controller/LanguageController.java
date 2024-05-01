package com.example.lab2vscode.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab2vscode.dto.LanguageDTO;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Language;
import com.example.lab2vscode.service.LanguageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RequestMapping("/language")
@RestController
@AllArgsConstructor
@Tag(name = "Language", description = "Work with languages")
public class LanguageController {
    private LanguageService languageService;

    @PostMapping("/create")
    @Operation(summary = "Create language", description = "Enable to create language")
    public Language createLanguage(@Validated @RequestBody Language languageModel) throws ServerException {
        return languageService.createLanguage(languageModel);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all languages", description = "Enable to get all languages")
    public List<LanguageDTO> getAllLanguages() throws NotFoundException {
        return languageService.getAllLanguages();
    }
    
    @GetMapping("/{languageId}")
    @Operation(summary = "Get language by id", description = "Enable to get language by id")
    public LanguageDTO getLanguageById(@PathVariable Integer languageId) throws NotFoundException
    {
        return languageService.getLanguageById(languageId);
    }
    
    @DeleteMapping("/all")
    @Operation(summary = "Delete all languages", description = "Enable to delete all languages")
    public String deleteAllLanguages() {
        languageService.deleteAllLanguages();
        return "nothing :(";
    }

    @DeleteMapping("/{languageId}")
    @Operation(summary = "Delete language by id", description = "Enable to delete language by id")
    public void deleteLanguage(@PathVariable Integer languageId) throws BadRequestException {
        languageService.deleteLanguage(languageId);
    }

    @PutMapping("/{languageId}")
    @Operation(summary = "Update language", description = "Enable to update language")
    public LanguageDTO updateLanguage(@PathVariable Integer languageId, @RequestBody Language languageDetails) 
    throws BadRequestException {
        return languageService.updateLanguage(languageId, languageDetails);
    }

}
