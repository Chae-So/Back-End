package com.contest.chaeso.domain.users.language.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {

    KOREAN("한국어"),
    ENGLISH("영어"),
    JAPANESE("일본어"),
    CHINESE("중국어"),
    FRENCH("프랑스어"),
    GERMAN("독일어"),
    SPANISH("스페인어"),
    PORTUGUESE("포르투칼어");

    private String name;

}
