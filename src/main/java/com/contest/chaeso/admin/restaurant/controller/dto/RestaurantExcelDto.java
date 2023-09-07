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
    private Double corpLat;
    private Double corpLon;
    private int forHere;
    private int toGo;
    private int delivery;
    private String rtImgLink;
    private String bzh; //영업일

    public RestaurantExcelDto(String name, String category, String phoneNumber, String address, Double corpLat, Double corpLon, int forHere, int toGo, int delivery, String rtImgLink, String bzh) {
        this.name = name;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.corpLat = corpLat;
        this.corpLon = corpLon;
        this.forHere = forHere;
        this.toGo = toGo;
        this.delivery = delivery;
        this.rtImgLink = rtImgLink;
        this.bzh = bzh;
    }
}
