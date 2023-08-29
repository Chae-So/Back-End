package com.contest.chaeso.domain.restaurant.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
public class Address {

    private String address; // 경기도 성남시 분당구 판교역로 166
    private Float corpLat;
    private Float corpLon;

    public Address(String address, Float corpLat, Float corpLon) {
        this.address = address;
        this.corpLat = corpLat;
        this.corpLon = corpLon;
    }
}
