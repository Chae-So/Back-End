package com.contest.chaeso.domain.restaurant.menu.menu.api.dto.res;

import com.contest.chaeso.domain.restaurant.menu.img.domain.RestaurantMenuImg;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantMenuImgResDto {

    private String rtMenuImgLink;

    public RestaurantMenuImgResDto(RestaurantMenuImg restaurantMenuImg) {
        this.rtMenuImgLink = restaurantMenuImg.getRtMenuImgLink();
    }
}
