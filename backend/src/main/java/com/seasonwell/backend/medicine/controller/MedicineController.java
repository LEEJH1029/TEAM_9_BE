package com.seasonwell.backend.medicine.controller;

import com.seasonwell.backend.medicine.dto.MedicineDiseaseDto;
import com.seasonwell.backend.medicine.dto.MedicineDto;
import com.seasonwell.backend.medicine.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/medicine")
public class MedicineController {
    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("") // 전체 출력
    public ResponseEntity<List<MedicineDto>> getAllMedicines() {
        List<MedicineDto> allMedicines = medicineService.getAllMedicines();
        return new ResponseEntity<>(allMedicines, HttpStatus.OK);
    }

    @GetMapping("/representation") // 대표 4개
    public ResponseEntity<List<MedicineDto>> getRepresentMedicines() {
        List<MedicineDto> allMedicines = medicineService.getRepresentMedicines();
        return new ResponseEntity<>(allMedicines, HttpStatus.OK);
    }

    @GetMapping("/{medicine_code}") // detail
    public ResponseEntity<MedicineDto> getMedicineByCode(@PathVariable String medicine_code) {
        MedicineDto medicineDto = medicineService.getMedicineByCode(medicine_code);
        if (medicineDto != null) {
            return new ResponseEntity<>(medicineDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{medicine_name}") // 검색
    public ResponseEntity<List<MedicineDto>> searchMedicines(@PathVariable String medicine_name) {
        List<MedicineDto> medicinesByKeyword = medicineService.getMedicineByNameContaining(medicine_name);
        if(!medicinesByKeyword.isEmpty()) {
            return new ResponseEntity<>(medicinesByKeyword, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/recommendation") // 의약품 추천
    public ResponseEntity<List<MedicineDto>> getRecommendedMedicines(
            @RequestParam(name = "disease1", defaultValue = "") String disease1,
            @RequestParam(name = "disease2", defaultValue = "") String disease2,
            @RequestParam(name = "disease3", defaultValue = "") String disease3,
            @RequestParam(name = "disease4", defaultValue = "") String disease4,
            @RequestParam(name = "disease5", defaultValue = "") String disease5
    ) {
        List<MedicineDto> recommendedMedicines = medicineService.getRecommendedMedicines(disease1, disease2, disease3, disease4, disease5);
        return new ResponseEntity<>(recommendedMedicines, HttpStatus.OK);
    }

    @GetMapping("/personal")
    public ResponseEntity<List<MedicineDiseaseDto>> getPersonalMedicines(
            @RequestParam(name = "disease1", defaultValue = "") String disease1,
            @RequestParam(name = "disease2", defaultValue = "") String disease2,
            @RequestParam(name = "disease3", defaultValue = "") String disease3,
            @RequestParam(name = "disease4", defaultValue = "") String disease4
    ) {
        List<String> diseases = Arrays.asList(disease1, disease2, disease3, disease4);
        List<MedicineDiseaseDto> personalMedicines = medicineService.getPersonalMedicines(diseases);
        return new ResponseEntity<>(personalMedicines, HttpStatus.OK);
    }


}
