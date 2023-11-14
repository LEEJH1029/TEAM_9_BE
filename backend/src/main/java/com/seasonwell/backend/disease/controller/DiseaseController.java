package com.seasonwell.backend.disease.controller;

import com.seasonwell.backend.disease.dto.DiseaseDto;
import com.seasonwell.backend.disease.service.DiseaseParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/disease")
@RestController
public class DiseaseController {

    private final DiseaseParser diseaseParser;

    @Autowired
    public DiseaseController(DiseaseParser diseaseParser) {
        this.diseaseParser = diseaseParser;
    }

    @GetMapping("/test")
    public List<DiseaseDto> parseDiseases() {
        List<DiseaseDto> diseaseDtoList = diseaseParser.parseCsvFile();
        return diseaseDtoList;
    }

}
