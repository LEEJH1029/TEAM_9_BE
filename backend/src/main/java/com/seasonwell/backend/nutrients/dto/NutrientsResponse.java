package com.seasonwell.backend.nutrients.dto;

import com.seasonwell.backend.nutrients.entity.Nutrients;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class NutrientsResponse {
    private Long Id;
    private List<String> disease_codes;
    private String nutrients_name;
    private String nutrients_efficiency;
    private String image_url;

    public NutrientsResponse(Nutrients nutrients) {
        this.Id = nutrients.getId();
        this.disease_codes = nutrients.getDiseaseNutrients().stream()
                .map(diseaseNutrient -> diseaseNutrient.getDisease().getDisease_code())
                .collect(Collectors.toList());
        this.nutrients_name = nutrients.getNutrients_name();
        this.nutrients_efficiency = nutrients.getNutrients_efficiency();
        this.image_url = nutrients.getImage_url();
    }
}
