package com.seasonwell.backend.disease.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseDetailDto {
    private String disease_code;

    private String disease_season;

    private String disease_name;

    private DiseaseProtectDto disease_protect;

    private DiseaseCureDto disease_cure;

    private String disease_symptom;
}
