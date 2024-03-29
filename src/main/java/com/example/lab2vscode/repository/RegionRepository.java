package com.example.lab2vscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lab2vscode.model.RegionModel;

public interface RegionRepository extends JpaRepository<RegionModel, Integer>{ }
