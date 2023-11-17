package com.seasonwell.backend.nutrients.controller;

import com.seasonwell.backend.nutrients.dto.NutrientsDetailResponse;
import com.seasonwell.backend.nutrients.dto.NutrientsResponse;
import com.seasonwell.backend.nutrients.service.NutrientsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<NutrientsResponse>> getAllNutrients() {
        List<NutrientsResponse> allNutrientsResponses = nutrientsService.getAllNutrients();
        if (allNutrientsResponses.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            return new ResponseEntity<>(allNutrientsResponses, HttpStatus.OK);
        }
    }
    @GetMapping("/representation") // 대표 4개
    public BaseResponse<List<NutrientsResponse>> getRepresentNutrients() {
        List<NutrientsResponse> allNutrientsResponses = nutrientsService.getRepresentNutrients();
        return new BaseResponse<>(allNutrientsResponses);
    }

    @GetMapping("/{nutrients_Id}")
    public ResponseEntity<NutrientsDetailResponse> getNutrientById(@PathVariable Long nutrients_Id) {
        NutrientsDetailResponse nutrientsDetailResponse = nutrientsService.getNutrientsById(nutrients_Id);

        if (nutrientsDetailResponse != null) {
            return new ResponseEntity<>(nutrientsDetailResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{disease_code}")
    public ResponseEntity<List<NutrientsResponse>> getPersonalNutrients(@PathVariable String disease_code) {
        List<NutrientsResponse> nutrientsResponses = nutrientsService.getPersonalNutrients(disease_code);
        if (nutrientsResponses.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            return new ResponseEntity<>(nutrientsResponses, HttpStatus.OK);
        }
    }

    @GetMapping("/recommendation") // 의약품 추천
    public ResponseEntity<List<NutrientsResponse>> getRecommendedMedicines(
            @RequestParam(name = "disease1", defaultValue = "") String disease1,
            @RequestParam(name = "disease2", defaultValue = "") String disease2,
            @RequestParam(name = "disease3", defaultValue = "") String disease3,
            @RequestParam(name = "disease4", defaultValue = "") String disease4,
            @RequestParam(name = "disease5", defaultValue = "") String disease5
    ) {
        List<NutrientsResponse> recommendedNutrients = nutrientsService.getRecommendedNutrients(disease1, disease2, disease3, disease4, disease5);
        return new ResponseEntity<>(recommendedNutrients, HttpStatus.OK);
    }
}
