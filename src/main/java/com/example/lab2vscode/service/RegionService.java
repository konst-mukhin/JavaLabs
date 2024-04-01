package com.example.lab2vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab2vscode.model.Region;
import com.example.lab2vscode.repository.RegionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegionService {
    private RegionRepository regionRepository;

    @SuppressWarnings("null")
    public Region createRegion(Region regionModel) {
        return regionRepository.save(regionModel);
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Region> getRegionById(Integer regionId) {
        return regionRepository.findById(regionId);
    }

    public void deleteAllRegions() {
        regionRepository.deleteAll();
    }

    @SuppressWarnings("null")
    public void deleteRegion(Integer regionId) {
        regionRepository.deleteById(regionId);
    }

    public Region updateRegion(Integer regionId , Region regionDetails) {
        @SuppressWarnings("null")
        Optional<Region> region = regionRepository.findById(regionId);
        if (region.isPresent()) {
            Region existingRegion = region.get();
            existingRegion.setName(regionDetails.getName());
            return regionRepository.save(existingRegion);
        }
        return null;
    }
}
