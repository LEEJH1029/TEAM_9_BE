package com.seasonwell.backend.nutrients.service;

import com.seasonwell.backend.board.dto.AllBoardResponse;
import com.seasonwell.backend.board.entity.Board;
import com.seasonwell.backend.nutrients.dto.NutrientsDetailResponse;
import com.seasonwell.backend.nutrients.dto.NutrientsResponse;
import com.seasonwell.backend.nutrients.entity.Nutrients;
import com.seasonwell.backend.nutrients.repository.NutrientsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<NutrientsResponse> getRepresentNutrients() {
        try {
            List<Nutrients> nutrientsList = nutrientsRepository.findAll();
            List<NutrientsResponse> responseDtoList = new ArrayList<>();

            if (nutrientsList.size() >= 4) {
                nutrientsList = nutrientsList.subList(0, 4);
            }
            for (Nutrients nutrients : nutrientsList) {
                responseDtoList.add(new NutrientsResponse(nutrients));
            }

            return responseDtoList;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    // 영양제 이름으로 검색
    public List<NutrientsResponse> findAllNutrientesByName(String nutrients_name) {
        try {
            List<Nutrients> nutrientsList = nutrientsRepository.findByNutrients_name(nutrients_name);
            List<NutrientsResponse> responseDtoList = new ArrayList<>();

            for (Nutrients nutrients : nutrientsList) {
                responseDtoList.add(
                        new NutrientsResponse(nutrients)
                );
            }
            return responseDtoList;
        } catch (Exception e) {
//            throw new ResponseStatus.BAD_REQUEST;
        }
        return null;
    }


    public NutrientsDetailResponse getNutrientsById(Long nutrient_id) {
        Optional<Nutrients> nutrientsOptional = nutrientsRepository.findById(nutrient_id);

        if (nutrientsOptional.isPresent()) {
            Nutrients nutrients = nutrientsOptional.get();
            return new NutrientsDetailResponse(nutrients);
        } else {
            return null;
        }
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
