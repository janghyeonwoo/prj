package com.example.multi.prj.dto;

import com.example.multi.prj.type.AreaType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaRateDto {
    //    @Schema(description = "강원도 수")
    private double gwCnt;

    //    @Schema(description = "수도권 수")
    private double sdCnt;

    //    @Schema(description = "충청도 수")
    private double ccCnt;

    //    @Schema(description = "경상도 수")
    private double gsCnt;

    //    @Schema(description = "전라도 수")
    private double jlCnt;

    //    @Schema(description = "제주도 수")
    private double jjCnt;

    public AreaRateDto() {
        this.gwCnt = 0.0;
        this.sdCnt = 0.0;
        this.ccCnt = 0.0;
        this.gsCnt = 0.0;
        this.jlCnt = 0.0;
        this.jjCnt = 0.0;
    }

    public void setting(Integer regiCnt, Integer joinCnt, AreaType areaType) {
        double rate = 0.0;
        if (regiCnt != 0 && joinCnt != 0) {
            rate = ((double) joinCnt / (double) regiCnt) * 100.0;
        }

        switch (areaType) {
            case CAPITAL:
                this.sdCnt = rate;
                break;
            case GANGWON:
                this.gwCnt = rate;
                break;
            case CHUNGCHEONG:
                this.ccCnt = rate;
                break;
            case GYEONGSANG:
                this.gsCnt = rate;
                break;
            case JEOLLA:
                this.jlCnt = rate;
                break;
            case JEJU:
                this.jjCnt = rate;
                break;
        }
    }


}
