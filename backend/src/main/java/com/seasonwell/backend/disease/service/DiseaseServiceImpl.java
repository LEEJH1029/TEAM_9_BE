package com.seasonwell.backend.disease.service;

import com.seasonwell.backend.disease.dto.DiseaseDto;
import com.seasonwell.backend.disease.entity.Disease;
import com.seasonwell.backend.disease.repository.DiseaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DiseaseServiceImpl implements DiseaseService {
    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public List<DiseaseDto> getAllDiseases() {
        List<Disease> diseaseEntities = diseaseRepository.findAll();
        return convertToDtoList(diseaseEntities);
    }

    @Override
    public DiseaseDto getDiseaseByCode(String diseaseCode) {
        Disease diseaseEntity = diseaseRepository.findById(diseaseCode).orElse(null);
        if (diseaseEntity != null) {
            return convertToDto(diseaseEntity);
        } else {
            return null;
        }
    }

    @Override
    public List<DiseaseDto> getDiseasesBySeason(String season) {
        List<Disease> diseaseEntities = diseaseRepository.findByDisease_season(season);
        return convertToDtoList(diseaseEntities);
    }

    @Override
    public List<DiseaseDto> getDiseasesByName(String name) {
        List<Disease> diseaseEntities = diseaseRepository.findByDisease_name(name);
        return convertToDtoList(diseaseEntities);
    }

    @Override
    public List<DiseaseDto> getDiseasesByNameContaining(String keyword) {
        List<Disease> diseaseEntities = diseaseRepository.findByDiseaseNameContainingIgnoreCase(keyword);
        return convertToDtoList(diseaseEntities);
    }

    private List<DiseaseDto> convertToDtoList(List<Disease> diseaseEntities) {
        return diseaseEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DiseaseDto convertToDto(Disease diseaseEntity) {
        return DiseaseDto.builder()
                .disease_code(diseaseEntity.getDisease_code())
                .disease_season(diseaseEntity.getDisease_season())
                .disease_name(diseaseEntity.getDisease_name())
                .disease_protect(diseaseEntity.getDisease_protect())
                .disease_cure(diseaseEntity.getDisease_cure())
                .disease_symptom(diseaseEntity.getDisease_symptom())
                .build();
    }
}

