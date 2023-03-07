package com.example.multi.prj.dto;

import com.example.multi.prj.type.GenderType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class AgeGenderKey {
    private GenderType genderType;
    private Integer age;

    public AgeGenderKey(GenderType genderType, Integer age) {
        this.genderType = genderType;
        this.age = age;
    }
}
