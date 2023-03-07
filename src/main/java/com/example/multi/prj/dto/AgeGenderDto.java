package com.example.multi.prj.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AgeGenderDto {

    private GenderRate genderRate;

    private GenderRate oneRate;
    private GenderRate twoRate;
    private GenderRate threeRate;


    public void setting(GenderRate genderRate, Integer age) {
        switch (age) {
            case 10:
                oneRate = genderRate;
            case 20:
                twoRate = genderRate;
            case 30:
                threeRate = genderRate;
        }
    }


    @Setter
    @Getter
    public static class GenderRate {
        private Double maleRate = 0.0;
        private Double femaleRate = 0.0;

        public GenderRate(Integer maleCnt, Integer femaleCnt, Integer totalCnt) {
            if (totalCnt != 0 && maleCnt != 0) {
                this.maleRate = ((double) maleCnt / (double) totalCnt) * 100.0;
            }
            if(totalCnt != 0 && femaleCnt != 0){
                this.femaleRate = ((double) femaleCnt / (double) totalCnt) * 100.0;
            }
        }
    }

}

