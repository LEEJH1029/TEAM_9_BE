package com.seasonwell.backend.nutrients.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Nutrients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Nutrients {
    // pk) 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    // 질병 - 영양제 관계
    @OneToMany(mappedBy = "nutrients")
    private List<DiseaseNutrients> diseaseNutrients;

    // 영양제 이름
    @Column(length = 20)
    private String nutrients_name;

    // 효능
    @Column(length = 200)
    private String nutrients_efficiency;

    // 이미지 url
    @Column(length = 200)
    private String image_url;
}
