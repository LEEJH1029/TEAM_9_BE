package com.seasonwell.backend.disease.dto;

import com.seasonwell.backend.medicine.dto.MedicineDiseaseDto;
import com.seasonwell.backend.medicine.dto.MedicineDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseCureDto {
//    private String category;
    private List<MedicineDiseaseDto> keyword; // 약 리스트
    private String description; // 치료법 설명
}

