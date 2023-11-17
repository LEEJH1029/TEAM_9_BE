package com.seasonwell.backend.nutrients.controller;

import com.seasonwell.backend.global.config.BaseResponse;
import com.seasonwell.backend.global.config.ResponseStatus;
import com.seasonwell.backend.nutrients.dto.NutrientsDetailResponse;
import com.seasonwell.backend.nutrients.dto.NutrientsResponse;
import com.seasonwell.backend.nutrients.service.NutrientsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/nutrients")
@RestController
@Slf4j
public class NutrientsController {
    private final NutrientsService nutrientsService;

    public NutrientsController(NutrientsService nutrientsService) {
        this.nutrientsService = nutrientsService;
    }

    @GetMapping()
    public BaseResponse<List<NutrientsResponse>> getAllNutrients() {
        List<NutrientsResponse> allNutrientsResponses = nutrientsService.getAllNutrients();
        return new BaseResponse<>(allNutrientsResponses);
    }
    @GetMapping("/representation") // 대표 4개
    public BaseResponse<List<NutrientsResponse>> getRepresentNutrients() {
        List<NutrientsResponse> allNutrientsResponses = nutrientsService.getRepresentNutrients();
        return new BaseResponse<>(allNutrientsResponses);
    }

    @GetMapping("/{nutrients_Id}")
    public BaseResponse<NutrientsDetailResponse> getNutrientById(@PathVariable Long nutrients_Id) {
        NutrientsDetailResponse nutrientsDetailResponse = nutrientsService.getNutrientsById(nutrients_Id);

        if (nutrientsDetailResponse != null) {
            return new BaseResponse<>(nutrientsDetailResponse);
        } else {
            return new BaseResponse<>(ResponseStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{disease_code}")
    public BaseResponse<List<NutrientsResponse>> getPersonalNutrients(@PathVariable String disease_code) {
        List<NutrientsResponse> nutrientsResponses = nutrientsService.getPersonalNutrients(disease_code);
        return new BaseResponse<>(nutrientsResponses);
    }

    @GetMapping("/recommendation") // 의약품 추천
    public BaseResponse<List<NutrientsResponse>> getRecommendedMedicines(
            @RequestParam(name = "disease1", defaultValue = "") String disease1,
            @RequestParam(name = "disease2", defaultValue = "") String disease2,
            @RequestParam(name = "disease3", defaultValue = "") String disease3,
            @RequestParam(name = "disease4", defaultValue = "") String disease4,
            @RequestParam(name = "disease5", defaultValue = "") String disease5
    ) {
        List<NutrientsResponse> recommendedNutrients = nutrientsService.getRecommendedNutrients(disease1, disease2, disease3, disease4, disease5);
        return new BaseResponse<>(recommendedNutrients);
    }
}
