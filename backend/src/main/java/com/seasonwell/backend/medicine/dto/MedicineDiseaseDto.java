package com.seasonwell.backend.medicine.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineDiseaseDto {
    private String medicine_code;
    private String medicine_name;
}
