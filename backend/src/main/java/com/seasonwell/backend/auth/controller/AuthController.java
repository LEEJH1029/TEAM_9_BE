package com.seasonwell.backend.auth.controller;

import com.seasonwell.backend.auth.dto.LoginResponseDto;
import com.seasonwell.backend.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/auth/login")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/kakao")
    public ResponseEntity<LoginResponseDto> kakaoLogin(HttpServletRequest request) {
        String code = request.getParameter("code");
        String kakaoAccessToken = authService.getKakaoAccessToken(code);
        log.debug("code={}", kakaoAccessToken);
        return authService.kakaoLogin(kakaoAccessToken);
    }

}
