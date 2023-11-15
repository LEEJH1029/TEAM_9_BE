package com.seasonwell.backend.disease.dto;

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
}
