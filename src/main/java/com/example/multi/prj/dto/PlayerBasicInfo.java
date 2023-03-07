package com.example.multi.prj.dto;

import com.example.multi.prj.type.AreaType;
import com.example.multi.prj.type.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.geom.Area;

@AllArgsConstructor
@Getter
public class PlayerBasicInfo {

    private Integer age;
    private AreaType area;
    private GenderType gender;

    public AgeGenderKey convertAgeGenderKey(){
        return new AgeGenderKey(this.gender, this.age);
    }
}
