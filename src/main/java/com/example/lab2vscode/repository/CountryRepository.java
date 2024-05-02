package com.example.lab2vscode.repository;

import com.example.lab2vscode.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
  @Modifying
  @Transactional
  @Query(
      value = "UPDATE COUNTRY SET region_id = null WHERE country_id= :countryId",
      nativeQuery = true)
  void deleteRegionFromCountry(@Param("countryId") Integer countryId);
}
