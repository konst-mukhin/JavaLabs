package com.example.lab2vscode.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.dto.RegionDTO;
import com.example.lab2vscode.model.Region;
import com.example.lab2vscode.repository.RegionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegionService {
    private RegionRepository regionRepository;
    private Cache<Integer, Optional<Region>> cache;
    private ModelMapper modelMapper;


    public Region createRegion(Region regionModel) {
        Region region = regionRepository.save(regionModel);
        cache.put(region.getRegionId(), Optional.of(region));
        return region;
    }

    public List<RegionDTO> getAllRegions() {
        List<Region> regions = regionRepository.findAll();
        return Arrays.asList(modelMapper.map(regions, RegionDTO[].class));
    }

    public RegionDTO getRegionById(Integer regionId) {
        Optional<Region> region;
        if(cache.containsKey(regionId)){
            region = cache.get(regionId);
        }
        else{
            region = regionRepository.findById(regionId);
            cache.put(regionId, region);
        }
        RegionDTO regionDTO = modelMapper.map(region, RegionDTO.class);
        return regionDTO;
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

    public RegionDTO updateRegion(Integer regionId , Region regionDetails) {
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
            regionRepository.save(existingRegion);
            return modelMapper.map(existingRegion, RegionDTO.class);
        }
        return null;
    }
}
