package com.contest.chaeso.admin.restaurant.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantMenuExcelDto {
    private String rtName;
    private String menuName;
    private int price;
    private String imgLink;

    public RestaurantMenuExcelDto(String rtName, String menuName, int price, String imgLink) {
        this.rtName = rtName;
        this.menuName = menuName;
        this.price = price;
        this.imgLink = imgLink;
    }
}
