package com.example.lab2vscode.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab2vscode.model.LanguageModel;
import com.example.lab2vscode.service.LanguageService;

@RequestMapping("/language")
@RestController
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @PostMapping("/create")
    public LanguageModel createLanguage(@Validated @RequestBody LanguageModel languageModel) {
        return languageService.createLanguage(languageModel);
    }

    @GetMapping("/all")
    public List<LanguageModel> getAllLanguages() {
        return languageService.getAllLanguages();
    }
    
    @GetMapping("/{language_id}")
    public Optional<LanguageModel> getLanguageById(@PathVariable Integer language_id)
    {
        return languageService.getLanguageById(language_id);
    }
    
    @DeleteMapping("/all")
    public String deleteAllLanguages() {
        languageService.deleteAllLanguages();
        return "nothing :(";
    }

    @DeleteMapping("/{language_id}")
    public void deleteLanguage(@PathVariable Integer language_id) {
        languageService.deleteLanguage(language_id);
    }

    @PutMapping("/{language_id}")
    public LanguageModel updateLanguage(@PathVariable Integer language_id, @RequestBody LanguageModel languageDetails) {
        return languageService.updateLanguage(language_id, languageDetails);
    }

}
