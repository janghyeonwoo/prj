package com.example.multi.prj.controller;

import com.example.multi.prj.TestService.TestService;
import com.example.multi.prj.dto.*;
import com.example.multi.prj.type.AreaType;
import com.example.multi.prj.type.GenderType;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.lang.annotation.Native;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MyConfig myConfig;


    @GetMapping("/config")
    public MyConfig getMyConfig(){
        return myConfig;
    }


    @GetMapping
    public LeagueSummaryRsp getTest(){
        List<PlayerBasicInfo> regiList = getRegisterList();
        List<PlayerBasicInfo> joinList = getJoinList();

        LeagueSummaryRsp leagueSummaryRsp = new LeagueSummaryRsp();
        leagueSummaryRsp.setAgeGenderDto(this.getAgeGenderDto(regiList,joinList));
        leagueSummaryRsp.setAreaRateDto(this.getAreaRateDto(regiList,joinList));
        return  leagueSummaryRsp;
    }


    //연령대&성별 백분율 조회
    private AgeGenderDto getAgeGenderDto(List<PlayerBasicInfo> regiList, List<PlayerBasicInfo> joinList){
        AgeGenderDto ageGenderDto = new AgeGenderDto();

        Integer regiTotalCnt = regiList.size();
        Integer joinTotalCnt = joinList.size();

        //남여 비율 (    리그참여 남자 / 리그참여 전체인원수  )
        Map<GenderType, List<PlayerBasicInfo>> joinGroupGender = joinList.stream().collect(Collectors.groupingBy(PlayerBasicInfo::getGender));
        Integer joinMaleCnt = joinGroupGender.getOrDefault(GenderType.M, Collections.EMPTY_LIST).size();
        Integer joinfeMaleCnt = joinGroupGender.getOrDefault(GenderType.F, Collections.EMPTY_LIST).size();

        //남여&나이대
        Map<AgeGenderKey, List<PlayerBasicInfo>> ageGenderList = joinList.stream().collect(Collectors.groupingBy(PlayerBasicInfo::convertAgeGenderKey));

        //남성 비율
        ageGenderDto.setGenderRate(new AgeGenderDto.GenderRate(joinMaleCnt, joinfeMaleCnt, joinList.size()));
        for (int age = 10; age <= 60; age += 10) {
            Integer ageMaleCnt = ageGenderList.getOrDefault(new AgeGenderKey(GenderType.M, age), Collections.EMPTY_LIST).size();
            Integer ageFemaleCnt = ageGenderList.getOrDefault(new AgeGenderKey(GenderType.F, age), Collections.EMPTY_LIST).size();
            ageGenderDto.setting(new AgeGenderDto.GenderRate(ageMaleCnt, ageFemaleCnt, regiTotalCnt), age);
        }
        return ageGenderDto;
    }

    private AreaRateDto getAreaRateDto(List<PlayerBasicInfo> regiList, List<PlayerBasicInfo> joinList){
        AreaRateDto areaRateDto = new AreaRateDto();
        Map<AreaType, Long> regiAreaGroup = regiList.stream().collect(Collectors.groupingBy(PlayerBasicInfo::getArea, Collectors.counting()));
        Map<AreaType, Long> joinAreaGroup = joinList.stream().collect(Collectors.groupingBy(PlayerBasicInfo::getArea, Collectors.counting()));

        for(AreaType area : AreaType.values()){
            Integer regiAreaCnt = regiAreaGroup.getOrDefault(area, 0L).intValue();
            Integer joinAreaCnt = joinAreaGroup.getOrDefault(area, 0L).intValue();
            areaRateDto.setting(regiAreaCnt,joinAreaCnt,area);
        }
        return areaRateDto;
    }


    //9개
    private List<PlayerBasicInfo> getRegisterList(){
        return Arrays.asList(
                new PlayerBasicInfo(30, AreaType.CAPITAL, GenderType.M),
                new PlayerBasicInfo(30, AreaType.CAPITAL, GenderType.M),
                new PlayerBasicInfo(30, AreaType.CAPITAL, GenderType.M),
                new PlayerBasicInfo(30, AreaType.CAPITAL, GenderType.M),
                new PlayerBasicInfo(30, AreaType.CHUNGCHEONG, GenderType.F),
                new PlayerBasicInfo(30, AreaType.CHUNGCHEONG, GenderType.F),

                new PlayerBasicInfo(20, AreaType.CHUNGCHEONG, GenderType.F),
                new PlayerBasicInfo(20, AreaType.CHUNGCHEONG, GenderType.F),
                new PlayerBasicInfo(20, AreaType.CHUNGCHEONG, GenderType.M),
                new PlayerBasicInfo(20, AreaType.CHUNGCHEONG, GenderType.M)
        );
    }

    //9개 중 여자 4개 남자 2개
    private List<PlayerBasicInfo> getJoinList(){
        return Arrays.asList(
                new PlayerBasicInfo(30, AreaType.CAPITAL, GenderType.M),
                new PlayerBasicInfo(30, AreaType.CAPITAL, GenderType.M),
                new PlayerBasicInfo(30, AreaType.CAPITAL, GenderType.M),



                new PlayerBasicInfo(20, AreaType.CHUNGCHEONG, GenderType.F),
                new PlayerBasicInfo(20, AreaType.CHUNGCHEONG, GenderType.F),
                new PlayerBasicInfo(20, AreaType.CHUNGCHEONG, GenderType.F),
                new PlayerBasicInfo(20, AreaType.CHUNGCHEONG, GenderType.F),
                new PlayerBasicInfo(20, AreaType.CHUNGCHEONG, GenderType.M),
                new PlayerBasicInfo(20, AreaType.CHUNGCHEONG, GenderType.M)
                );
    }

    @GetMapping("add")
    public void addTest(){
        testService.increased();
    }
}
