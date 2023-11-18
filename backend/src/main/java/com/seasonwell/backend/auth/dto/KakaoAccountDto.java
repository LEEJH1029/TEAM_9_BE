package com.seasonwell.backend.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seasonwell.backend.user.entity.User;
import lombok.Data;

@Data
public class KakaoAccountDto {
    private String user_id;
    private String user_pw;
}