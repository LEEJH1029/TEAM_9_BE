package com.seasonwell.backend.disease.entity;

import lombok.*;
import javax.persistence.*;

@Entity(name = "Disease")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Disease {

    // pk) 질병 코드
    @Id
    @Column(length = 20, nullable = false)
    private String disease_code;

    // 계절
    @Column(length = 20, nullable = false)
    private String disease_season;

    // 질병명
    @Column(length = 100, nullable = false)
    private String disease_name;

    // 예방 방법
    @Column(length = 2000, nullable = false)
    private String disease_protect;

    // 치료법
    @Column(length = 2000, nullable = false)
    private String disease_cure;

    // 증상
    @Column(length = 2000, nullable = false)
    private String disease_symptom;

}
