package com.example.lab2vscode.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.lab2vscode.dto.RegionDto;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Region;
import com.example.lab2vscode.service.CounterService;
import com.example.lab2vscode.service.RegionService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegionControllerTest {

  @Mock private RegionService regionService;

  @Mock private CounterService counterService;

  @InjectMocks private RegionController regionController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    counterService = new CounterService();
  }

  @Test
  void createRegionTest() throws ServerException {
    Region region = new Region();
    when(regionService.createRegion(region)).thenReturn(region);
    Region result = regionController.createRegion(region);
    assertEquals(region, result);
  }

  @Test
  void getAllRegionsTest() throws NotFoundException {
    List<RegionDto> regionDtos = Arrays.asList(new RegionDto(), new RegionDto(), new RegionDto());
    when(regionService.getAllRegions()).thenReturn(regionDtos);
    List<RegionDto> result = regionController.getAllRegions();
    assertEquals(regionDtos, result);
  }

  @Test
  void getRegionByIdTest() throws NotFoundException {
    RegionDto regionDto = new RegionDto();
    when(regionService.getRegionById(1)).thenReturn(regionDto);
    RegionDto result = regionController.getRegionById(1);
    assertEquals(regionDto, result);
  }

  @Test
  void deleteAllRegionsTest() {
    doNothing().when(regionService).deleteAllRegions();
    String result = regionController.deleteAllRegions();
    assertEquals("pusto", result);
  }

  @Test
  void updateRegionTest() throws BadRequestException {
    Region updatedRegion = new Region();
    when(regionService.updateRegion(1, updatedRegion)).thenReturn(updatedRegion);
    Region result = regionController.updateRegion(1, updatedRegion);
    assertEquals(updatedRegion, result);
  }
}
