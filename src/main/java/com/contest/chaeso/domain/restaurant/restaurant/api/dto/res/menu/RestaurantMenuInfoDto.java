package com.contest.chaeso.domain.restaurant.restaurant.api.dto.res.menu;

import com.contest.chaeso.domain.restaurant.menu.domain.RestaurantMenu;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantMenuInfoDto {

    private Long rtMenuId;
    private String menuName;
    private int price;
    private String menuImgLink;

    public RestaurantMenuInfoDto(RestaurantMenu restaurantMenu) {
        this.rtMenuId = restaurantMenu.getRtMenuId();
        this.menuName = restaurantMenu.getMenuName();
        this.price = restaurantMenu.getPrice();
        this.menuImgLink = restaurantMenu.getMenuImgLink();
    }
}
