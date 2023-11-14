package com.seasonwell.backend.user.Repositoy;

import com.seasonwell.backend.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // user_id 스네이크 케이스로 작성해도 findBy 사용 가능한지?? 카멜케이스로 썼을 때는 되던데
//    Optional<UserEntity> findByUserid(String user_id);
}
