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

import com.example.lab2vscode.dto.RegionDTO;
import com.example.lab2vscode.model.Region;
import com.example.lab2vscode.service.RegionService;

import lombok.AllArgsConstructor;

@RequestMapping("/region")
@RestController
@AllArgsConstructor
public class RegionController {
    private RegionService regionService;

    @PostMapping("/create")
    public Region createRegion(@Validated @RequestBody Region regionModel) {
        return regionService.createRegion(regionModel);
    }

    @GetMapping("/all")
    public List<RegionDTO> getAllRegions() {
        return regionService.getAllRegions();
    }
    
    @GetMapping("/{regionId}")
    public RegionDTO getRegionById(@PathVariable Integer regionId)
    {
        return regionService.getRegionById(regionId);
    }
    
    @DeleteMapping("/all")
    public String deleteAllRegions() {
        regionService.deleteAllRegions();
        return "pusto";
    }

    @DeleteMapping("/{regionId}")
    public void deleteRegion(@PathVariable Integer regionId) {
        regionService.deleteRegion(regionId);
    }

    @PutMapping("/{regionId}")
    public RegionDTO updateRegion(@PathVariable Integer regionId, @RequestBody Region regionDetails) {
        return regionService.updateRegion(regionId, regionDetails);
    }
}
