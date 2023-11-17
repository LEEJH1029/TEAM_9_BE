package com.seasonwell.backend.disease.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseDto {

    private String disease_code;

    private String disease_season;

    private String disease_name;

    private String disease_image;

}
