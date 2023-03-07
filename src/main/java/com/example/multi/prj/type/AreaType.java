package com.example.multi.prj.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AreaType {
    CAPITAL(1, "수도권"),
    GANGWON(2, "강원"),
    CHUNGCHEONG(3, "충청"),
    JEOLLA(4, "전라"),
    GYEONGSANG(5, "경상"),
    JEJU(6, "제주"),
    ;

    @JsonValue
    private final Integer value;
    private final String text;
}
