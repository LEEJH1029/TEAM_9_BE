package com.seasonwell.backend.nutrients.controller;

import com.seasonwell.backend.board.dto.AllBoardResponse;
import com.seasonwell.backend.global.config.BaseResponse;
import com.seasonwell.backend.nutrients.dto.NutrientsResponse;
import com.seasonwell.backend.nutrients.service.NutrientsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/nutrients")
@RestController
@Slf4j
public class NutrientsController {
    private final NutrientsService nutrientsService;

    public NutrientsController(NutrientsService nutrientsService) {
        this.nutrientsService = nutrientsService;
    }

    @GetMapping("/all")
    public BaseResponse<List<NutrientsResponse>> getAllNutrients() {
        List<NutrientsResponse> allNutrientsResponses = nutrientsService.getAllNutrients();
        return new BaseResponse<>(allNutrientsResponses);
    }

    @GetMapping("/personal/{disease_code}")
    public BaseResponse<List<NutrientsResponse>> getPersonalNutrients(@PathVariable String disease_code) {
        List<NutrientsResponse> nutrientsResponses = nutrientsService.getPersonalNutrients(disease_code);
        return new BaseResponse<>(nutrientsResponses);
    }
}
