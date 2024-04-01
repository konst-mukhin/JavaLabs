package com.example.lab2vscode.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab2vscode.model.Language;
import com.example.lab2vscode.service.LanguageService;

import lombok.AllArgsConstructor;

@RequestMapping("/language")
@RestController
@AllArgsConstructor
public class LanguageController {
    private LanguageService languageService;

    @PostMapping("/create")
    public Language createLanguage(@Validated @RequestBody Language languageModel) {
        return languageService.createLanguage(languageModel);
    }

    @GetMapping("/all")
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }
    
    @GetMapping("/{languageId}")
    public Optional<Language> getLanguageById(@PathVariable Integer languageId)
    {
        return languageService.getLanguageById(languageId);
    }
    
    @DeleteMapping("/all")
    public String deleteAllLanguages() {
        languageService.deleteAllLanguages();
        return "nothing :(";
    }

    @DeleteMapping("/{languageId}")
    public void deleteLanguage(@PathVariable Integer languageId) {
        languageService.deleteLanguage(languageId);
    }

    @PutMapping("/{languageId}")
    public Language updateLanguage(@PathVariable Integer languageId, @RequestBody Language languageDetails) {
        return languageService.updateLanguage(languageId, languageDetails);
    }

}
