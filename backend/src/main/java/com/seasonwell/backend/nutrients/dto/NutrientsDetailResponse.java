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
public class NutrientsDetailResponse {
    private Long Id;
    private String disease_code;
    private String disease_name;
    private String nutrients_name;
    private String nutrients_efficiency;
    private String image_url;

    public NutrientsDetailResponse(Nutrients nutrients) {
        this.Id = nutrients.getId();
        this.disease_code = nutrients.getDiseaseNutrients().get(0).getDisease().getDisease_code();
        this.disease_name = nutrients.getDiseaseNutrients().get(0).getDisease().getDisease_name();
        this.nutrients_name = nutrients.getNutrients_name();
        this.nutrients_efficiency = nutrients.getNutrients_efficiency();
        this.image_url = nutrients.getImage_url();
    }
}
