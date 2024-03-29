package com.example.lab2vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab2vscode.model.RegionModel;
import com.example.lab2vscode.repository.RegionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegionService {
    private RegionRepository regionRepository;

    @SuppressWarnings("null")
    public RegionModel createRegion(RegionModel regionModel) {
        return regionRepository.save(regionModel);
    }

    public List<RegionModel> getAllRegions() {
        return regionRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<RegionModel> getRegionById(Integer region_id) {
        return regionRepository.findById(region_id);
    }

    public void deleteAllRegions() {
        regionRepository.deleteAll();
    }

    @SuppressWarnings("null")
    public void deleteRegion(Integer region_id) {
        regionRepository.deleteById(region_id);
    }

    public RegionModel updateRegion(Integer region_id , RegionModel regionDetails) {
        @SuppressWarnings("null")
        Optional<RegionModel> region = regionRepository.findById(region_id);
        if (region.isPresent()) {
            RegionModel existingRegion = region.get();
            existingRegion.setName(regionDetails.getName());
            return regionRepository.save(existingRegion);
        }
        return null;
    }
}
