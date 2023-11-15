package com.seasonwell.backend.user.service;

import com.seasonwell.backend.global.config.BaseException;
import com.seasonwell.backend.global.config.ResponseStatus;
import com.seasonwell.backend.user.entity.UserEntity;
import com.seasonwell.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    public UserEntity loadMemberByUsername(String user_id) throws BaseException {
        UserEntity userId = userRepository.findByUserId(user_id)
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_USER));

        return userId;
    }
}

