package com.seasonwell.backend.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequest {
    @JsonProperty
    private String user_id;

    @JsonProperty
    private String user_pw;
}
