package com.contest.chaeso.admin.restaurant.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantExcelDto {
    private String name;
    private String category;
    private String phoneNumber;
    private String address;
    private Float corpLat;
    private Float corpLon;
    private int forHere;
    private int toGo;
    private int delivery;
    private String bzh; //영업일

    public RestaurantExcelDto(String name, String category, String phoneNumber, String address, Float corpLat, Float corpLon, int forHere, int toGo, int delivery, String bzh) {
        this.name = name;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.corpLat = corpLat;
        this.corpLon = corpLon;
        this.forHere = forHere;
        this.toGo = toGo;
        this.delivery = delivery;
        this.bzh = bzh;
    }
}
