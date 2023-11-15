package com.seasonwell.backend.medicine.service;

import com.seasonwell.backend.medicine.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    List<MedicineDto> getAllMedicines();

    MedicineDto getMedicineByCode(String medicineCode);

    List<MedicineDto> getMedicineByName(String name);
    List<MedicineDto> getMedicineByNameContaining(String keyword);

    List<MedicineDto> getRecommendedMedicines(String disease1, String disease2, String disease3, String disease4, String disease5);
}
