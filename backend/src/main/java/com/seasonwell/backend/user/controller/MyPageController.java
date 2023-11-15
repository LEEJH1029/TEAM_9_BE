package com.seasonwell.backend.user.controller;

import com.seasonwell.backend.global.config.BaseException;
import com.seasonwell.backend.global.config.BaseResponse;
import com.seasonwell.backend.global.config.ResponseStatus;
import com.seasonwell.backend.user.dto.MyPageResponse;
import com.seasonwell.backend.user.entity.UserEntity;
import com.seasonwell.backend.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MyPageService mypageService;

    @GetMapping("/userinfo")
    public BaseResponse<MyPageResponse> memberInfo(HttpSession session) {
        try {
            String userId = (String) session.getAttribute("userId");
            UserEntity currentUser = mypageService.loadMemberByUsername(userId);
            log.info(userId);

            return new BaseResponse<>(new MyPageResponse(currentUser));
        } catch (BaseException e) {
            return new BaseResponse<>(ResponseStatus.INVALID_AUTH);
        }
    }
}
