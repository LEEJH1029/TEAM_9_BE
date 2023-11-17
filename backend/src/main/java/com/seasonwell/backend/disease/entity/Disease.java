package com.seasonwell.backend.disease.entity;

import com.seasonwell.backend.nutrients.entity.DiseaseNutrients;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
public class Disease {

    @Id
    private String disease_code;

    private String disease_season;

    private String disease_name;

    private String disease_protect;

    private String disease_cure;

    private String disease_symptom;

    @OneToMany(mappedBy = "disease")
    private List<DiseaseNutrients> diseaseNutrients;

    public Disease() {
    }

    public Disease(String disease_code, String disease_season, String disease_name, String disease_protect, String disease_cure, String disease_symptom) {
        this.disease_code = disease_code;
        this.disease_season = disease_season;
        this.disease_name = disease_name;
        this.disease_protect = disease_protect;
        this.disease_cure = disease_cure;
        this.disease_symptom = disease_symptom;
    }
}

