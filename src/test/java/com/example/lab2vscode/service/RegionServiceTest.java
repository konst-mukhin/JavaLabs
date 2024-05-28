package com.example.lab2vscode.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {

  @Mock private RegionRepository regionRepository;

  @Mock private Cache<Integer, Optional<Region>> cache;

  @Spy private ModelMapper modelMapper = new ModelMapper();

  @InjectMocks private RegionService regionService;

  private Region region;
  private RegionDto regionDto;

  @BeforeEach
  void setUp() {
    region = new Region();
    regionDto = new RegionDto();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    region.setRegionId(1);
    region.setName("name");

    regionDto.setName("name");
  }

  @Test
  void createRegionTest() throws ServerException {
    when(regionRepository.findById(1)).thenReturn(Optional.empty());
    when(regionRepository.save(region)).thenReturn(region);

    Region result = regionService.createRegion(region);

    assertEquals(region, result);
    verify(cache, times(1)).put(1, Optional.of(region));
  }

  @Test
  void createRegionExceptionTest() throws ServerException {
    when(regionRepository.findById(1)).thenReturn(Optional.of(region));

    assertThrows(ServerException.class, () -> regionService.createRegion(region));
  }

  @Test
  void getAllRegionsTest() throws NotFoundException {
    when(regionRepository.findAll()).thenReturn(Arrays.asList(region, region));

    List<RegionDto> result =
        Arrays.asList(modelMapper.map(regionService.getAllRegions(), RegionDto[].class));

    assertEquals(2, result.size());
    assertEquals(regionDto, result.get(0));
  }

  @Test
  void getAllRegionsExceptionTest() throws NotFoundException {
    when(regionRepository.findAll()).thenReturn(Arrays.asList());

    assertThrows(NotFoundException.class, () -> regionService.getAllRegions());
  }

  @Test
  void getRegionByIdTest() throws NotFoundException {
    when(cache.containsKey(1)).thenReturn(false);
    when(regionRepository.findById(1)).thenReturn(Optional.of(region));

    RegionDto result = regionService.getRegionById(1);

    assertEquals(regionDto, result);
    verify(cache, times(1)).put(1, Optional.of(region));
  }

  @Test
  void getRegionByIdCacheTest() throws NotFoundException {
    when(cache.containsKey(1)).thenReturn(true);
    when(cache.get(1)).thenReturn(Optional.of(region));

    RegionDto result = regionService.getRegionById(1);

    assertEquals(regionDto, result);
  }

  @Test
  void getRegionByIdExceptionTest() throws NotFoundException {
    when(cache.containsKey(1)).thenReturn(false);
    when(regionRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> regionService.getRegionById(1));
  }

  @Test
  void updateRegionTest() throws BadRequestException {
    when(cache.containsKey(1)).thenReturn(false);
    when(regionRepository.findById(1)).thenReturn(Optional.of(region));

    Region result = regionService.updateRegion(1, region);

    assertEquals(region, result);
    verify(cache, times(1)).put(1, Optional.of(region));
  }

  @Test
  void updateRegionCacheTest() throws BadRequestException {
    when(cache.containsKey(1)).thenReturn(true);
    when(cache.get(1)).thenReturn(Optional.of(region));

    Region result = regionService.updateRegion(1, region);

    assertEquals(region, result);
  }

  @Test
  void updateRegionExceptionTest() throws BadRequestException {
    when(regionRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(BadRequestException.class, () -> regionService.updateRegion(1, region));
  }
}
