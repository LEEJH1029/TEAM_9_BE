package com.seasonwell.backend.disease.service;

import com.seasonwell.backend.disease.dto.DiseaseDetailDto;
import com.seasonwell.backend.disease.dto.DiseaseDto;
import com.seasonwell.backend.disease.dto.PreventionResponse;

import java.util.List;

public interface DiseaseService {
    List<DiseaseDto> getAllDiseases();
    List<DiseaseDto> getRepresentDiseases();
    DiseaseDetailDto getDiseaseByCode(String diseaseCode);
    List<DiseaseDto> getDiseasesBySeason(String season);
    List<DiseaseDto> getDiseasesByNameContaining(String keyword);
    List<PreventionResponse> getPreventionByDiseaseCode(String diseaseCode);
}
