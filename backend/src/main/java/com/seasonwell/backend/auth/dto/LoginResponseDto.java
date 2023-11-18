package com.seasonwell.backend.auth.dto;

import com.seasonwell.backend.user.entity.User;
import lombok.Data;

@Data
public class LoginResponseDto {
    public boolean loginSuccess;
    public User user;
}