package com.seasonwell.backend.disease.repository;

import com.seasonwell.backend.disease.entity.Prevention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseasePreventionRepository extends JpaRepository<Prevention, Long> {
    @Query("SELECT p FROM Prevention p INNER JOIN p.diseasePreventions dp INNER JOIN dp.disease d WHERE d.disease_code = :disease_code")
    List<Prevention> findAllByDiseaseCode(@Param("disease_code") String disease_code);
}
