package com.example.lab2vscode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lab2vscode.model.Region;


@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> { }
