package com.seasonwell.backend.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.seasonwell.backend.auth.dto.KakaoAccountDto;
import com.seasonwell.backend.auth.dto.KakaoTokenDto;
import com.seasonwell.backend.auth.dto.LoginResponseDto;
import com.seasonwell.backend.user.entity.User;
import com.seasonwell.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {

    @Value("${api.kakao-rest-api-key}")
    private String apiKey;

    private final UserRepository userRepository;

    @Transactional
    public String getKakaoAccessToken(String code) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", apiKey);
            params.add("redirect_uri", "https://seasonwell.vercel.app/auth/login/kakao");
            params.add("code", code);

            HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

            RestTemplate rt = new RestTemplate();
            ResponseEntity<String> accessTokenResponse = rt.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    kakaoTokenRequest,
                    String.class
            );

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            KakaoTokenDto kakaoTokenDto = objectMapper.readValue(accessTokenResponse.getBody(), KakaoTokenDto.class);

            return kakaoTokenDto.getAccessToken();
        } catch (Exception e) {
            log.error("Error in getKakaoAccessToken", e);
            throw new RuntimeException("Failed to get Kakao access token", e);
        }
    }

    public ResponseEntity<LoginResponseDto> kakaoLogin(String kakaoAccessToken) {
        try {
            User account = getKakaoInfo(kakaoAccessToken);

            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setLoginSuccess(true);
            loginResponseDto.setUser(account);

            User existOwner = userRepository.findByUserId(account.getUserId()).orElse(null);

            try {
                if (existOwner == null) {
                    System.out.println("처음 로그인 하는 회원입니다.");
                    userRepository.save(account);
                }
                loginResponseDto.setLoginSuccess(true);

                return ResponseEntity.ok().body(loginResponseDto);
            } catch (Exception e) {
                loginResponseDto.setLoginSuccess(false);
                return ResponseEntity.badRequest().body(loginResponseDto);
            }
        } catch (Exception e) {
            log.error("Error in kakaoLogin", e);
            throw new RuntimeException("Failed to perform Kakao login", e);
        }
    }

    public User getKakaoInfo(String kakaoAccessToken) {
        try {
            RestTemplate rt = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + kakaoAccessToken);
            headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

            HttpEntity<MultiValueMap<String, String>> accountInfoRequest = new HttpEntity<>(headers);

            ResponseEntity<String> accountInfoResponse = rt.exchange(
                    "https://kapi.kakao.com/v2/user/me",
                    HttpMethod.POST,
                    accountInfoRequest,
                    String.class
            );

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            KakaoAccountDto kakaoAccountDto = objectMapper.readValue(accountInfoResponse.getBody(), KakaoAccountDto.class);

            String kakaoId = kakaoAccountDto.getUser_id();
            User existOwner = userRepository.findByUserId(kakaoId).orElse(null);

            if (existOwner != null) {
                return User.builder()
                        .userId(kakaoAccountDto.getUser_id())
                        .user_pw(kakaoAccountDto.getUser_pw())
                        .build();
            } else {
                return User.builder()
                        .userId(kakaoAccountDto.getUser_id())
                        .user_pw(kakaoAccountDto.getUser_pw())
                        .build();
            }
        } catch (Exception e) {
            log.error("Error in getKakaoInfo", e);
            throw new RuntimeException("Failed to get Kakao user information", e);
        }
    }
}
