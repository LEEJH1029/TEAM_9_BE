package com.seasonwell.backend.disease.repository;

import com.seasonwell.backend.disease.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DiseaseRepository extends JpaRepository<Disease, String> {
    @Query("SELECT d FROM Disease d WHERE d.disease_season = :season")
    List<Disease> findByDisease_season(@Param("season") String season);

    @Query("SELECT d FROM Disease d WHERE d.disease_name = :name")
    List<Disease> findByDisease_name(@Param("name") String name);

    @Query("SELECT d FROM Disease d WHERE lower(d.disease_name) LIKE lower(concat('%', :keyword, '%'))")
    List<Disease> findByDiseaseNameContainingIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT d FROM Disease d WHERE d.disease_code = :code")
    Disease findByDisease_code(@Param("code") String code);
}


