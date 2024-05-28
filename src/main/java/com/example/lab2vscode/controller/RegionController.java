package com.example.lab2vscode.controller;

import com.example.lab2vscode.dto.RegionDto;
import com.example.lab2vscode.exceptions.BadRequestException;
import com.example.lab2vscode.exceptions.NotFoundException;
import com.example.lab2vscode.exceptions.ServerException;
import com.example.lab2vscode.model.Region;
import com.example.lab2vscode.service.CounterService;
import com.example.lab2vscode.service.RegionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/region")
@RestController
@AllArgsConstructor
@Tag(name = "Region", description = "Work with regions")
public class RegionController {
  private RegionService regionService;
  private CounterService counterService;

  @PostMapping("/create")
  @Operation(summary = "Create region", description = "Enable to create region")
  public Region createRegion(@Validated @RequestBody Region regionModel) throws ServerException {
    counterService.incrementCounter();
    return regionService.createRegion(regionModel);
  }

  @GetMapping("/all")
  @Operation(summary = "Get all regions", description = "Enable to get all regions")
  public List<RegionDto> getAllRegions() throws NotFoundException {
    counterService.incrementCounter();
    return regionService.getAllRegions();
  }

  @GetMapping("/{regionId}")
  @Operation(summary = "Get region by id", description = "Enable to get region by id")
  public RegionDto getRegionById(@PathVariable Integer regionId) throws NotFoundException {
    counterService.incrementCounter();
    return regionService.getRegionById(regionId);
  }

  @DeleteMapping("/all")
  @Operation(summary = "Delete all regions", description = "Enable to delete all regions")
  public String deleteAllRegions() {
    counterService.incrementCounter();
    regionService.deleteAllRegions();
    return "pusto";
  }

  @DeleteMapping("/{regionId}")
  @Operation(summary = "Delete region by id", description = "Enable to delete region by id")
  public void deleteRegion(@PathVariable Integer regionId) throws BadRequestException {
    counterService.incrementCounter();
    regionService.deleteRegion(regionId);
  }

  @PutMapping("/{regionId}")
  @Operation(summary = "Update region", description = "Enable to update region")
  public Region updateRegion(@PathVariable Integer regionId, @RequestBody Region regionDetails)
      throws BadRequestException {
    counterService.incrementCounter();
    return regionService.updateRegion(regionId, regionDetails);
  }
}
