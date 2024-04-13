package com.example.lab2vscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.model.Region;
import com.example.lab2vscode.repository.RegionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegionService {
    private RegionRepository regionRepository;
    private Cache<Integer, Optional<Region>> cache;


    public Region createRegion(Region regionModel) {
        Region region = regionRepository.save(regionModel);
        cache.put(region.getRegionId(), Optional.of(region));
        return region;
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionById(Integer regionId) {
        Optional<Region> region;
        if(cache.containsKey(regionId)){
            region = cache.get(regionId);
        }
        else{
            region = regionRepository.findById(regionId);
            cache.put(regionId, region);
        }
        return region;
    }

    public void deleteAllRegions() {
        cache.clear();
        regionRepository.deleteAll();
    }

    public void deleteRegion(Integer regionId) {
        if(cache.containsKey(regionId)){
            cache.remove(regionId);
        }
        regionRepository.deleteById(regionId);
    }

    public Region updateRegion(Integer regionId , Region regionDetails) {
        Optional<Region> region;
        if(cache.containsKey(regionId)){
            region = cache.get(regionId);
        }
        else{
            region = regionRepository.findById(regionId);
            cache.put(regionId, region);
        }
        if (region.isPresent()) {
            Region existingRegion = region.get();
            existingRegion.setName(regionDetails.getName());
            return regionRepository.save(existingRegion);
        }
        return null;
    }
}
