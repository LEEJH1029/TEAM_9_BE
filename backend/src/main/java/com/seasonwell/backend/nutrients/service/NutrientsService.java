package com.seasonwell.backend.nutrients.service;

import com.seasonwell.backend.nutrients.dto.NutrientsResponse;
import com.seasonwell.backend.nutrients.entity.Nutrients;
import com.seasonwell.backend.nutrients.repository.NutrientsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NutrientsService {
    private final NutrientsRepository nutrientsRepository;

    public List<NutrientsResponse> getAllNutrients() {
        try {
            List<Nutrients> nutrientsList = nutrientsRepository.findAll();
            List<NutrientsResponse> responseDtoList = new ArrayList<>();

            for (Nutrients nutrients : nutrientsList) {
                responseDtoList.add(new NutrientsResponse(nutrients));
            }
            return responseDtoList;
        } catch (Exception e) {
//            throw new ResponseStatus.BAD_REQUEST;
        }
        return null;
    }

    public List<NutrientsResponse> getPersonalNutrients(String disease_code) {
        try {
            List<Nutrients> nutrientsList = nutrientsRepository.findAllByDiseaseCode(disease_code);
            List<NutrientsResponse> responses = new ArrayList<>();

            for (Nutrients nutrients : nutrientsList) {
                responses.add(new NutrientsResponse(nutrients));
            }
            return responses;
        } catch (Exception e) {
//            throw
        }
        return null;
    }


    public List<NutrientsResponse> getRecommendedNutrients(String disease1, String disease2, String disease3, String disease4, String disease5) {
        List<String> diseaseCodes = List.of(disease1, disease2, disease3, disease4, disease5);

        List<Nutrients> recommendedNutrients = nutrientsRepository.findByDiseaseCodeIn(diseaseCodes);
        List<NutrientsResponse> responses = new ArrayList<>();

        for (Nutrients nutrients : recommendedNutrients) {
            responses.add(new NutrientsResponse(nutrients));
        }
        return responses;

    }
}
