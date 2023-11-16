package com.seasonwell.backend.user.repository;

import com.seasonwell.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    // user_id 스네이크 케이스로 작성해도 findBy 사용 가능한지?? 카멜케이스로 썼을 때는 되던데
    Optional<User> findByUserId(String userId);


}
