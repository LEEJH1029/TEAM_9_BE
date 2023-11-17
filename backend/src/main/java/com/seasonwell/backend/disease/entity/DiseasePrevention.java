package com.seasonwell.backend.disease.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DiseasePrevention {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_code")
    private Disease disease;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prevention_id")
    private Prevention prevention;

}
