package com.seasonwell.backend.user.controller;

import com.seasonwell.backend.global.config.BaseException;
import com.seasonwell.backend.user.dto.MyPageResponse;
import com.seasonwell.backend.user.dto.UpdateUserRequest;
import com.seasonwell.backend.user.entity.User;
import com.seasonwell.backend.user.service.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
    private final MyPageService mypageService;

    @GetMapping("/userinfo")
    public ResponseEntity<MyPageResponse> memberInfo(HttpSession session) {
        try {
            String userId = (String) session.getAttribute("userId");
            User currentUser = mypageService.loadMemberByUsername(userId);
            log.info(userId);

            return new ResponseEntity<>(new MyPageResponse(currentUser), HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/userinfo/update")
    public ResponseEntity<String> updateInfo(
            @RequestBody UpdateUserRequest updateUserRequest,
            HttpSession session
    ) {
        try {
            mypageService.updateUser(updateUserRequest, session);
            String result = "회원정보 변경 완료";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (BaseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
