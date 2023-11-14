package com.seasonwell.backend.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SignupRequest {
    @NotNull
    @JsonProperty
    private String user_id;

    @JsonProperty
    private String user_name;

    @NotNull
    @JsonProperty
    private String user_pw;

    @JsonProperty
    private String user_tel;

    @JsonProperty
    private Double user_height;

    @JsonProperty
    private Double user_weight;

    @JsonProperty
    private Integer user_age;

    @JsonProperty
    private Boolean user_gender;

    @JsonProperty
    private Integer user_type;
}
