package com.seasonwell.backend.disease.repository;

import com.seasonwell.backend.disease.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
//    List<Disease> findByDisease_code(String disease_code);
}
