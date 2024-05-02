package com.example.lab2vscode.service;

import com.example.lab2vscode.cache.Cache;
import com.example.lab2vscode.dto.RegionDto;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Region;
import com.example.lab2vscode.repository.RegionRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegionService {
  private RegionRepository regionRepository;
  private Cache<Integer, Optional<Region>> cache;
  private ModelMapper modelMapper;

  public Region createRegion(Region regionModel) throws ServerException {
    if (regionRepository.findById(regionModel.getRegionId()).isPresent()) {
      throw new ServerException("Change id");
    }
    Region region = regionRepository.save(regionModel);
    cache.put(region.getRegionId(), Optional.of(region));
    return region;
  }

  public List<RegionDto> getAllRegions() throws NotFoundException {
    List<Region> regions = regionRepository.findAll();
    if (regions.isEmpty()) {
      throw new NotFoundException("Regions list is empty");
    }
    return Arrays.asList(modelMapper.map(regions, RegionDto[].class));
  }

  public RegionDto getRegionById(Integer regionId) throws NotFoundException {
    Optional<Region> region;
    if (cache.containsKey(regionId)) {
      region = cache.get(regionId);
    } else {
      region = regionRepository.findById(regionId);
      cache.put(regionId, region);
    }
    if (region.isEmpty()) {
      throw new NotFoundException("No region with this id");
    }
    return modelMapper.map(region, RegionDto.class);
  }

  public void deleteAllRegions() {
    cache.clear();
    regionRepository.deleteAll();
  }

  public void deleteRegion(Integer regionId) throws BadRequestException {
    if (regionRepository.findById(regionId).isEmpty()) {
      throw new BadRequestException("Wrong id");
    }
    if (cache.containsKey(regionId)) {
      cache.remove(regionId);
    }
    regionRepository.deleteById(regionId);
  }

  public RegionDto updateRegion(Integer regionId, Region regionDetails) throws BadRequestException {
    Optional<Region> region;
    if (cache.containsKey(regionId)) {
      region = cache.get(regionId);
    } else {
      region = regionRepository.findById(regionId);
      cache.put(regionId, region);
    }
    if (region.isEmpty()) {
      throw new BadRequestException("Region is empty");
    }
    if (region.isPresent()) {
      Region existingRegion = region.get();
      existingRegion.setName(regionDetails.getName());
      regionRepository.save(existingRegion);
      return modelMapper.map(existingRegion, RegionDto.class);
    }
    return null;
  }
}
