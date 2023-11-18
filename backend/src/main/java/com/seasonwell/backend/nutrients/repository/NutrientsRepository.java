package com.seasonwell.backend.nutrients.repository;

import com.seasonwell.backend.board.entity.Board;
import com.seasonwell.backend.nutrients.entity.Nutrients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NutrientsRepository extends JpaRepository<Nutrients, Long> {
    List<Nutrients> findAll();

    Optional<Nutrients> findById(Long id);

    @Query("SELECT m FROM Nutrients m WHERE lower(m.nutrients_name) LIKE lower(concat('%', :nutrients_name, '%'))")
    List<Nutrients> findByNutrients_name(
            @Param("nutrients_name") String nutrients_name
    );

    @Query("SELECT n FROM Nutrients n INNER JOIN n.diseaseNutrients dn INNER JOIN dn.disease d WHERE d.disease_code = :diseaseCode")
    List<Nutrients> findAllByDiseaseCode(@Param("diseaseCode") String diseaseCode);

    @Query("SELECT DISTINCT n FROM Nutrients n INNER JOIN n.diseaseNutrients dn INNER JOIN dn.disease d WHERE d.disease_code IN :diseaseCode")
    List<Nutrients> findByDiseaseCodeIn(@Param("diseaseCode") List<String> diseaseCode);
}
