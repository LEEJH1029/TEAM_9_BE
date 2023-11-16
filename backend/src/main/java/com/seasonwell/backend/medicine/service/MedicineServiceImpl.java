package com.seasonwell.backend.medicine.service;

import com.seasonwell.backend.medicine.dto.MedicineDto;
import com.seasonwell.backend.medicine.entity.Medicine;
import com.seasonwell.backend.medicine.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public List<MedicineDto> getAllMedicines() {
        List<Medicine> medicineEntities = medicineRepository.findAll();
        return convertToDtoList(medicineEntities);
    }

    @Override
    public MedicineDto getMedicineByCode(String medicineCode) {
        Medicine medicineEntity = medicineRepository.findById(medicineCode).orElse(null);
        if(medicineEntity != null) {
            return convertToDto(medicineEntity);
        } else {
            return null;
        }
    }

    @Override
    public List<MedicineDto> getMedicineByName(String name) {
        List<Medicine> medicineEntities = medicineRepository.findByMedicine_name(name);
        return convertToDtoList(medicineEntities);
    }

    @Override
    public List<MedicineDto> getMedicineByNameContaining(String keyword) {
        List<Medicine> medicineEntities = medicineRepository.findByMedicine_nameContainingIgnoreCase(keyword);
        return convertToDtoList(medicineEntities);
    }

    @Override
    public List<MedicineDto> getRecommendedMedicines(String disease1, String disease2, String disease3, String disease4, String disease5) {
        List<String> diseaseCodes = List.of(disease1, disease2, disease3, disease4, disease5);

        List<Medicine> recommendedMedicines = medicineRepository.findByDiseaseCodeIn(diseaseCodes);

        return convertToDtoList(recommendedMedicines);
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

}
