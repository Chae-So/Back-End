package com.contest.chaeso.domain.users.vegan.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VeganLevel {

    VEGAN("비건"),
    LACTO("락토"),
    OVO("오보"),
    POLLO("폴로"),
    PESCO("페스코");

    private String name;
}
