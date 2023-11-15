package com.seasonwell.backend.user.controller;

import com.seasonwell.backend.global.config.BaseException;
import com.seasonwell.backend.global.config.BaseResponse;
import com.seasonwell.backend.user.dto.SignupRequest;
import com.seasonwell.backend.user.dto.UserLoginRequest;
import com.seasonwell.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public BaseResponse<String> registerMember(@RequestBody SignupRequest signupRequest) {
        try {
            userService.registerUser(signupRequest);
            String result = "회원가입이 완료되었습니다.";
            log.info(signupRequest.getUser_id());
            log.info(signupRequest.getUser_pw());
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            log.info("회원가입 실패");
            return new BaseResponse<>(e.getStatus());
        }
    }

    @PostMapping("/login")
    public BaseResponse<String> login(@RequestBody UserLoginRequest dto, HttpSession session) {
        try {
            userService.loginUser(dto.getUser_id(), dto.getUser_pw());
            session.setAttribute("userId", dto.getUser_id());
            String result = "로그인 완료";
            return new BaseResponse<>(result);
        } catch (BaseException e) {
            log.info("로그인 실패");
            return new BaseResponse<>(e.getStatus());
        }
    }
}
