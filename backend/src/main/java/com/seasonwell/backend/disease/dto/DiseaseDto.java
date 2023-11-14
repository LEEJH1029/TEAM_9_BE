package com.seasonwell.backend.disease.dto;

import com.seasonwell.backend.disease.entity.Disease;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseDto {

    private String disease_code;

    private String disease_season;

    private String disease_name;

    private String disease_protect;

    private String disease_cure;

    private String disease_symptom;

    public Disease toEntity() {
        return Disease.builder()
                .disease_code(this.disease_code)
                .disease_season(this.disease_season)
                .disease_name(this.disease_name)
                .disease_protect(this.disease_protect)
                .disease_cure(this.disease_cure)
                .disease_symptom(this.disease_symptom)
                .build();
    }

    @Override
    public String toString() {
        return "DiseaseDto{" +
                "disease_code='" + disease_code + '\'' +
                ", disease_season='" + disease_season + '\'' +
                ", disease_name='" + disease_name + '\'' +
                ", disease_protect='" + disease_protect + '\'' +
                ", disease_cure='" + disease_cure + '\'' +
                ", disease_symptom='" + disease_symptom + '\'' +
                '}';
    }
}
