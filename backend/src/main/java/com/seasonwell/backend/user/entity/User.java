package com.seasonwell.backend.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    // pk) 아이디. camelCase 사용
    @Id
    @Column(length = 20, nullable = false)
    private String userId;

    // fk) 질병 코드
    @Column(length = 100)
    private String disease_code;

    // 이름
    @Column(length = 20)
    private String user_name;

    // 비밀번호
    @Column(length = 15, nullable = false)
    private String user_pw;

    // 전화번호
    @Column(length = 13)
    private String user_tel;

    // 키
    @Column
    private Double user_height;

    // 몸무게
    @Column
    private Double user_weight;

    // 나이
    @Column
    private Integer user_age;

    // 성별
    // 0: 여성    1: 남성
    @Column
    private Boolean user_gender;

    // 타입
    // 1: 일반사용자     2: 의료종사자       3: 관리자
    @Column
    private Integer user_type;
}