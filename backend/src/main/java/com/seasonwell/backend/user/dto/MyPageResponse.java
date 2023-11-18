package com.seasonwell.backend.user.dto;

import com.seasonwell.backend.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageResponse {
    private String userId;
    private List<String> disease_codes;
    private String user_name;
    private String user_pw;
    private String user_tel;
    private Double user_height;
    private Double user_weight;
    private Integer user_age;
    private Boolean user_gender;

    public MyPageResponse(User user) {
        this.userId = user.getUserId();
        this.disease_codes = Optional.ofNullable(user.getDisease_code())
                .map(codes -> Arrays.asList(codes.split(", ")))
                .orElse(Collections.emptyList());
        this.user_name = user.getUser_name();
        this.user_pw = user.getUser_pw();
        this.user_tel = user.getUser_tel();
        this.user_height = user.getUser_height();
        this.user_weight = user.getUser_weight();
        this.user_age = user.getUser_age();
        this.user_gender = user.getUser_gender();
    }
}
