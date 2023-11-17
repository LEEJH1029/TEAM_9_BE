package com.seasonwell.backend.disease.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseProtectDto {
    private List<PreventionResponse> keyword; // 예방법 이미지, 텍스트 리스트
    private String description; // 예방법 설명
}
