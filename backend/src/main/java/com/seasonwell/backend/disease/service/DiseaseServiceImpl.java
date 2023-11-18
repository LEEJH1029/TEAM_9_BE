package com.seasonwell.backend.disease.service;

import com.seasonwell.backend.disease.dto.*;
import com.seasonwell.backend.disease.entity.Disease;
import com.seasonwell.backend.disease.entity.Prevention;
import com.seasonwell.backend.disease.repository.DiseasePreventionRepository;
import com.seasonwell.backend.disease.repository.DiseaseRepository;
import com.seasonwell.backend.medicine.dto.MedicineDiseaseDto;
import com.seasonwell.backend.medicine.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DiseaseServiceImpl implements DiseaseService {
    private final DiseaseRepository diseaseRepository;
    private final DiseasePreventionRepository diseasePreventionRepository;
    private final MedicineService medicineService;


    @Autowired
    public DiseaseServiceImpl(DiseaseRepository diseaseRepository, DiseasePreventionRepository diseasePreventionRepository, MedicineService medicineService) {
        this.diseaseRepository = diseaseRepository;
        this.diseasePreventionRepository = diseasePreventionRepository;
        this.medicineService = medicineService;
    }

    @Override
    public List<DiseaseDto> getRepresentDiseases() { // 약 대표 4개 출력
        List<Disease> diseaseEntities = diseaseRepository.findAll();

        List<Disease> selectedDiseases = new ArrayList<>();
        selectedDiseases.add(diseaseEntities.get(0));
        selectedDiseases.add(diseaseEntities.get(2));
        selectedDiseases.add(diseaseEntities.get(6));
        selectedDiseases.add(diseaseEntities.get(9));

        return convertToDtoList(selectedDiseases);
    }


    @Override
    public List<DiseaseDto> getAllDiseases() { // 전체 질병 출력
        List<Disease> diseaseEntities = diseaseRepository.findAll();
        return convertToDtoList(diseaseEntities);
    }

    @Override
    public List<DiseaseDto> getDiseasesBySeason(String season) { // 계절별 질병 출력
        List<Disease> diseaseEntities = diseaseRepository.findByDisease_season(season);
        return convertToDtoList(diseaseEntities);
    }

    @Override
    public List<DiseaseDto> getDiseasesByNameContaining(String keyword) { // 질병 검색 결과 출력
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
                .disease_image(diseaseEntity.getDisease_image())
                .build();
    }

    @Override
    public List<PreventionResponse> getPreventionByDiseaseCode(String disease_code) { // 질병 별 예방방법 출력
        try {
            List<Prevention> preventionList = diseasePreventionRepository.findAllByDiseaseCode(disease_code);
            List<PreventionResponse> preventionResponseList = new ArrayList<>();

            for (Prevention prevention : preventionList) {
                preventionResponseList.add(new PreventionResponse(prevention));
            }
            return preventionResponseList;
        } catch (Exception e) {
            // Exception 처리
        }
        return null;
    }

    @Override
    public DiseaseDetailDto getDiseaseByCode(String diseaseCode) { // Disease_Detail
        Disease diseaseEntity = diseaseRepository.findById(diseaseCode).orElse(null);
        List<PreventionResponse> preventionResponses = getPreventionByDiseaseCode(diseaseCode);
        List<MedicineDiseaseDto> medicineDtos = medicineService.getMedicinesByDiseaseCode(diseaseCode);

        if (diseaseEntity != null) {
            return convertToDetailDto(diseaseEntity, preventionResponses, medicineDtos);
        } else {
            return null;
        }
    }

    private DiseaseDetailDto convertToDetailDto(Disease diseaseEntity, List<PreventionResponse> preventionResponses, List<MedicineDiseaseDto> medicineDtos) {
        return DiseaseDetailDto.builder()
                .disease_code(diseaseEntity.getDisease_code())
                .disease_season(diseaseEntity.getDisease_season())
                .disease_name(diseaseEntity.getDisease_name())
                .disease_protect(convertToProtectDto(preventionResponses,diseaseEntity.getDisease_protect()))
                .disease_cure(converToCureDto(medicineDtos, diseaseEntity.getDisease_cure()))
                .disease_symptom(diseaseEntity.getDisease_symptom())
                .disease_image(diseaseEntity.getDisease_image())
                .build();
    }

    private DiseaseProtectDto convertToProtectDto(List<PreventionResponse> preventionResponses, String description) {
        return DiseaseProtectDto.builder()
                .keyword(preventionResponses)
                .description(description)
                .build();
    }


    private DiseaseCureDto converToCureDto(List<MedicineDiseaseDto> medicineDtos, String description) {
        return DiseaseCureDto.builder()
                .keyword(medicineDtos)
                .description(description)
                .build();
    }

}

