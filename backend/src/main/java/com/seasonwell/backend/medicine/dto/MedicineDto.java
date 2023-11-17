package com.seasonwell.backend.medicine.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineDto {

    private String disease_code; // fk

    private String disease_name;

    private String medicine_code;

    private String medicine_name;

    private String medicine_efficacy;

    private String medicine_usage;

    private String medicine_caution;

    private String medicine_image;

}
