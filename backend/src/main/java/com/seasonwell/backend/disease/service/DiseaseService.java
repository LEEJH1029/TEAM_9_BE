package com.seasonwell.backend.disease.service;

import com.seasonwell.backend.disease.dto.DiseaseDto;

import java.util.List;

public interface DiseaseService {
    List<DiseaseDto> getAllDiseases();
    DiseaseDto getDiseaseByCode(String diseaseCode);
    List<DiseaseDto> getDiseasesBySeason(String season);
    List<DiseaseDto> getDiseasesByName(String name);
    List<DiseaseDto> getDiseasesByNameContaining(String keyword);
}
