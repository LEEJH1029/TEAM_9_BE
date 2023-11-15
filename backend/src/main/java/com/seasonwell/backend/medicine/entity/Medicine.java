package com.seasonwell.backend.medicine.entity;

import com.seasonwell.backend.disease.entity.Disease;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
public class Medicine {

    @ManyToOne
    @JoinColumn(name = "disease_code", nullable = false)
    private Disease disease; // fk

    @Id
    private String medicine_code;

    private String medicine_name;

    private String medicine_efficacy;

    private String medicine_usage;

    private String medicine_caution;

    private String medicine_image;

    public Medicine() {}

    public Medicine(Disease disease, String medicine_code, String medicine_name, String medicine_efficacy, String medicine_usage, String medicine_caution, String medicine_image) {
        this.disease = disease;
        this.medicine_code = medicine_code;
        this.medicine_name = medicine_name;
        this.medicine_efficacy = medicine_efficacy;
        this.medicine_usage = medicine_usage;
        this.medicine_caution = medicine_caution;
        this.medicine_image = medicine_image;
    }

}
