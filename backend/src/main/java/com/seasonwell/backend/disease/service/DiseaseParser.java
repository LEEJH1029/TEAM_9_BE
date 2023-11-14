package com.seasonwell.backend.disease.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.seasonwell.backend.disease.dto.DiseaseDto;
import com.seasonwell.backend.disease.entity.Disease;
import com.seasonwell.backend.disease.repository.DiseaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DiseaseParser {
    private final DiseaseRepository diseaseRepository;

    public DiseaseParser(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }
//    private final Disease disease;
//
//    public DiseaseParser(Disease disease) {
//        this.disease = disease;
//    }

    public List<DiseaseDto> parseCsvFile() {
        String resource = "backend/src/main/resources/diseases.csv";
        String[] diseaseInfo;

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(resource), "UTF-8"))) {
            csvReader.readNext();
            // 첫 번째 라인 읽기
            diseaseInfo = csvReader.readNext();

            List<DiseaseDto> diseaseDtoList = new ArrayList<>();

            while (diseaseInfo != null) {
                DiseaseDto diseaseDto = new DiseaseDto();

                diseaseDto.setDisease_code(diseaseInfo[0]);
                diseaseDto.setDisease_season(diseaseInfo[1]);
                diseaseDto.setDisease_name(diseaseInfo[2]);
                diseaseDto.setDisease_protect(diseaseInfo[3]);
                diseaseDto.setDisease_cure(diseaseInfo[4]);
                diseaseDto.setDisease_symptom(diseaseInfo[5]);

                diseaseDtoList.add(diseaseDto);
                log.debug("diseaseDto={}", diseaseDto);
//                disease.persist(diseaseDto);
                // 다음 라인 읽기
                diseaseInfo = csvReader.readNext();
            }

            saveDiseaseEntities(diseaseDtoList);

            return diseaseDtoList;
        } catch (IOException e) {
            log.error("IOException while parsing CSV file", e);
            // IOException 처리 (예: 로깅 또는 예외 다시 던지기)
            throw new RuntimeException("Error reading CSV file", e);
        } catch (CsvValidationException e) {
            log.error("CsvValidationException while parsing CSV file", e);
            // CsvValidationException 처리 (예: 로깅 또는 예외 다시 던지기)
            throw new RuntimeException("Error validating CSV file", e);
        }
    }

    private void saveDiseaseEntities(List<DiseaseDto> diseaseDtoList) {
        List<Disease> diseaseEntities = diseaseDtoList.stream()
                .map(DiseaseDto::toEntity)
                .collect(Collectors.toList());

        diseaseRepository.saveAll(diseaseEntities);
    }
}
