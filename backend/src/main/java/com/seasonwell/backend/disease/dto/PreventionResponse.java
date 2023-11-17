package com.seasonwell.backend.disease.dto;

import com.seasonwell.backend.disease.entity.Prevention;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PreventionResponse {
    private String prevention_image;
    private String prevention_text;

    public PreventionResponse(Prevention prevention) {
        this.prevention_image = prevention.getPrevention_image();
        this.prevention_text = prevention.getPrevention_text();
    }
}
