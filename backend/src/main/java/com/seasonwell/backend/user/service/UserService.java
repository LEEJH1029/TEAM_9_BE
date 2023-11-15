package com.seasonwell.backend.user.service;

import com.seasonwell.backend.global.config.BaseException;
import com.seasonwell.backend.global.config.ResponseStatus;
import com.seasonwell.backend.user.repository.UserRepository;
import com.seasonwell.backend.user.dto.SignupRequest;
import com.seasonwell.backend.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(SignupRequest signupRequest) throws BaseException {
        String userid = signupRequest.getUser_id();

        if(userRepository.findByUserId(userid).isPresent()) {
            throw new BaseException(ResponseStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUserId(userid);
        user.setUser_name(signupRequest.getUser_name());
        user.setUser_pw(signupRequest.getUser_pw());
        user.setUser_tel(signupRequest.getUser_tel());
        user.setUser_height(signupRequest.getUser_height());
        user.setUser_weight(signupRequest.getUser_weight());
        user.setUser_age(signupRequest.getUser_age());
        user.setUser_gender(signupRequest.getUser_gender());
        user.setUser_type(signupRequest.getUser_type());

        userRepository.save(user);
    }

    public void loginUser(String user_id, String password) throws BaseException {
        userRepository.findByUserId(user_id).orElseThrow(() -> new BaseException(ResponseStatus.NO_USER));
    }
}
