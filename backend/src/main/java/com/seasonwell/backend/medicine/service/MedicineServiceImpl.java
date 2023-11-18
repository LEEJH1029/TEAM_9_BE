package com.seasonwell.backend.medicine.service;

import com.seasonwell.backend.medicine.dto.MedicineDiseaseDto;
import com.seasonwell.backend.medicine.dto.MedicineDto;
import com.seasonwell.backend.medicine.entity.Medicine;
import com.seasonwell.backend.medicine.repository.MedicineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public List<MedicineDto> getAllMedicines() { // 약 전체 출력
        List<Medicine> medicineEntities = medicineRepository.findAll();
        return convertToDtoList(medicineEntities);
    }

    @Override
    public List<MedicineDto> getRepresentMedicines() { // 약 대표 4개 출력
        List<Medicine> medicineEntities = medicineRepository.findAll();

        List<Medicine> selectedMedicines = new ArrayList<>();
        selectedMedicines.add(medicineEntities.get(1));
        selectedMedicines.add(medicineEntities.get(4));
        selectedMedicines.add(medicineEntities.get(9));
        selectedMedicines.add(medicineEntities.get(15));

        return convertToDtoList(selectedMedicines);
    }

    @Override
    public MedicineDto getMedicineByCode(String medicineCode) { // medicine _ detail
        Medicine medicineEntity = medicineRepository.findById(medicineCode).orElse(null);
        if(medicineEntity != null) {
            return convertToDto(medicineEntity);
        } else {
            return null;
        }
    }

    @Override
    public List<MedicineDto> getMedicineByNameContaining(String keyword) { // 약 검색 결과 출력
        List<Medicine> medicineEntities = medicineRepository.findByMedicine_nameContainingIgnoreCase(keyword);
        return convertToDtoList(medicineEntities);
    }

    @Override
    public List<MedicineDto> getRecommendedMedicines(String disease1, String disease2, String disease3, String disease4, String disease5) { // 약 추천
        List<String> diseaseCodes = List.of(disease1, disease2, disease3, disease4, disease5);
        List<Medicine> recommendedMedicines = medicineRepository.findByDiseaseCodeIn(diseaseCodes);
        return convertToDtoList(recommendedMedicines);
    }

    @Override
    public List<MedicineDto> getPersonalMedicines(List<String> diseases) {
        List<Medicine> resultMedicines = getMedicinesByDiseases(diseases);
        return convertToDtoList(resultMedicines);
    }

    private List<Medicine> getMedicinesByDiseases(List<String> diseases) {
        List<Medicine> resultMedicines = new ArrayList<>();

        for (String disease : diseases) {
            if (StringUtils.hasText(disease)) {
                List<Medicine> medicines = medicineRepository.findByDiseaseSymptomContainingIgnoreCase(disease);
                resultMedicines.addAll(medicines);
            }
        }

        // 중복 제거
        Set<Medicine> uniqueMedicines = new HashSet<>(resultMedicines);
        resultMedicines.clear();
        resultMedicines.addAll(uniqueMedicines);

        return resultMedicines;
    }



    private List<MedicineDto> convertToDtoList(List<Medicine> medicineEntities) {
        return medicineEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MedicineDto convertToDto(Medicine medicineEntity) {
        return MedicineDto.builder()
                .disease_code(medicineEntity.getDisease().getDisease_code())
                .disease_name(medicineEntity.getDisease().getDisease_name())
                .medicine_code(medicineEntity.getMedicine_code())
                .medicine_name(medicineEntity.getMedicine_name())
                .medicine_efficacy(medicineEntity.getMedicine_efficacy())
                .medicine_usage(medicineEntity.getMedicine_usage())
                .medicine_caution(medicineEntity.getMedicine_caution())
                .medicine_image(medicineEntity.getMedicine_image())
                .build();
    }

    @Override
    public List<MedicineDiseaseDto> getMedicinesByDiseaseCode(String disease_code) { // 질병 별 의약품 출력
        List<Medicine> medicines = medicineRepository.findByDiseaseCode(disease_code);
        return convertToDtoList2(medicines);
    }

    private List<MedicineDiseaseDto> convertToDtoList2(List<Medicine> medicineEntities) {
        return medicineEntities.stream()
                .map(this::convertToDto2)
                .collect(Collectors.toList());
    }

    private MedicineDiseaseDto convertToDto2(Medicine medicineEntity) {
        return MedicineDiseaseDto.builder()
                .medicine_code(medicineEntity.getMedicine_code())
                .medicine_name(medicineEntity.getMedicine_name())
                .build();
    }

}
