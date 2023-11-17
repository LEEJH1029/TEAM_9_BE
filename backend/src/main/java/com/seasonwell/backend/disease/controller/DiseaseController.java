package com.seasonwell.backend.disease.controller;

import com.seasonwell.backend.disease.dto.DiseaseDetailDto;
import com.seasonwell.backend.disease.dto.DiseaseDto;
import com.seasonwell.backend.disease.dto.PreventionResponse;
import com.seasonwell.backend.disease.service.DiseaseService;
import com.seasonwell.backend.global.config.BaseResponse;
import com.seasonwell.backend.medicine.dto.MedicineDto;
import com.seasonwell.backend.medicine.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/disease")
public class DiseaseController {
    private final DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("") // 전체 출력
    public ResponseEntity<List<DiseaseDto>> getAllDiseases() {
        List<DiseaseDto> allDiseases = diseaseService.getAllDiseases();
        return new ResponseEntity<>(allDiseases, HttpStatus.OK);
    }

    @GetMapping("/{disease_code}") // detail
    public ResponseEntity<DiseaseDetailDto> getDiseaseByCode(@PathVariable String disease_code) {
        DiseaseDetailDto diseaseDetailDto = diseaseService.getDiseaseByCode(disease_code);
        if (diseaseDetailDto != null) {
            return new ResponseEntity<>(diseaseDetailDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/season/{season}") // 계절별 출력
    public ResponseEntity<List<DiseaseDto>> getDiseasesBySeason(@PathVariable String season) {
        List<DiseaseDto> diseasesBySeason = diseaseService.getDiseasesBySeason(season);
        if (!diseasesBySeason.isEmpty()) {
            return new ResponseEntity<>(diseasesBySeason, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{disease_name}") /// 검색
    public ResponseEntity<List<DiseaseDto>> searchDiseases(@PathVariable String disease_name) {
        List<DiseaseDto> diseasesByKeyword = diseaseService.getDiseasesByNameContaining(disease_name);
        if (!diseasesByKeyword.isEmpty()) {
            return new ResponseEntity<>(diseasesByKeyword, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

