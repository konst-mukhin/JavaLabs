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

import com.example.lab2vscode.model.RegionModel;
import com.example.lab2vscode.service.RegionService;

@RequestMapping("/region")
@RestController
public class RegionController {
     @Autowired
    private RegionService regionService;

    @PostMapping("/create")
    public RegionModel createRegion(@Validated @RequestBody RegionModel regionModel) {
        return regionService.createRegion(regionModel);
    }

    @GetMapping("/all")
    public List<RegionModel> getAllRegions() {
        return regionService.getAllRegions();
    }
    
    @GetMapping("/{region_id}")
    public Optional<RegionModel> getRegionById(@PathVariable Integer region_id)
    {
        return regionService.getRegionById(region_id);
    }
    
    @DeleteMapping("/all")
    public String deleteAllRegions() {
        regionService.deleteAllRegions();
        return "pusto";
    }

    @DeleteMapping("/{region_id}")
    public void deleteRegion(@PathVariable Integer region_id) {
        regionService.deleteRegion(region_id);
    }

    @PutMapping("/{region_id}")
    public RegionModel updateRegion(@PathVariable Integer region_id, @RequestBody RegionModel regionDetails) {
        return regionService.updateRegion(region_id, regionDetails);
    }
}
