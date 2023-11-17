package com.seasonwell.backend.medicine.service;

import com.seasonwell.backend.medicine.dto.MedicineDiseaseDto;
import com.seasonwell.backend.medicine.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    List<MedicineDto> getAllMedicines();
    List<MedicineDto> getRepresentMedicines();

    MedicineDto getMedicineByCode(String medicineCode);

    List<MedicineDto> getMedicineByNameContaining(String keyword);

    List<MedicineDiseaseDto> getMedicinesByDiseaseCode(String disease_code);

    List<MedicineDto> getRecommendedMedicines(String disease1, String disease2, String disease3, String disease4, String disease5);
}
