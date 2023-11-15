package com.seasonwell.backend.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateUserRequest {

    private String disease_code;
    private String user_name;
    private String user_pw;
    private String user_tel;
    private Double user_height;
    private Double user_weight;
    private Integer user_age;
}
