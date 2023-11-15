package com.seasonwell.backend.user.service;

import com.seasonwell.backend.global.config.BaseException;
import com.seasonwell.backend.global.config.ResponseStatus;
import com.seasonwell.backend.user.dto.UpdateUserRequest;
import com.seasonwell.backend.user.entity.UserEntity;
import com.seasonwell.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    public UserEntity loadMemberByUsername(String user_id) throws BaseException {
        UserEntity userId = userRepository.findByUserId(user_id)
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_USER));

        return userId;
    }

    public void updateUser(UpdateUserRequest updateUserRequest, HttpSession session) throws BaseException {
        String userId = (String) session.getAttribute("userId");

        UserEntity currentUser = loadMemberByUsername(userId);

        currentUser.setDisease_code(updateUserRequest.getDisease_code());
        currentUser.setUser_name(updateUserRequest.getUser_name());
        currentUser.setUser_pw(updateUserRequest.getUser_pw());
        currentUser.setUser_tel(updateUserRequest.getUser_tel());
        currentUser.setUser_height(updateUserRequest.getUser_height());
        currentUser.setUser_weight(updateUserRequest.getUser_weight());
        currentUser.setUser_age(updateUserRequest.getUser_age());

        userRepository.save(currentUser);
    }
}

