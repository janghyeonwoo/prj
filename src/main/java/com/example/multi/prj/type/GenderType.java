package com.example.multi.prj.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenderType {
    M("남자", 1),
    F("여자", 2),
    ;
    private final String text;

    @JsonValue
    private final Integer value;
}
