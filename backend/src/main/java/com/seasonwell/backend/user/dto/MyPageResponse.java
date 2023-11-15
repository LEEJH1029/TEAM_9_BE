package com.seasonwell.backend.user.dto;

import com.seasonwell.backend.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageResponse {
    private String userId;
    private String disease_code;
    private String user_name;
    private String user_pw;
    private String user_tel;
    private Double user_height;
    private Double user_weight;
    private Integer user_age;
    private Boolean user_gender;

    public MyPageResponse(UserEntity user) {
        this.userId = user.getUserId();
        this.disease_code = user.getDisease_code();
        this.user_name = user.getUser_name();
        this.user_pw = user.getUser_pw();
        this.user_tel = user.getUser_tel();
        this.user_height = user.getUser_height();
        this.user_weight = user.getUser_weight();
        this.user_age = user.getUser_age();
        this.user_gender = user.getUser_gender();
    }
}
