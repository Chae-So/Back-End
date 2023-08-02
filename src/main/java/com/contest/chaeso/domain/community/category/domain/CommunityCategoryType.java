package com.contest.chaeso.domain.community.category.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommunityCategoryType {

    RESTAURANT("음식점"), CAFE("카페"), STORE("스토어");

    private String name;

}
