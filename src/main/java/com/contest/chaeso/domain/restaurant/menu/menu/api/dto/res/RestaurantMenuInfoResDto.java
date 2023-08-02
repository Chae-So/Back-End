package com.contest.chaeso.domain.restaurant.menu.menu.api.dto.res;

import com.contest.chaeso.domain.restaurant.menu.menu.domain.RestaurantMenu;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantMenuInfoResDto {

    private Long rtMenuId;
    private String menuName;
    private int price;
    private List<RestaurantMenuImgResDto> restaurantMenuImgList;

    public RestaurantMenuInfoResDto(RestaurantMenu restaurantMenu) {
        this.rtMenuId = restaurantMenu.getRtMenuId();
        this.menuName = restaurantMenu.getMenuName();
        this.price = restaurantMenu.getPrice();
        this.restaurantMenuImgList = restaurantMenu.getRestaurantMenuImgList().stream().map(RestaurantMenuImgResDto::new).collect(Collectors.toList());
    }
}
