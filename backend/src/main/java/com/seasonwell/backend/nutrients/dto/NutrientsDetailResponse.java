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
    private List<DiseaseInfo> disease;
    private String nutrients_name;
    private String nutrients_efficiency;
    private String image_url;

    public NutrientsDetailResponse(Nutrients nutrients) {
        this.Id = nutrients.getId();
        this.disease = nutrients.getDiseaseNutrients().stream()
                .map(diseaseNutrient -> new DiseaseInfo(diseaseNutrient.getDisease().getDisease_code(), diseaseNutrient.getDisease().getDisease_name()))
                .collect(Collectors.toList());
        this.nutrients_name = nutrients.getNutrients_name();
        this.nutrients_efficiency = nutrients.getNutrients_efficiency();
        this.image_url = nutrients.getImage_url();
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @NoArgsConstructor
    private static class DiseaseInfo {
        private String code;
        private String name;
    }
}
